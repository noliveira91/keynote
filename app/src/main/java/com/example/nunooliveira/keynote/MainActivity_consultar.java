package com.example.nunooliveira.keynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity_consultar extends AppCompatActivity {

    protected Button btneditar;
    protected Button btnapagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        btneditar = (Button) findViewById(R.id.editar);
        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (MainActivity_consultar.this,MainActivity_editar.class);
                startActivity (i);
            }
        });

        btnapagar = (Button) findViewById(R.id.apagar);
        btnapagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (MainActivity_consultar.this,MainActivity.class);
                startActivity (i);
            }
        });


    }
}
