package com.example.testeruas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class login_activity extends AppCompatActivity {

    private EditText mViewUser, mViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewUser = findViewById(R.id.et_emailSignin);
        mViewPassword = findViewById(R.id.et_passwordSignin);
        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    Pengecekkan();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.button_signinSignin).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Pengecekkan();
            }
        });

        findViewById(R.id.button_signupSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), registrasi_activity.class));
            }
        });
    }

    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), home_activity.class));
            finish();
        }
    }


    private void Pengecekkan() {
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();


        if (TextUtils.isEmpty(user)) {
            mViewUser.setError("Masukkan Username");
            fokus = mViewUser;
            cancel = true;
        } else if (!cekUser(user)) {
            mViewUser.setError("Username tidak ditemukan atau salah");
            fokus = mViewUser;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("Masukkan Password");
            fokus = mViewPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            mViewPassword.setError("Password Salah");
            fokus = mViewPassword;
            cancel = true;
        }


        if (cancel) fokus.requestFocus();
        else masuk();
    }

    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean cekPassword(String password) {
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    private void masuk() {
        Preferences.setLoggedInUser(getBaseContext(), Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(), true);
        startActivity(new Intent(getBaseContext(), home_activity.class));
        finish();
    }

}
