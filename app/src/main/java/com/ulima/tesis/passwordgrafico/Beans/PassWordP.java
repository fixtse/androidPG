package com.ulima.tesis.passwordgrafico.Beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fixt on 09/06/17.
 */

public class PassWordP implements Serializable {
    private List<Integer> preal,densidad,pos;

    public List<Integer> getPreal() {
        return preal;
    }

    public void setPreal(List<Integer> preal) {
        this.preal = preal;
    }

    public List<Integer> getDensidad() {
        return densidad;
    }

    public void setDensidad(List<Integer> densidad) {
        this.densidad = densidad;
    }

    public List<Integer> getPos() {
        return pos;
    }

    public void setPos(List<Integer> pos) {
        this.pos = pos;
    }
}
