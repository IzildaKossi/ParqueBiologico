/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.tads;

/**
 *
 * @author
 */
public interface List<E> {

    public int size();

    public boolean isEmpty();

    public E get(int r) throws OutofBoundsException;

    public void add(int r, E elem) throws OutofBoundsException;

    public E remove(int r) throws OutofBoundsException;

    public E set(int r, E elem) throws OutofBoundsException;

}
