package com.ulima.tesis.passwordgrafico.Beans;

import java.io.Serializable;

/**
 * Created by fixt on 11/06/17.
 */

public class Puntos implements Serializable {
    private int px, py;
    private String por;

    public Puntos(int px, int py, String por) {
        this.px = px;
        this.py = py;
        this.por = por;
    }

    public int getPx() {
        return px;
    }

    public Puntos() {
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public String getPor() {
        return por;
    }

    public void setPor(String por) {
        this.por = por;
    }





}
