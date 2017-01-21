package com.tolabulilm.jadwalkajian.user;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tolabulilm.jadwalkajian.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView registerText;
    private boolean isLogin = true;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        onClickListener();
    }

    private void initView() {
        TextView textJadwalKajian = (TextView) findViewById(R.id.login_jadwalkajian);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Shrikhand-Regular.ttf");
        textJadwalKajian.setTypeface(custom_font);

        loginButton = (Button)findViewById(R.id.login_button);
        inputEmail = (EditText)findViewById(R.id.login_input_email);
        inputPassword = (EditText)findViewById(R.id.login_input_password);
        registerText = (TextView)findViewById(R.id.login_daftar);
    }

    private void onClickListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
                if (!inputIsEmpty()) {
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

    }

    private void loginWithEmail(String email, String password) {

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
}
