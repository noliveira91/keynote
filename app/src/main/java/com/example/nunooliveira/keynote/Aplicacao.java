package com.example.nunooliveira.keynote;

/**
 * Created by Nuno Oliveira on 30/12/2017.
 */

public class Aplicacao {
    protected String apl;

    public Aplicacao(String nome) {
        apl = nome;
    }
    public Aplicacao() {
        apl = null;
    }
    public void setAplicacao(String nome) {
        apl = nome;
    }
    public String getAplicacao() {
        return apl;
    }

    @Override
    public String toString() {
        return apl;
    }
}
