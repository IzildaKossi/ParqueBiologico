/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import pt.ips.pa.parquebiologico.dao.MapaDAOSerialization;
import pt.ips.pa.parquebiologico.model.Conexao;
import pt.ips.pa.parquebiologico.model.Ponto;
import pt.ips.pa.parquebiologico.tads.Graph;
import pt.ips.pa.parquebiologico.tads.GraphLinked;
import pt.ips.pa.parquebiologico.tads.InvalidEdgeException;
import pt.ips.pa.parquebiologico.tads.Vertex;
import pt.ips.pa.parquebiologico.ui.GraphDraw;

/**
 *
 * @author
 */
public class Mapa1 extends Mapa {

    public Mapa1(int mapaId) {
        super(mapaId);
        desenharParqueBiologico();
    }

    @Override
    public int getMapaId() {
        return super.getMapaId();
    }

    @Override
    public Graph<Ponto, Conexao> getMyGraph() {
        return super.getMyGraph();
    }

    @Override
    public MapaDAOSerialization getMapaDAO() {
        return super.getMapaDAO();
    }

    private void desenharParqueBiologico() {
        //lista para obter todos pontos no mapa.dat pelo id
        ArrayList<Ponto> pontos = getMapaDAO().loadAllPontoMapa(String.valueOf(getMapaId()));
        Collections.sort(pontos, new Comparator<Ponto>() {
            @Override
            public int compare(Ponto p1, Ponto p2) {
                if (p1.getId() > p2.getId()) {
                    return 1;
                } else if (p1.getId() < p2.getId()) {
                    return -1;
                }
                return 0;
            }

        });

        //lista para obter todas as conexoes no mapa.dat pelo id
        ArrayList<Conexao> conexoes = getMapaDAO().loadAllConexaoMapa(String.valueOf(getMapaId()));

        //criar os pontos
        for (Ponto ponto : pontos) {
            getMyGraph().insertVertex(ponto);
        }

        //criar as conexoes
        for (Conexao conexao : conexoes) {
            getMyGraph().insertEdge(conexao.getPontoOrigem(), conexao.getPontoDestino(), conexao);
        }
    }

    @Override
    public void calcularPercurso() throws InvalidEdgeException {
        super.calcularPercurso();
    }

    @Override
    public GraphDraw getGraphDraw() {
        return super.getGraphDraw();
    }

}
