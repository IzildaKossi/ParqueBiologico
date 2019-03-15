/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.model;

import java.io.*;
import pt.ips.pa.parquebiologico.ui.DrawableGraphElement;

/**
 *
 * @author
 */
public class Conexao implements DrawableGraphElement, Comparable<Conexao>, Serializable {

    private int conexaoId;
    private String tipo;
    private String conexao;
    private Ponto pontoOrigem;
    private Ponto pontoDestino;
    private boolean navegabilidade;
    private int custo;
    private int distancia;
    private boolean selected;
    private Percurso percurso;
    private int percursoId;

    public Conexao(int conexaoId, String tipo, String conexao, Ponto pontoOrigem, Ponto pontoDestino, boolean navegabilidade, int custo, int distancia) {
        this.conexaoId = conexaoId;
        this.tipo = tipo;
        this.conexao = conexao;
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
        this.navegabilidade = navegabilidade;
        this.custo = custo;
        this.distancia = distancia;
        this.selected = false;
        this.percurso = null;
    }

    //conexao para percuso e BD
    public Conexao(Ponto pontoOrigem, Ponto pontoDestino) {
        this.conexaoId = 0;
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
        this.selected = false;
        this.percurso = null;
    }

    //conexao de BD para lista de percurso
    public Conexao(int conexaoId, Ponto pontoOrigem, Ponto pontoDestino, int idPercurso) {
        this.conexaoId = conexaoId;
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
        this.selected = false;
        this.percurso = null;
        this.percursoId = idPercurso;
    }

    @Override
    public int getId() {
        return conexaoId;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    public void toggleSelect() {
        //this.selected = this.selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return conexao;
    }

    public String getNome() {
        return String.format("{%s,%s,%s,%d,%d}", conexao, pontoOrigem.getNome(), pontoDestino.getNome(), custo, distancia);
    }

    public String getTipo() {
        return tipo;
    }

    public Ponto getPontoOrigem() {
        return pontoOrigem;
    }

    public void setPontoOrigem(Ponto pontoOrigem) {
        this.pontoOrigem = pontoOrigem;
    }

    public Ponto getPontoDestino() {
        return pontoDestino;
    }

    public void setPontoDestino(Ponto pontoDestino) {
        this.pontoDestino = pontoDestino;
    }

    public boolean isNavegabilidade() {
        return navegabilidade;
    }

    public int getCusto() {
        return custo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNavegabilidade(boolean navegabilidade) {
        this.navegabilidade = navegabilidade;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getConexaoId() {
        return conexaoId;
    }

    public void setConexaoId(int conexaoId) {
        this.conexaoId = conexaoId;
    }

    public String getConexao() {
        return conexao;
    }

    public void setConexao(String conexao) {
        this.conexao = conexao;
    }

    public Percurso getPercurso() {
        return percurso;
    }

    public void setPercurso(Percurso percurso) {
        this.percurso = percurso;
    }

    @Override
    public int compareTo(Conexao conexao) {
        if (this.conexaoId < conexao.conexaoId) {
            return -1;
        }
        if (this.conexaoId > conexao.conexaoId) {
            return 1;
        }
        return 0;
    }

    public int getPercursoId() {
        return percursoId;
    }

    public void setPercursoId(int percursoId) {
        this.percursoId = percursoId;
    }

}
