/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.model;

/**
 *
 * @author
 */
public class Config {

    private int mapaId;
    private Ponto ponto1;
    private Ponto ponto2;
    private String trajeto;
    private String caminho;

    public Config(int mapaId, Ponto ponto1, Ponto ponto2, String trajeto, String caminho) {
        this.mapaId = mapaId;
        this.ponto1 = ponto1;
        this.ponto2 = ponto2;
        this.trajeto = trajeto;
        this.caminho = caminho;
    }

    @Override
    public String toString() {
        return "Config{" + "mapaId=" + mapaId + ", ponto1=" + ponto1.getNome() + ", ponto2=" + ponto2.getNome() + ", trajeto=" + trajeto + ", custo=" + caminho + '}';
    }

    public int getMapaId() {
        return mapaId;
    }

    public void setMapaId(int mapaId) {
        this.mapaId = mapaId;
    }

    public Ponto getPonto1() {
        return ponto1;
    }

    public void setPonto1(Ponto ponto1) {
        this.ponto1 = ponto1;
    }

    public Ponto getPonto2() {
        return ponto2;
    }

    public void setPonto2(Ponto ponto2) {
        this.ponto2 = ponto2;
    }

    public String getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(String trajeto) {
        this.trajeto = trajeto;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    
}
