/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.model;

import java.text.*;
import java.util.*;

/**
 *
 * @author
 */
public class Hora {

    private int hora;
    private int minuto;

    public Hora() {
        this(0, 0);
    }

    public Hora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    public static Hora obterHoraSistema() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date horaSistema = Calendar.getInstance().getTime();
        String dataFormatada = sdf.format(horaSistema);
        int horas = Integer.valueOf(dataFormatada.charAt(0) + "" + dataFormatada.charAt(1));
        int minutos = Integer.valueOf(dataFormatada.charAt(3) + "" + dataFormatada.charAt(4));
        Hora hora = new Hora(horas, minutos);
        return hora;
    }

    public static Hora stringToHora(String hora) {
        StringTokenizer st = new StringTokenizer(hora, ":");
        int horas, minutos;
        horas = Integer.parseInt(st.nextToken());
        minutos = Integer.parseInt(st.nextToken());
        return new Hora(horas, minutos);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hora, minuto);
    }

}
