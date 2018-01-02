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

public class MainActivity_editar extends AppCompatActivity {

    protected Button btnguardar;
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
        setContentView(R.layout.activity_editar);

        nomeaplicacao = (AutoCompleteTextView) findViewById(R.id.nomeaplicacao);
        utilizador = (EditText) findViewById(R.id.utilizador);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        btnguardar = (Button) findViewById(R.id.guardar);

        new ViewAsyncGenerator(btnguardar, "ibizaclubpt.com", "a030502/index.xml", 443, nomeaplicacao, getApplicationContext()).execute(0);

        intent = getIntent();
        _id = intent.getExtras().getInt("_id");

        adb = new AdaptadorBaseDados(this).open();

        cursor = adb.obterRegistosId(_id);

        if(cursor.moveToFirst()){
            nomeaplicacao.setText(cursor.getString(1));
            utilizador.setText(cursor.getString(2));
            email.setText(cursor.getString(3));
            password.setText(cursor.getString(4));
        }



        btnguardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(nomeaplicacao.getText().toString().equals("") || utilizador.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast toast = Toast.makeText(MainActivity_editar.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Boolean resultado = adb.alteraRegisto(_id, nomeaplicacao.getText().toString(), utilizador.getText().toString(), email.getText().toString(), password.getText().toString());

                    if (resultado) {
                        Toast toast = Toast.makeText(MainActivity_editar.this, "Guardado com sucesso!", Toast.LENGTH_LONG);
                        toast.show();

                        Intent i=new Intent (MainActivity_editar.this,MainActivity_consultar.class);
                        i.putExtra("_id", _id);
                        startActivity (i);


                    } else {
                        Toast toast = Toast.makeText(MainActivity_editar.this, "Ocorreu um erro. Tente novamente!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent (MainActivity_editar.this,MainActivity_consultar.class);
        intent.putExtra("_id", _id);
        startActivity (intent);
        finish();
        super.onBackPressed();
    }
}
