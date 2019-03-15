/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.tads;

/**
 *
 * @author 
 * @param <E>
 */
public interface Iterator<E> {
    public boolean hasNext();
    public E next();
}
