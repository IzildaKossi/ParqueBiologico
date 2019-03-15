/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.model;

import java.util.*;

/**
 *
 * @author
 */
public class Fatura {

    /*
    A fatura é emitida automaticamente em conjunto com o bilhete, e resulta na criação
de dois documentos PDF (um para o bilhete e outro para a fatura).
     */
    private Random rd = new Random();
    private int numFatura;
    private Data data;
    private Hora hora;
    private int utilizadorNif;

    //Fatura sem nif
    public Fatura() {
        this.utilizadorNif = 0;
        this.numFatura = rd.nextInt(100);
        this.data = Data.obterDataSistema();
        this.hora = Hora.obterHoraSistema();
    }

    //Fatura com nif
    public Fatura(int utilizadorNif) {
        this.utilizadorNif = utilizadorNif;
        this.numFatura = rd.nextInt(100);
        this.data = Data.obterDataSistema();
        this.hora = Hora.obterHoraSistema();
    }

    @Override
    public String toString() {
        String str = String.format("\n --- FATURA Nº %06d ---", numFatura);
        str += "\nData de Emissao: " + data;
        str += "\nHora de Emissao: " + hora;
        if (utilizadorNif == 0) {
            str += "\nNif de Utilizador: N/A";
        } else {
            str += "\nNif de Utilizador: " + utilizadorNif;
        }
        return str;
    }

    public int getNumFatura() {
        return numFatura;
    }

    public int getUtilizadorNif() {
        return utilizadorNif;
    }

    public Data getData() {
        return data;
    }

    public Hora getHora() {
        return hora;
    }

}
