package com.quadrolord.epicbattle.logic.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quadrolord.ejge.utils.AbstractAuthService;
import com.quadrolord.ejge.utils.StorageValueListener;
import com.quadrolord.epicbattle.logic.bgu.GameHelper;

import java.util.Iterator;

/**
 * Created by Goorus on 16.09.2016.
 */
public class AndroidAuthService extends AbstractAuthService {

    private static final String TAG = "AndroidAuthService";

    private Activity mActivity;

    private boolean mInitialized = false;

    private GameHelper mGameHelper;

    private Array<EventListenerItem> mListeners = new Array<EventListenerItem>();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRootRef;
    private DatabaseReference mUserRef;

    public AndroidAuthService(Activity activity) {
        mActivity = activity;
        mGameHelper = new GameHelper(activity, GameHelper.CLIENT_SIGN_IN);// | GameHelper.CLIENT_PLUS);
//        FirebaseApp.initializeApp(mActivity, FirebaseOptions.fromResource(mActivity));
    }

    public GameHelper getGameHelper() {
        return mGameHelper;
    }

    @Override
    public void auth() {
        init();
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            Gdx.app.log(TAG, "RC_RESOLVE ok");
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        } else {
            Gdx.app.log(TAG, "RC_RESOLVE failed: " + result.getStatus().toString());
            // Google Sign In failed, update UI appropriately
            // [START_EXCLUDE]
//                                updateUI(null);
            // [END_EXCLUDE]
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Gdx.app.log(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        Gdx.app.log(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Gdx.app.log(TAG, "signInWithCredential", task.getException());
                        }
                        // ...
                    }
                });
    }

    public void onStart(Activity activity) {
        OptionalPendingResult<GoogleSignInResult> optPenRes = Auth.GoogleSignInApi.silentSignIn(mGameHelper.getApiClient());
        if (optPenRes.isDone()) {
            Gdx.app.log(TAG, "Yayy!");
            GoogleSignInResult result = optPenRes.get();
            handleSignInResult(result);
        } else {
            Gdx.app.log(TAG, "Oh1...");
            optPenRes.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    Gdx.app.log(TAG, "Oh2...");
                    handleSignInResult(googleSignInResult);
                }
            });
        }
        mGameHelper.onStart(activity);
    }

    public void setup() {
        mGameHelper.setup(new GameHelper.GameHelperListener() {
            @Override
            public void onSignInFailed() {
                Gdx.app.log("AndroidLauncher", "sign in failed");
            }

            @Override
            public void onSignInSucceeded() {
                Gdx.app.log(TAG, "sign in success");

                mDatabase = FirebaseDatabase.getInstance();
                mRootRef = mDatabase.getReference();

                FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {

                    @Override
                    public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        mUserRef = user == null ? null : mRootRef.child( user.getUid() );
                    }

                });

//                Gdx.app.log(TAG, "FA Uid " + FirebaseAuth.getInstance().getCurrentUser().getUid());

//                ConnectionResult cr = mGameHelper.getApiClient().getConnectionResult(Plus.API);

                mGameHelper.getApiClient().registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {

                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        ConnectionResult cr = mGameHelper.getApiClient().getConnectionResult(Auth.GOOGLE_SIGN_IN_API);
                        if (cr.isSuccess()) {
                            Gdx.app.log(TAG, "Games connected OK");

                            Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGameHelper.getApiClient());
                            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
                            if (googleSignInResult != null) {
                                Gdx.app.log(TAG, "Games connection not null!");
                                firebaseAuthWithGoogle(googleSignInResult.getSignInAccount());
                            } else {
                                Gdx.app.log(TAG, "Games connection is null");
                            }
                        } else {
                            Gdx.app.log(TAG, "Games connection failed");
                        }
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                });

            }
        });
    }

    private void init() {
        if (mInitialized) {
            return;
        }
        mInitialized = true;

        mGameHelper.beginUserInitiatedSignInNew();

//        mGameHelper.beginUserInitiatedSignIn();
    }

    @Override
    public void goOffline() {

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

    @Override
    public void saveValue(String key, Object value) {
        init();
        mUserRef.child(key).setValue(value);
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        mGameHelper.onActivityResult(requestCode, resultCode, intent);

        if (requestCode != GameHelper.RC_RESOLVE) {
            return;
        }

        Gdx.app.log(TAG, "onActivityResult with RC_RESOLVE or RC_UNUSED");

        GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
        handleSignInResult(googleSignInResult);
    }

}
