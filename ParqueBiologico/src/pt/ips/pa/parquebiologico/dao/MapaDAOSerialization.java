/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import pt.ips.pa.parquebiologico.model.*;

/**
 *
 * @author
 */
public class MapaDAOSerialization implements ParqueBiologicoDAO {

    public MapaDAOSerialization() {
    }

    @Override
    public List<Object> selectAll(String nomeTabela) {
        return null;
    }

    @Override
    public Object select(String mapaId, Object object) {
        ArrayList<Ponto> pontos = loadAllPontoMapa(mapaId);
        if (object instanceof Integer) {
            int pontoId = (Integer) object;
            for (Ponto ponto : pontos) {
                if (ponto.getId() == pontoId) {
                    return ponto;
                }
            }
        }
        return null;
    }

    @Override
    public boolean insert(String nomeTabela, Object object) {
        return false;
    }

    @Override
    public boolean remove(String nomeTabela, Object object
    ) {

        return false;
    }

    @Override
    public boolean update(String nomeTabela, Object object
    ) {

        return false;
    }

    public ArrayList<Ponto> loadAllPontoMapa(String mapaId) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        File file = new File("mapa" + mapaId + ".dat");
        if (!file.exists()) {
            System.out.println(file.getName() + " does not exist");
            return pontos;
        } else {
            try {
                Scanner scnr = new Scanner(file);
                //trim para remover espaço
                int totalPontos = Integer.valueOf(scnr.nextLine().trim());
                int i = 0;
                while (scnr.hasNextLine() && i < totalPontos) {
                    //Lê a linha com o nextLine e a divide os campos com split
                    String[] campos = scnr.nextLine().split(",");
                    //trim para remover espaço
                    int pontoId = Integer.valueOf(campos[0].trim());
                    String nomePonto = campos[1].trim();
                    Ponto ponto = new Ponto(pontoId, nomePonto);
                    pontos.add(ponto);//guarda na lista
                    i++;
                }
                return pontos;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                Logger.getLogger(MapaDAOSerialization.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return pontos;
    }

    public ArrayList<Conexao> loadAllConexaoMapa(String mapaId) {
        ArrayList<Conexao> conexoes = new ArrayList<>();
        Ponto ponto1 = null;
        Ponto ponto2 = null;
        File file = new File("mapa" + mapaId + ".dat");
        if (!file.exists()) {
            System.out.println(file.getName() + " does not exist");
            return conexoes;
        } else {
            try {
                Scanner scnr = new Scanner(file);
                //trim para remover espaço
                int totalPontos = Integer.valueOf(scnr.nextLine().trim());
                int i = 0;
                while (scnr.hasNextLine() && i < totalPontos) {
                    //le para passar os dados de cada ponto
                    scnr.nextLine();
                    i++;
                }
                if (i == totalPontos) {
                    int j = 0;
                    //trim para remover espaço
                    int totalConexoes = Integer.valueOf(scnr.nextLine().trim());
                    while (scnr.hasNextLine() && j < totalConexoes) {
                        //Lê a linha com o nextLine e a divide os campos com split
                        String[] campos = scnr.nextLine().split(",");
                        //trim para remover espaço
                        int conexaoId = Integer.valueOf(campos[0].trim());
                        String tipo = campos[1].trim();
                        String nomeConexao = campos[2].trim();
                        int pontoId1 = Integer.valueOf(campos[3].trim());
                        int pontoId2 = Integer.valueOf(campos[4].trim());
                        boolean navegabilidade = Boolean.parseBoolean(campos[5].trim());//boolean
                        int custo = Integer.valueOf(campos[6].trim());
                        int distancia = Integer.valueOf(campos[7].trim());
                        if ((select(mapaId, pontoId1) != null)
                                && (select(mapaId, pontoId2) != null)) {
                            ponto1 = (Ponto) select(mapaId, pontoId1);
                            ponto2 = (Ponto) select(mapaId, pontoId2);
                        }
                        Conexao conexao = new Conexao(conexaoId, tipo, nomeConexao, ponto1, ponto2, navegabilidade, custo, distancia);
                        conexoes.add(conexao);//guarda na lista
                        j++;
                    }
                }
                return conexoes;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                Logger.getLogger(MapaDAOSerialization.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return conexoes;
    }

    public void saveAll(String mapaId) {
        try {
            FileOutputStream fileOut = new FileOutputStream("mapa" + mapaId + ".dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            switch (mapaId) {
                case "1":
                    out.writeObject("");//mapa1.dat
                    break;
                case "2":
                    out.writeObject("");//mapa1.dat
                    break;
                case "3":
                    out.writeObject("");//mapa1.dat
                    break;
            }
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, e.getMessage());
        }
    }
}
