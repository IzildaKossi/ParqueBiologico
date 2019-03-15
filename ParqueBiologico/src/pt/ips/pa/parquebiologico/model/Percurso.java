/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.model;

import java.util.ArrayList;
import java.util.Map;
import pt.ips.pa.parquebiologico.dijkstra.Dijkstra;
import pt.ips.pa.parquebiologico.tads.Edge;
import pt.ips.pa.parquebiologico.tads.Vertex;

/**
 *
 * @author
 */
public class Percurso {

    public static int nextNumber = 1;
    private Config config;
    private String caminho;
    private int custoTotal;
    private int distanciaTotal;
    private ParqueBiologico parqueBiologico;
    private String nome;
    private int percursoId;

    public Percurso(ParqueBiologico parqueBiologico, Config config) {
        this.nome = "Percurso" + (nextNumber++);
        this.parqueBiologico = parqueBiologico;
        this.config = config;
        this.caminho = "";
        this.custoTotal = 0;
        this.distanciaTotal = 0;
    }

    public Percurso(int percursoId, String nome) {
        this.percursoId = percursoId;
        this.nome = nome;
    }

    public ArrayList<Conexao> dijkstra() {
        //insere o caminho na base de dados
        parqueBiologico.getParqueBiologicoDAO().insert("percurso", this);

        ArrayList<Conexao> conexoes = new ArrayList<>();

        Iterable<Vertex<Ponto>> lista = parqueBiologico.getMapa().getMyGraph().vertices();
        for (Vertex<Ponto> v1 : lista) {
            if (v1.element().equals(config.getPonto1())) {
                for (Vertex<Ponto> v2 : lista) {
                    if (v2.element().equals(config.getPonto2())) {

                        Dijkstra<Ponto, Conexao> dijkstra = new Dijkstra();
                        dijkstra.executeDijkstra(parqueBiologico.getMapa().getMyGraph(), v1, v2);
                        caminho = dijkstra.getResultadoPercurso();
                        String[] vertices = caminho.split("->");

                        for (String vertice1 : vertices) {
                            for (String vertice2 : vertices) {
                                if (vertice1.equals(vertice2) != true) {
                                    Percurso percursoAux = (Percurso) parqueBiologico.getParqueBiologicoDAO().select("percurso", this);
                                    Conexao conexao = new Conexao(new Ponto(vertice1.trim()), new Ponto(vertice2.trim()));
                                    conexao.setPercurso(percursoAux);
                                    int distancia = 0;
                                    //para obter os distancia
                                    for (Map.Entry<Vertex<Ponto>, Integer> par : dijkstra.getDistances().entrySet()) {
                                        if (par.getKey().element().getNome().equals(conexao.getPontoOrigem().getNome())) {
                                            System.out.println("Distancia: " + par.getKey().element().getNome() + ": " + par.getValue());
                                            //obter a soma da distancia
                                            distancia += par.getValue();
                                        }
                                    }
                                    distanciaTotal = distancia;
                                    custoTotal = distanciaTotal;
                                    conexoes.add(conexao);
                                    parqueBiologico.getParqueBiologicoDAO().insert("conexao", conexao);
                                }
                            }
                        }
                        return conexoes;
                    }
                }
            }
        }
        return null;
    }

    public int getPercursoId() {
        return percursoId;
    }

    public void setPercursoId(int percursoId) {
        this.percursoId = percursoId;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public int getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(int custoTotal) {
        this.custoTotal = custoTotal;
    }

    public int getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(int distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public ParqueBiologico getParqueBiologico() {
        return parqueBiologico;
    }

    public void setParqueBiologico(ParqueBiologico parqueBiologico) {
        this.parqueBiologico = parqueBiologico;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
