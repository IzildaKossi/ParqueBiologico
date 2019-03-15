/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.factory;

import pt.ips.pa.parquebiologico.model.Percurso;
import pt.ips.pa.parquebiologico.tads.*;
import pt.ips.pa.parquebiologico.ui.*;

/**
 *
 * @author
 */
public interface IMapa<V, E> {

    public int getMapaId();

    public Graph<V, E> getMyGraph();

    public void calcularPercurso() throws InvalidEdgeException;

    public GraphDraw getGraphDraw();

    public Percurso getPercurso();

    public void setPercurso(Percurso percurso);
}
