package com.example.testeruas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ListView listView;
    private DatabaseAcces databaseAcces;
    private List<Memo> memos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        databaseAcces = DatabaseAcces.getInstance(this);

        listView = findViewById(R.id.listView);
        Button btnBuat = findViewById(R.id.btnBuat);

        btnBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity2.this, EditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseAcces.open();
        this.memos = databaseAcces.getAllMemos();
        databaseAcces.close();
        MemoAdapter adapter = new MemoAdapter(this,memos);
        this.listView.setAdapter(adapter);
    }


    private class MemoAdapter extends ArrayAdapter<Memo> {

        MemoAdapter(@NonNull Context context, List<Memo> objects) {
            super(context, 0,  objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.activity_layout_list_item, parent, false);
            }

            Button btnEdit = convertView.findViewById(R.id.btnEdit);
            Button btnDelete = convertView.findViewById(R.id.btnDelete);

            TextView txtDate =  convertView.findViewById(R.id.txtDate);
            TextView txtMemo = convertView.findViewById(R.id.txtMemo);

            final Memo memo = memos.get(position);
            txtDate.setText(memo.getDate());
            txtMemo.setText(memo.getShortText());

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity2.this, EditActivity.class);
                    intent.putExtra("MEMO", memo);
                    startActivity(intent);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseAcces.open();
                    databaseAcces.delete(memo);
                    databaseAcces.close();

                    ArrayAdapter<Memo> adapter = (ArrayAdapter<Memo>) listView.getAdapter();
                    adapter.remove(memo);
                    adapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }
}