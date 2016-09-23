package com.quadrolord.epicbattle.desktop.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;
import com.quadrolord.ejge.utils.AbstractAuthService;
import com.quadrolord.ejge.utils.Dates;
import com.quadrolord.ejge.utils.PlatformServices;
import com.quadrolord.ejge.utils.StorageValueListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Goorus on 16.09.2016.
 */
public class DesktopAuthService extends AbstractAuthService {

    private boolean mLogEnabled = false;

    private static final int LOG_ACTION_AUTH = 1;

    private static final int LOG_ACTION_SET_SETTINGS = 2;

    private boolean mInitialized = false;

    private String mDevUserUid = "dev-default";

    private Array<EventListenerItem> mListeners = new Array<EventListenerItem>();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRootRef;
    private DatabaseReference mUserRef;

    @Override
    public void auth() {
        init();
        saveValue("last_auth", Dates.now());
    }

    @Override
    public void goOffline() {
        init();
//        mDatabase.goOffline();
    }

    private void init() {
        if (mInitialized) {
            return;
        }
        mInitialized = true;
        File credentialFile = Gdx.files.internal("keys/google-api-key.json").file();
        mDevUserUid = Gdx.files.internal("keys/firebase-debug-user.json").readString().trim();
        FirebaseOptions options = null;
        Map<String, Object> auth = new HashMap<String, Object>();
        auth.put("uid", mDevUserUid);
        try {
            options = new FirebaseOptions.Builder()
                    .setServiceAccount(new FileInputStream(credentialFile))
                    .setDatabaseUrl("https://epic-battle.firebaseio.com")
                    .setDatabaseAuthVariableOverride(auth)
                    .build();
        } catch (FileNotFoundException e) {
            Gdx.app.error(getClass().getSimpleName(), "No credential file for firebase account. Its not presented in git repository.", e);
        }
        FirebaseApp app = FirebaseApp.initializeApp(options);

        mDatabase = FirebaseDatabase.getInstance(app);
//        mDatabase.setLogLevel(Logger.Level.DEBUG);
        mDatabase.setPersistenceEnabled(false);
        mRootRef = mDatabase.getReference();
        mRootRef.keepSynced(false);
        mUserRef = mRootRef.child("users/" + mDevUserUid);

        writeLog(LOG_ACTION_AUTH, null);
        saveValue("last_auth", Dates.now());

//        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);
//        FirebaseDatabase.getInstance().goOnline();
    }

    @Override
    public void saveValue(String key, Object value) {
        Gdx.app.log("auth.saveValue", key + " = " + (value != null ? value.toString() : "null"));

        DatabaseReference refOption = mUserRef.child("settings/" + key);
        refOption.setValue(value, new DatabaseReference.CompletionListener() {

            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Gdx.app.log("value saved", databaseReference.getKey());
            }

        });

        writeLog(LOG_ACTION_SET_SETTINGS, new String[] {
                "key", key,
                "val", value.toString()
        });

//        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String res = (String)dataSnapshot.getValue();
//                System.out.println(res);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }

    @Override
    public void addValueListener(Object owner, String key, final StorageValueListener listener) {
        init();
        EventListenerItem item = new EventListenerItem();
        item.owner = owner;
        item.listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Gdx.app.error("DB listener", "onDataChange" + dataSnapshot.exists());
                listener.onDataChange( dataSnapshot.getValue() );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Gdx.app.error("DB listener", "cancelled", databaseError.toException());
            }
        };
        item.reference = mUserRef.child(key);
        item.reference.addValueEventListener(item.listener);
        item.reference.push();
    }

    @Override
    public void removeValueListener(Object owner) {
        for (Iterator<EventListenerItem> it = mListeners.iterator(); it.hasNext(); ) {
            EventListenerItem item = it.next();
            if (item.owner == owner) {
                item.reference.removeEventListener(item.listener);
                it.remove();
            }
        }
    }

    public void writeLog(int action, String[] data) {
        if (!mLogEnabled) {
            return;
        }

        Map<String, Object> logObj = new HashMap<String, Object>();
        logObj.put("created", Dates.now());
        logObj.put("action", action);
        if (data != null) {
            if (data.length == 1) {
                logObj.put("data", data[0]);
            } else if (data.length > 1) {
                Map<String, Object> dataObj = new HashMap<String, Object>();
                for (int i = 0; i < data.length; i += 2) {
                    dataObj.put(data[i], data[i + 1]);
                }
                logObj.put("data", dataObj);
            }
        }

        DatabaseReference refLog = mUserRef.child("log").push();
        refLog.updateChildren(logObj);
    }

}
