/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.model;

import java.io.*;
import pt.ips.pa.parquebiologico.tads.Vertex;
import pt.ips.pa.parquebiologico.ui.*;

/**
 *
 * @author
 */
public class Ponto implements DrawableGraphElement, Serializable {

    private int pontoId;
    private String ponto;

    public Ponto(String ponto) {
        this.pontoId = 0;
        this.ponto = ponto;
    }

    public Ponto(int pontoId, String ponto) {
        this.pontoId = pontoId;
        this.ponto = ponto;
    }

    @Override
    public int getId() {
        return pontoId;
    }

    @Override
    public String toString() {
        return ponto;
    }

    public String getNome() {
        return ponto;
    }

    @Override
    public boolean isSelected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSelected(boolean selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
