package com.example.testeruas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText etText;

    private Memo memo;
    private Notification channelid;
    private String channelnotif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        this.etText = findViewById(R.id.etText);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCencel);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            memo = (Memo) bundle.get("MEMO");
            if (memo != null) {
                this.etText.setText(memo.getText());
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }


        });

    }


    public void onSaveClicked() {
        DatabaseAcces databaseAcces = DatabaseAcces.getInstance(this);
        databaseAcces.open();
        if (memo == null) {
            Memo temp = new Memo();
            temp.setText(etText.getText().toString());
            databaseAcces.save(temp);
        } else {
            memo.setText(etText.getText().toString());
            databaseAcces.update(memo);
        }
        databaseAcces.close();
        this.finish();
        Button button = findViewById(R.id.btnSave);
        button.setOnClickListener((View.OnClickListener) this);

    }



}


