package com.example.testeruas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        TextView nama = findViewById(R.id.tv_namaMain);


        nama.setText(Preferences.getLoggedInUser(getBaseContext()));


        findViewById(R.id.button_logoutMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getBaseContext(), login_activity.class));
                finish();
            }
        });
    }
}