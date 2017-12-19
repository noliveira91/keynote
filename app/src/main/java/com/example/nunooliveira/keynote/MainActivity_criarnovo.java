package com.example.nunooliveira.keynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_criarnovo extends AppCompatActivity {

    protected Button btncriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criarnovo);

        btncriar = (Button) findViewById(R.id.criar);
        btncriar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent (MainActivity_criarnovo.this,MainActivity.class);
                startActivity (i);
            }
        });


    }
}
