/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.factory;

import java.util.*;
import java.util.logging.Logger;
import pt.ips.pa.parquebiologico.dao.*;
import pt.ips.pa.parquebiologico.model.Conexao;
import pt.ips.pa.parquebiologico.model.Percurso;
import pt.ips.pa.parquebiologico.model.Ponto;
import pt.ips.pa.parquebiologico.tads.*;
import pt.ips.pa.parquebiologico.ui.GraphDraw;

/**
 *
 * @author
 */
public abstract class Mapa implements IMapa {

    private Graph<Ponto, Conexao> myGraph;
    private GraphDraw graphDraw;
    private MapaDAOSerialization mapaDAO;
    private int mapaId;
    private Percurso percurso;

    public Mapa(int mapaId) {
        this.mapaId = mapaId;
        this.myGraph = new GraphLinked<>();
        this.mapaDAO = new MapaDAOSerialization();
        this.graphDraw = new GraphDraw(myGraph);
        this.percurso = null;
    }

    @Override
    public Graph<Ponto, Conexao> getMyGraph() {
        return myGraph;
    }

    public MapaDAOSerialization getMapaDAO() {
        return mapaDAO;
    }

    @Override
    public int getMapaId() {
        return mapaId;
    }

    @Override
    public void calcularPercurso() throws InvalidEdgeException {
        if (percurso != null) {
            ArrayList<Conexao> conexoes = percurso.dijkstra();//executa o dijkstra;

            for (Conexao conexao : conexoes) {
                for (Edge<Conexao, Ponto> edge : myGraph.edges()) {
                    if (((edge.element().getPontoOrigem().getNome().equalsIgnoreCase(conexao.getPontoOrigem().getNome()))
                            && (edge.element().getPontoDestino().getNome().equalsIgnoreCase(conexao.getPontoDestino().getNome())))
                            || ((edge.element().getPontoOrigem().getNome().equalsIgnoreCase(conexao.getPontoDestino().getNome()))
                            && (edge.element().getPontoDestino().getNome().equalsIgnoreCase(conexao.getPontoOrigem().getNome())))) {

                        System.out.println("\nEgde: " + edge.element().getPontoOrigem().getNome() + " - " + edge.element().getPontoDestino().getNome());
                        System.out.println("Conexao: " + conexao.getPontoOrigem().getNome() + " - " + conexao.getPontoDestino().getNome());
                        edge.element().setSelected(true);
                        edge.element().isSelected();
                    }
                }
            }
        }
        graphDraw = new GraphDraw(myGraph);
        graphDraw.setPrefSize(500, 500);
        graphDraw.setId("pane");
    }

    @Override
    public GraphDraw getGraphDraw() {
        return graphDraw;
    }

    @Override
    public Percurso getPercurso() {
        return percurso;
    }

    @Override
    public void setPercurso(Percurso percurso) {
        this.percurso = percurso;
    }

}
