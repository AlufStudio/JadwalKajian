package com.tolabulilm.jadwalkajian.user;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tolabulilm.jadwalkajian.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView registerText;
    private boolean isLogin = true;
    private static final String TAG = "Login";
    private String email;
    private String password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser fireUser;
    private DatabaseReference mRef;
    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        authListen();
        onClickListener();
    }

    private void initData() {
        mRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    private void authListen() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                fireUser = firebaseAuth.getCurrentUser();
                if (fireUser != null) {
                    // User is signed in
                    // Go to MainActivity
                    //Toast.makeText(getApplicationContext(), "user ada", Toast.LENGTH_SHORT).show();
                    if (isLogin) {
                        toggleProgressBar();
                        goToMainActivity();
                    } else {
                        User newUser = new User(fireUser.getUid(), fireUser.getEmail());
                        mRef.child("users").child(fireUser.getUid()).setValue(newUser).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        toggleProgressBar();
                                        goToMainActivity();
                                    }
                                });
                    }
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + fireUser.getUid());
                } else {
                    // User is signed out
                    //Toast.makeText(getApplicationContext(), "user tidak ada", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };
    }

    private void initView() {
        TextView textJadwalKajian = (TextView) findViewById(R.id.login_jadwalkajian);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Shrikhand-Regular.ttf");
        textJadwalKajian.setTypeface(custom_font);

        loginButton = (Button)findViewById(R.id.login_button);
        inputEmail = (EditText)findViewById(R.id.login_input_email);
        inputPassword = (EditText)findViewById(R.id.login_input_password);
        registerText = (TextView)findViewById(R.id.login_daftar);
        loginProgress = (ProgressBar)findViewById(R.id.login_progress_bar);
    }

    private void onClickListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputIsEmpty()) {
                    toggleProgressBar();
                    getInputData();
                    if (isLogin) {
                        loginWithEmail(email, password);
                    } else {
                        registerWithEmail(email, password);
                    }
                }
            }
        });
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRegister();
            }
        });
    }

    private void registerWithEmail(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Pendaftaran gagal", Toast.LENGTH_SHORT).show();
                            toggleProgressBar();
                        }
                    }
                });
    }

    private void loginWithEmail(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                            toggleProgressBar();
                        }
                    }
                });
    }

    private void getInputData() {
        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();
    }

    private void toggleRegister() {
        if (isLogin) {
            registerText.setText("Sudah punya akun? Login sekarang");
            loginButton.setText("Daftar");
            isLogin = false;
        } else {
            registerText.setText("Belum punya akun? Daftar sekarang");
            loginButton.setText("Login");
            isLogin = true;
        }
    }

    private boolean inputIsEmpty() {
        return (inputEmail.getText().toString().isEmpty() ||
                inputPassword.getText().toString().isEmpty());
    }

    private void goToMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void toggleProgressBar() {
        if (loginProgress.getVisibility() == View.VISIBLE) {
            loginProgress.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
            registerText.setClickable(true);
        } else {
            loginProgress.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
            registerText.setClickable(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
