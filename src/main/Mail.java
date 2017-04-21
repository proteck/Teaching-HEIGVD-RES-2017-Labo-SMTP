/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Maxime
 */
public class Mail {

    private String sender = null;
    private String receiver = null;
    private String messsage = null;

    public Mail(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.messsage = message;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMesssage() {
        return messsage;
    }

    public void send() {
        // TODO
    }

}
