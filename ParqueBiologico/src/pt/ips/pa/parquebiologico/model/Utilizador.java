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
public class Utilizador {

    private String nome;
    private int nif;
    private int id;

    //novo utilizador com nif
    public Utilizador(String nome, int nif) {
        this.id = 0;
        this.nome = nome;
        this.nif = nif;
    }

    //utilizador da DB
    public Utilizador(int id, String nome, int nif) {
        this.id = id;
        this.nome = nome;
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public int getNif() {
        return nif;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Utilizador{" + "nome=" + nome + ", nif=" + nif + ", id=" + id + '}';
    }

}
