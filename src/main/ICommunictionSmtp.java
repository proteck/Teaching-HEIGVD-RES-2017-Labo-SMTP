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
public interface ICommunictionSmtp {

    public static final String HELLO = "EHLO Maxime";
    public static final String MAIL_FROM = "MAIL FROM: ";
    public static final String MAIL_RCP = "RCPT TO: ";
    public static final String DATA = "DATA";
    public static final String END_DATA = ".";

    public static final String HEADER_FROM = "From:";
    public static final String HEADER_TO = "To:";
    public static final String HEADER_SUBJECT = "Subject:";
    public static final String HEADER_END = "";
}
