package com.example.nunooliveira.keynote;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity_consultar extends AppCompatActivity {

    protected Button btneditar;
    protected Button btnapagar;
    protected TextView nomeaplicacao;
    protected TextView utilizador;
    protected TextView email;
    protected TextView password;
    protected AdaptadorBaseDados adb;
    protected Cursor cursor;
    protected Intent intent;
    protected Integer _id;

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
        setContentView(R.layout.activity_consultar);

        nomeaplicacao = (TextView)findViewById(R.id.nomeaplicacao);
        utilizador = (TextView)findViewById(R.id.utilizador);
        email = (TextView)findViewById(R.id.email);
        password = (TextView)findViewById(R.id.password);


        intent = getIntent();
        _id = intent.getExtras().getInt("_id");

        adb = new AdaptadorBaseDados(this).open();
        cursor = adb.obterRegistosId(_id);
        if (cursor.moveToFirst()) {
            nomeaplicacao.setText(cursor.getString(1));
            utilizador.setText(cursor.getString(2));
            email.setText(cursor.getString(3));
            password.setText(cursor.getString(4));
        }



        btneditar = (Button) findViewById(R.id.editar);
        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (MainActivity_consultar.this,MainActivity_editar.class);
                i.putExtra("_id", _id);
                startActivity (i);
            }
        });

        btnapagar = (Button) findViewById(R.id.apagar);
        btnapagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adb.apagarRegisto(_id);
                Toast toast = Toast.makeText(MainActivity_consultar.this, "Apagado com sucesso!", Toast.LENGTH_LONG);
                toast.show();

                Intent i=new Intent (MainActivity_consultar.this,MainActivity.class);
                startActivity (i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent (MainActivity_consultar.this,MainActivity.class);
        startActivity (intent);
        finish();
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle outputState) {
        if (_id!=null) {
            outputState.putInt("_id", _id);
        }
        super.onSaveInstanceState(outputState);
    }

    protected void restoreVarsFromBundle(Bundle savedInstanceState) {
        Integer index = savedInstanceState.getInt("_id");
        if (index != 0)
            _id=index;
    }
}
