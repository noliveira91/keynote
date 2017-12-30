package com.example.nunooliveira.keynote;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected Button btncriarnovo;
    protected Button btncriarnovo2;
    protected AdaptadorBaseDados adb;
    protected Cursor cursor;
    protected ListView listView;
    protected List<String> registos;

    @Override
    protected void onStart() {
        super.onStart();
        adb = new AdaptadorBaseDados(this).open();
    }
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adb.close();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cursor = null;
        registos = new ArrayList<>();
        adb = new AdaptadorBaseDados(this).open();

        cursor = adb.obterRegistos();

        if (cursor.moveToFirst()) {
            do {
                registos.add(cursor.getString(1));

            } while (cursor.moveToNext());
        }



        listView = (ListView)findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, registos);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                cursor.moveToPosition(position);
                int _id = cursor.getInt(0);

                Intent i=new Intent (MainActivity.this,MainActivity_consultar.class);
                i.putExtra("_id", _id);
                startActivity (i);
            }

        });



        btncriarnovo = (Button) findViewById(R.id.criarnovo);
        btncriarnovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (MainActivity.this,MainActivity_criarnovo.class);
                startActivity (i);
            }
        });
    }
}

