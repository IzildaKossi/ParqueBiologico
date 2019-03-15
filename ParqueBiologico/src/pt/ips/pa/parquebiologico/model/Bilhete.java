/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.model;

import pt.ips.pa.parquebiologico.factory.IMapa;
import pt.ips.pa.parquebiologico.factory.Mapa1;
import java.util.*;
import pt.ips.pa.parquebiologico.dao.*;
import pt.ips.pa.parquebiologico.tads.*;

/**
 *
 * @author
 */
public class Bilhete {

    /*
     O bilhete
deverá conter informação sobre o tipo de percurso selecionado, uma descrição detalhada do
mesmo, indicando todas as conexões a percorrer e a distancia das mesmas, assim como o valor total
do percurso e a data e hora de emissão do mesmo.
     */
    private Random rd = new Random();
    private int numBilhete;
    private IMapa<Ponto, Conexao> mapa;
    private Data data;
    private Hora hora;
    private Fatura fatura;
    private ParqueBiologicoDAOSQLite utilizadorDAOSQLite;
    private int mapaId;
    private int bilheteId;
    private Utilizador utilizador;
    private boolean nifNaFatura;
    private String percurso;

    //novo bilhete com nif para fatura
    public Bilhete(Utilizador utilizador, IMapa mapa, String percurso, boolean nifNaFatura) {
        this.numBilhete = rd.nextInt(100);
        this.mapaId = mapa.getMapaId();
        this.data = Data.obterDataSistema();
        this.hora = Hora.obterHoraSistema();
        this.utilizador = utilizador;
        this.nifNaFatura = nifNaFatura;
        this.percurso = percurso;
        if (this.nifNaFatura != true) {//sem nif na fatura
            this.fatura = new Fatura();
        } else {//com nif na fatura
            this.fatura = new Fatura(this.utilizador.getNif());
        }
    }

    //bilhete da DB
    public Bilhete(int bilheteId, int numBilhete, int mapaId, String data, String hora, int utilizadorNif, ParqueBiologicoDAOSQLite utilizadorDAOSQLite) {
        this.bilheteId = bilheteId;
        this.numBilhete = numBilhete;
        this.mapaId = mapaId;
        this.mapa = new Mapa1(mapaId);
        this.data = Data.stringToData(data);
        this.hora = Hora.stringToHora(hora);
        this.utilizadorDAOSQLite = utilizadorDAOSQLite;
        this.utilizador = (Utilizador) utilizadorDAOSQLite.select("utilizador", utilizadorNif);
    }

    @Override
    public String toString() {
        String str = String.format("\n --- BILHETE Nº %06d ---", numBilhete);
        str += "\nData de Emissao: " + data;
        str += "\nHora de Emissao: " + hora;
        str += "\nNome Utilizador: " + utilizador.getNome();
        str += "\nNif  Utilizador: " + utilizador.getNif();
        str += "\nPercurso: " + percurso;

        if (fatura != null) {
            str += fatura;
        }
        return str;
    }

    public int getNumBilhete() {
        return numBilhete;
    }

    public IMapa<Ponto, Conexao> getMapa() {
        return mapa;
    }

    public Data getData() {
        return data;
    }

    public Hora getHora() {
        return hora;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public ParqueBiologicoDAOSQLite getUtilizadorDAOSQLite() {
        return utilizadorDAOSQLite;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public int getMapaId() {
        return mapaId;
    }

    public int getBilheteId() {
        return bilheteId;
    }

    public String getPercurso() {
        return percurso;
    }

    public void setPercurso(String percurso) {
        this.percurso = percurso;
    }

}
