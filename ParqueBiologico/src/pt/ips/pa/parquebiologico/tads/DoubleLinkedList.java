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
public class DoubleLinkedList<E> implements List<E> {

    private Node<E> header;
    private Node<E> trailer;
    private int size;

    public DoubleLinkedList() {
        this.header = new Node(null, null, null);
        this.trailer = new Node(null, header, null);
        this.header.setNext(trailer);
        this.size = 0;
    }

    /**
     * retornar o nó “cauda” (trailer)
     *
     */
    private Node<E> nodeAtRank(int r) throws OutofBoundsException {
        Node<E> nodeAux = header.getNext();
        for (int i = 0; i < r; i++) {
            nodeAux = nodeAux.getNext();
        }
        return nodeAux;
    }

    @Override
    public int size() {
        return size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int r) throws OutofBoundsException {
        if (r < 0 || r >= size) {
            throw new OutofBoundsException(r);
        }
        return nodeAtRank(r).getElement();
    }

    @Override
    public void add(int r, E elem) throws OutofBoundsException {//A inserção é sempre feita antes do nó que possui a posição rank.
        if (r < 0 || r > size) {
            throw new OutofBoundsException(r);
        }
        Node<E> aux = nodeAtRank(r);
        Node<E> prev = aux.getPrevious();
        Node<E> newNode = new Node<>(elem, prev, aux);
        aux.setPrevious(newNode);
        prev.setNext(newNode);
        size++;
    }

    @Override
    public E remove(int r) throws OutofBoundsException {
        if (r < 0 || r >= size) {
            throw new OutofBoundsException(r);
        }
        Node<E> aux = nodeAtRank(r);
        E elem = aux.getElement();
        Node<E> prev = aux.getPrevious();
        Node<E> next = aux.getNext();
        prev.setNext(next);
        next.setPrevious(prev);
        size--;
        return elem;
    }

    @Override
    public E set(int r, E elem) throws OutofBoundsException {
        if (r < 0 || r >= size) {
            throw new OutofBoundsException(r);
        }
        Node<E> nodeAux = nodeAtRank(r);
        E elemAux = nodeAux.getElement();
        nodeAux.setElement(elem);
        return elemAux;
    }

}
