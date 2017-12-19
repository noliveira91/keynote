package com.example.nunooliveira.keynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Button btncriarnovo;
    protected Button btncriarnovo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

