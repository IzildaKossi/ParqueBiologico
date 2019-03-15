/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.dao;

import java.util.*;
import pt.ips.pa.parquebiologico.model.*;

/**
 *
 * @author
 */
public interface ParqueBiologicoDAO {

    public List<Object> selectAll(String nome);

    public Object select(String nomeTabela, Object object);

    public boolean insert(String nomeTabela, Object object);

    public boolean remove(String nomeTabela, Object object);

    public boolean update(String nomeTabela, Object object);

}
