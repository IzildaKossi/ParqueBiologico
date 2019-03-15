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
public class QueueFullException extends Exception {

    public QueueFullException() {
        super("The Queue is Full!");
    }

    public QueueFullException(String message) {
        super(message);
    }
}
