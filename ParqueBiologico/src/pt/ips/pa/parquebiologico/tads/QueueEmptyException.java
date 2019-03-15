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
public class QueueEmptyException extends Exception {

    public QueueEmptyException() {
        super("The Queue is Empty!");
    }

    public QueueEmptyException(String message) {
        super(message);
    }
}
