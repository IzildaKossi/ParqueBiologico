/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.factory;

/**
 *
 * @author
 */
public class MapaFactory {

    public static Mapa desenharMapa(String mapaId) {
        switch (mapaId) {
            case "Mapa 1":
                return new Mapa1(1);//mapa1.dat
            case "Mapa 2":
                return new Mapa2(2);//mapa2.dat
            case "Mapa 3":
                return new Mapa3(3);//mapa3.dar
        }
        return null;
    }

}
