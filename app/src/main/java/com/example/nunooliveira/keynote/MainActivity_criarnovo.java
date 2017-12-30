package com.example.nunooliveira.keynote;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_criarnovo extends AppCompatActivity {

    protected Button btncriar;
    protected AutoCompleteTextView nomeaplicacao;
    protected EditText utilizador;
    protected EditText email;
    protected EditText password;
    protected AdaptadorBaseDados adb;
    protected Cursor cursor;
    protected Intent intent;
    protected int _id;

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
        setContentView(R.layout.activity_criarnovo);

        nomeaplicacao = (AutoCompleteTextView) findViewById(R.id.nomeaplicacao);
        utilizador = (EditText) findViewById(R.id.utilizador);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        btncriar = (Button) findViewById(R.id.criar);

        new ViewAsyncGenerator(btncriar, "ibizaclubpt.com", "a030502/index.xml", 443, nomeaplicacao, getApplicationContext()).execute(0);

        btncriar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(nomeaplicacao.getText().toString().equals("") || utilizador.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast toast = Toast.makeText(MainActivity_criarnovo.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Boolean resultado = adb.insereRegisto(nomeaplicacao.getText().toString(), utilizador.getText().toString(), email.getText().toString(), password.getText().toString());

                    if (resultado) {
                        Toast toast = Toast.makeText(MainActivity_criarnovo.this, "Inserido com sucesso!", Toast.LENGTH_LONG);
                        toast.show();

                        Intent i=new Intent (MainActivity_criarnovo.this,MainActivity.class);
                        startActivity (i);


                    } else {
                        Toast toast = Toast.makeText(MainActivity_criarnovo.this, "Ocorreu um erro. Tente novamente!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
    }
}

