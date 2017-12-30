package com.example.nunooliveira.keynote;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;

/**
 * Created by Nuno Oliveira on 30/12/2017.
 */

public class SaxXmlAplicacaoHandler extends MyXmlListHandler<Aplicacao> {
    private String tempValue;
    private Aplicacao tempAplicacao;
    public SaxXmlAplicacaoHandler() {
        osElementos = new ArrayList<Aplicacao>();
    }
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        tempValue = "";
        if (qName.equalsIgnoreCase("item"))
            tempAplicacao = new Aplicacao();


    }
    @Override
    public void characters(char[] ch, int start, int end) {
        tempValue = new String(ch, start, end);
    }
    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            tempAplicacao.setAplicacao(tempValue);
            osElementos.add(tempAplicacao);
        }
    }
}

