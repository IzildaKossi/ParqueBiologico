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
public class Data {

    private int dia;
    private int mes;
    private int ano;

    public Data() {
        this(0, 0, 0);
    }

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public static Data obterDataSistema() {
        Calendar dataSistema = Calendar.getInstance();
        int dia = dataSistema.get(Calendar.DAY_OF_MONTH);
        int mes = (dataSistema.get(Calendar.MONTH) + 1);
        int ano = dataSistema.get(Calendar.YEAR);
        Data data = new Data(dia, mes, ano);
        return data;
    }

    public static Data stringToData(String data) {
        StringTokenizer st = new StringTokenizer(data, "/");
        int dia, mes, ano;
        dia = Integer.parseInt(st.nextToken());
        mes = Integer.parseInt(st.nextToken());
        ano = Integer.parseInt(st.nextToken());
        return new Data(dia, mes, ano);
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%02d", dia, mes, ano);
    }

}
