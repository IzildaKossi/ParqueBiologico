/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.model;

import pt.ips.pa.parquebiologico.factory.IMapa;
import java.util.*;
import pt.ips.pa.parquebiologico.dao.ParqueBiologicoDAO;
import pt.ips.pa.parquebiologico.dao.ParqueBiologicoDAOSQLite;
import pt.ips.pa.parquebiologico.factory.*;


/**
 *
 * @author
 */
public class ParqueBiologico extends Observable {

    private IMapa<Ponto, Conexao> mapa;
    private ParqueBiologicoDAO parqueBiologicoDAO;

    public ParqueBiologico() {
        this.parqueBiologicoDAO = new ParqueBiologicoDAOSQLite();
        this.mapa = null;
    }

    public void desenharParqueBiologico(String mapaId) {
        switch (mapaId) {
            case "Mapa 1":
                mapa = MapaFactory.desenharMapa(mapaId);
                break;
            case "Mapa 2":
                mapa = MapaFactory.desenharMapa(mapaId);
                break;
            case "Mapa 3":
                mapa = MapaFactory.desenharMapa(mapaId);
                break;
        }
        mapa.getGraphDraw().setPrefSize(500, 500);
        mapa.getGraphDraw().setId("pane");

    }

    public IMapa<Ponto, Conexao> getMapa() {
        return mapa;
    }

    public ParqueBiologicoDAO getParqueBiologicoDAO() {
        return parqueBiologicoDAO;
    }

    public void setParqueBiologicoDAO(ParqueBiologicoDAO parqueBiologicoDAO) {
        this.parqueBiologicoDAO = parqueBiologicoDAO;
    }

}
