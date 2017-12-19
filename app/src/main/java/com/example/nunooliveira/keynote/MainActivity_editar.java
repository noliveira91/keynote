package com.example.nunooliveira.keynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_editar extends AppCompatActivity {

    protected Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        btnguardar = (Button) findViewById(R.id.guardar);
        btnguardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent (MainActivity_editar.this,MainActivity_consultar.class);
                startActivity (i);
            }
        });



    }
}
