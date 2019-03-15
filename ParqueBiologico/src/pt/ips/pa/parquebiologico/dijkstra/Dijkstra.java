/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.dijkstra;

import java.util.*;
import pt.ips.pa.parquebiologico.tads.Edge;
import pt.ips.pa.parquebiologico.tads.Graph;
import pt.ips.pa.parquebiologico.tads.Vertex;

/**
 *
 * @author
 */
public class Dijkstra<V, E> {

    //To store a distance from each of the vertices to the vertex source.
    private Map<Vertex<V>, Integer> distances;
    //To store a cost from each of the vertices to the vertex source.
    private Map<Vertex<V>, Integer> cost;
    //To save the predecessor vertex on the shortest path
    private Map<Vertex<V>, Vertex<V>> path;
    //To save visited vertices
    private Set<Vertex<V>> visited;
    private String resultadoPercurso;

    //Construtor
    public Dijkstra() {
        this.distances = new HashMap<>();
        this.cost = new HashMap<>();
        this.path = new HashMap<>();
        this.visited = new HashSet<>();
    }

    public void executeDijkstra(Graph<V, E> graph, Vertex<V> origin, Vertex<V> destiny) {
        //execute dijkstra
        dijkstra(graph, origin);

        String strAux = destiny.element().toString();
        //need to return the 
        while (destiny != origin) {
            destiny = path.get(destiny);
            strAux = destiny.element().toString().concat(" -> ").concat(strAux);
        }
        resultadoPercurso = strAux;
        System.out.println("\nstrAux3: " + strAux);
    }

    //Dijkstra (graph, origin)
    private void dijkstra(Graph<V, E> graph, Vertex<V> origin) {
        //Q list of vertex
        ArrayList<Vertex<V>> Q = new ArrayList<>();
        //for each vertex v in the graph
        for (Vertex<V> v : graph.vertices()) {
            //vertex v diferent vertex origin
            if (v.element().equals(origin.element()) != true) {
                //distance [v] = Infinite;
                distances.put(v, Integer.MAX_VALUE);
            }//vertex v equal vertex origin
            else {
                //distance [source] = 0;
                distances.put(origin, 0);
            }
            //Q = all vertices of graph;
            Q.add(v);
        }

        //while (Q is not empty) do,
        while (Q.isEmpty() != true) {
            //remove u from Q;
            Vertex<V> u = remove(Q);
            //u = vertex is graph with less distance;
            for (Edge<E, V> edge : graph.incidentEdges(u)) {
                //for e
                Vertex<V> v = graph.opposite(u, edge);
                int d = distances.get(u) + 1;
                if (d < distances.get(v)) {
                    distances.put(v, d);
                    path.put(v, u);
                }
            }
        }
    }

    //remove u from Q;
    private Vertex<V> remove(ArrayList<Vertex<V>> fila) {//Q = Q - {v}
        if (fila.isEmpty()) {
            return null;
        }
        int idx = 0;
        int min = distances.get(fila.get(0));
        for (int i = 1; i < fila.size(); ++i) {
            int dist = distances.get(fila.get(i));
            if (dist < min) {
                idx = i;
                min = dist;
            }
        }
        return fila.remove(idx);
    }

    public Map<Vertex<V>, Integer> getDistances() {
        return distances;
    }

    public void setDistances(Map<Vertex<V>, Integer> distances) {
        this.distances = distances;
    }

    public Map<Vertex<V>, Vertex<V>> getPath() {
        return path;
    }

    public void setPath(Map<Vertex<V>, Vertex<V>> path) {
        this.path = path;
    }

    public Set<Vertex<V>> getVisited() {
        return visited;
    }

    public void setVisited(Set<Vertex<V>> visited) {
        this.visited = visited;
    }

    public String getResultadoPercurso() {
        return resultadoPercurso;
    }

    public void setResultadoPercurso(String resultadoPercurso) {
        this.resultadoPercurso = resultadoPercurso;
    }

}
