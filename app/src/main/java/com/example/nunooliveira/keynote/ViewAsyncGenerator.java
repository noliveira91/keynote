package com.example.nunooliveira.keynote;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nuno Oliveira on 30/12/2017.
 */

public class ViewAsyncGenerator extends AsyncTask<Integer, Double, String> {
    protected double percentage;
    protected Button b;
    protected AutoCompleteTextView t;
    protected String savedLabel;
    protected int port;
    protected String host;
    protected String path;
    protected boolean ignorar;
    protected Context mContext;

    public ViewAsyncGenerator(Button botao, String host, String path, int port, AutoCompleteTextView  t, Context context) {
        b = botao;
        this.host = host;
        this.path = path;
        this.port = port;
        this.t = t;
        this.mContext = context;
    }
    @Override // runs on the GUI thread
    protected void onPreExecute() {
        ignorar = false;
        savedLabel = b.getText().toString();
        b.setText("A carregar ... 0%");
        b.setEnabled(false);
    }
    @Override // runs on its own thread
    protected String doInBackground(Integer... args) {
        if (ignorar)
            return "";
        return Comunicar.contactar2(host, path, port);
    }
    @Override // runs on the GUI thread
    protected void onProgressUpdate(Double... percentComplete) {
        String theText;
        percentage = percentComplete[0];
        theText = "" + (int)(percentage * 100) + "%";
        b.setText(theText);
    }
    @Override // runs on the GUI thread
    protected void onPostExecute(String s) {
        List<Aplicacao> listaCategorias = null;
        listaCategorias = new ArrayList<>();


        b.setText(savedLabel);
        b.setEnabled(true);

        ArrayList<String> aLista = new ArrayList<String>(10);
        try {

            SaxXmlParser<Aplicacao, SaxXmlAplicacaoHandler> oMeuParser =
                    new SaxXmlParser<Aplicacao, SaxXmlAplicacaoHandler>();
            oMeuParser.setHandler(new SaxXmlAplicacaoHandler());

            listaCategorias = oMeuParser.parse(new StringReader(s));
        } catch (Exception e) {
            aLista.add(e.toString());  // devolve a excepção obtida
        }

        String osIds = "";
        Aplicacao umaAplicacao = null;

        String[] cat = new String[listaCategorias.size()];
        for (int k = 0; k < listaCategorias.size(); ++k) {

            umaAplicacao = listaCategorias.get(k);
            aLista.add(umaAplicacao.getAplicacao() + "\n");
            osIds = osIds + umaAplicacao.getAplicacao()+"\n";
            cat[k] =umaAplicacao.getAplicacao();
        }

        s = osIds;
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_dropdown_item_1line, cat);
        t.setThreshold(1);
        t.setAdapter(itemsAdapter);
    }
}
