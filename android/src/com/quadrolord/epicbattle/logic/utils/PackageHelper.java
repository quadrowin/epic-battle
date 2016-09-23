package com.quadrolord.epicbattle.logic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;

import com.badlogic.gdx.Gdx;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Goorus on 21.09.2016.
 */
public class PackageHelper {

    private static final int VALID = 0;

    private static final int INVALID = 1;

    public static int checkAppSignature(Context context) {

        try {

            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(),
                            PackageManager.GET_SIGNATURES);

            for (Signature signature : packageInfo.signatures) {
                byte[] signatureBytes = signature.toByteArray();
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                final String currentSignature = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Gdx.app.log("REMOVE_ME", "Include this string as a value for SIGNATURE:" + currentSignature);

                //compare signatures

                if (signature.equals(currentSignature)) {
                    return VALID;
                };

            }
        } catch (Exception e) {
            //assumes an issue in checking signature., but we let the caller decide on what to do.
        }

        return INVALID;
    }

    public static String getBuildTimestamp(Activity activity)
    {
        String s = "";
        try{
            ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);
            ZipEntry ze = zf.getEntry("classes.dex");
            long time = ze.getTime();
            s = SimpleDateFormat.getInstance().format(new java.util.Date(time));
            zf.close();
        }catch(Exception e){
        }
        return s;
    }

}
