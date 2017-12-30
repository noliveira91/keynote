package com.example.nunooliveira.keynote;

import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

/**
 * Created by Nuno Oliveira on 30/12/2017.
 */

public class MyXmlListHandler<E> extends DefaultHandler {
    protected List<E> osElementos;
    public List<E> obterElementos() {
        return osElementos;
    }
}
