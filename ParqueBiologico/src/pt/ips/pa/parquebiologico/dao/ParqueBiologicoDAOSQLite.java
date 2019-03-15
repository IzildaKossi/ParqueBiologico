/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import pt.ips.pa.parquebiologico.model.*;

/**
 *
 * @author
 */
public class ParqueBiologicoDAOSQLite implements ParqueBiologicoDAO {

    private static final String DB_FILE = "ParqueBiologicoDB.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;

    public ParqueBiologicoDAOSQLite() {
        strutureCreate();
    }

    @Override
    public List<Object> selectAll(String nomeTabela) {
        LinkedList<Object> list = new LinkedList<>();
        String sql = "";
        switch (nomeTabela) {
            case "utilizador":
                sql = "select * from utilizador";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        Utilizador utilizador = new Utilizador(rs.getInt("utilizadorId"),
                                rs.getString("nome"),
                                rs.getInt("nif"));
                        list.add(utilizador);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "bilhete":
                sql = "select * from bilhete";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Bilhete bilhete = new Bilhete(rs.getInt("bilheteId"),
                                rs.getInt("numBilhete"),
                                rs.getInt("mapaId"),
                                rs.getString("data"),
                                rs.getString("hora"),
                                rs.getInt("utilizadorId"),
                                this);
                        list.add(bilhete);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "conexao":
                sql = "select * from conexao";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Conexao conexao = new Conexao(rs.getInt("conexaoId"),
                                new Ponto(rs.getString("pontoOrigem")),
                                new Ponto(rs.getString("pontoDestino")),
                                rs.getInt("percursoId"));
                        list.add(conexao);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        return list;
    }

    @Override
    public Object select(String nomeTabela, Object object) {
        String sql = "";
        switch (nomeTabela) {
            case "utilizador":
                sql = "select * from utilizador where nome=? and nif=?";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {

                    if (object instanceof Utilizador) {
                        Utilizador utilizador = (Utilizador) object;
                        pstmt.setString(1, utilizador.getNome());
                        pstmt.setInt(2, utilizador.getNif());
                    }
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }
                    Utilizador utilizadorDB = new Utilizador(rs.getInt("utilizadorId"),
                            rs.getString("nome"),
                            rs.getInt("nif"));
                    return utilizadorDB;
                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "bilhete":
                sql = "select * from bilhete where utilizadorId=?";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {

                    if (object instanceof Integer) {
                        int utilizadorNif = (Integer) object;
                        pstmt.setInt(1, utilizadorNif);
                    }
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }
                    Bilhete bilhete = new Bilhete(rs.getInt("bilheteId"),
                            rs.getInt("numBilhete"),
                            rs.getInt("mapaId"),
                            rs.getString("data"),
                            rs.getString("hora"),
                            rs.getInt("utilizadorId"),
                            this);
                    return bilhete;
                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "conexao":
                sql = "select * from conexao where pontoOrigem=? and pontoDestino=?";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {

                    if (object instanceof Conexao) {
                        System.out.println("2");
                        Conexao conexao = (Conexao) object;
                        System.out.println("21");
                        pstmt.setString(1, conexao.getPontoOrigem().getNome());
                        System.out.println("22");
                        pstmt.setString(2, conexao.getPontoDestino().getNome());
                        System.out.println("23");
                    }
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        System.out.println("24");
                        return null;
                    }
                    Conexao conexao = new Conexao(rs.getInt("conexaoId"),
                            new Ponto(rs.getString("pontoOrigem")),
                            new Ponto(rs.getString("pontoDestino")),
                            rs.getInt("percursoId"));
                    System.out.println("25");
                    return conexao;
                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "percurso":
                sql = "select * from percurso where nomePercurso=?";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {

                    if (object instanceof Percurso) {
                        Percurso percurso = (Percurso) object;
                        pstmt.setString(1, percurso.getNome());
                    }
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }
                    Percurso percurso = new Percurso(rs.getInt("percursoId"),
                            rs.getString("nomePercurso"));
                    return percurso;
                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        return null;
    }

    @Override
    public boolean insert(String nomeTabela, Object object) {
        String sql = "";
        switch (nomeTabela) {
            case "utilizador":
                sql = "insert into utilizador(nome,nif) values (?,?)";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {
                    if (object instanceof Utilizador) {
                        Utilizador utilizador = (Utilizador) object;
                        if (select("utilizador", utilizador) != null) {
                            return false;
                        }
                        pstmt.setString(1, utilizador.getNome());
                        pstmt.setInt(2, utilizador.getNif());
                        pstmt.execute();
                        return true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "bilhete":
                sql = "insert into bilhete(numBilhete,mapaId,data,hora,utilizadorId) values (?,?,?,?,?)";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {
                    if (object instanceof Bilhete) {
                        Bilhete bilhete = (Bilhete) object;

                        pstmt.setInt(1, bilhete.getNumBilhete());
                        pstmt.setInt(2, bilhete.getMapaId());
                        pstmt.setString(3, bilhete.getData().toString());
                        pstmt.setString(4, bilhete.getHora().toString());
                        pstmt.setInt(5, bilhete.getUtilizador().getId());
                        pstmt.execute();
                        return true;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "percurso":
                sql = "insert into percurso(nomePercurso) values (?)";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {
                    if (object instanceof Percurso) {
                        Percurso percurso = (Percurso) object;
                        if (select("percurso", percurso) != null) {
                            return false;
                        }
                        pstmt.setString(1, percurso.getNome());
                        pstmt.execute();
                        return true;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "conexao":
                sql = "insert into conexao(pontoOrigem,pontoDestino,percursoId) values (?,?,?)";

                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {
                    if (object instanceof Conexao) {
                        Conexao conexao = (Conexao) object;
                        if (select("conexao", conexao) != null) {
                            return false;
                        }
                        pstmt.setString(1, conexao.getPontoOrigem().getNome());
                        pstmt.setString(2, conexao.getPontoDestino().getNome());
                        pstmt.setInt(3, conexao.getPercurso().getPercursoId());
                        pstmt.execute();
                        return true;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        return false;
    }

    @Override
    public boolean remove(String nomeTabela, Object object) {
        String sql = "";
        switch (nomeTabela) {
            case "conexao":
                sql = "delete from conexao where pontoOrigem=? and pontoDestino=?";
                try (Connection sqlConnection = connect();
                        PreparedStatement pstmt = sqlConnection.prepareStatement(sql)) {
                    if (object instanceof Conexao) {
                        Conexao conexao = (Conexao) object;
                        if (select("conexao", conexao) != null) {
                            return false;
                        }
                        pstmt.setString(1, conexao.getPontoOrigem().getNome());
                        pstmt.setString(2, conexao.getPontoDestino().getNome());
                        pstmt.execute();
                        return true;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

        }

        return false;
    }

    @Override
    public boolean update(String nomeTabela, Object object) {
        return false;
    }

    private void strutureCreate() {
        // SQL statement for creating a new table
        String sqlUtilizador = "CREATE TABLE IF NOT EXISTS utilizador(\n"
                + " utilizadorId INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " nome TEXT NOT NULL, \n"
                + " nif INTEGER NOT NULL)";

        // SQL statement for creating a new table
        String sqlBilhete = "CREATE TABLE IF NOT EXISTS bilhete(\n"
                + " bilheteId INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " numBilhete INTEGER text NOT NULL, \n"
                + " mapaId INTEGER NOT NULL, \n"
                + " data text NOT NULL, \n"
                + " hora text NOT NULL, \n"
                + " utilizadorId INTEGER NOT NULL, \n"
                + " FOREIGN KEY (utilizadorId) REFERENCES utilizador (utilizadorId))";

        String sqlPercurso = "CREATE TABLE IF NOT EXISTS percurso(\n"
                + " percursoId INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " nomePercurso text NOT NULL)";

        String sqlConexao = "CREATE TABLE IF NOT EXISTS conexao(\n"
                + " conexaoId INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " pontoOrigem text NOT NULL, \n"
                + " pontoDestino text NOT NULL, \n"
                + " percursoId INTEGER NOT NULL, \n"
                + " FOREIGN KEY (percursoId) REFERENCES percurso (percursoId))";

        try (Connection sqlConnection = connect(); Statement stmt = sqlConnection.createStatement()) {
            // create a new table
            stmt.execute(sqlUtilizador);
            stmt.execute(sqlBilhete);
            stmt.execute(sqlPercurso);
            stmt.execute(sqlConexao);

        } catch (SQLException ex) {
            Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection connect() {
        Connection connection = null;
        try {
            Properties properties = new Properties();
            properties.setProperty("PRAGMA foreign_keys", "ON");
            connection = DriverManager.getConnection(URL, properties);
        } catch (SQLException ex) {
            Logger.getLogger(ParqueBiologicoDAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

}
