package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Maxime Guillod
 */
public class Connection extends Socket implements ICommunictionSmtp {

    public static final String SEND_FORM = " >>> ";
    public static final String RECV_FORM = " <<< ";

    private final BufferedReader in;
    private final BufferedWriter out;

    public Connection(String host, int port) throws IOException {
        super(host, port);
        out = new BufferedWriter(
                new OutputStreamWriter(getOutputStream()));
        in = new BufferedReader(
                new InputStreamReader(getInputStream()));
        readLine();
    }

    public void send(Mail mail) throws IOException {
        send(HELLO);
        readLine();
        readLine();
        readLine();

        while (!mail.getReceiver().isEmpty()) {
            send(MAIL_FROM + mail.getSender());
            readLine();
            send(MAIL_RCP + mail.getReceiver().peek());
            readLine();
            send(DATA);
            readLine();
            send(HEADER_FROM + mail.getSender());
            send(HEADER_TO + mail.getReceiver().poll());
            send(HEADER_SUBJECT + mail.getSubject());
            send(HEADER_END);
            for (String line : mail.getContent()) {
                send(line);
            }
            send(END_DATA);
            readLine();
        }

    }

    private void send(String content) throws IOException {
        out.write(content + "\r\n");
        out.flush();
        System.out.println(SEND_FORM + content);
    }

    private void readLine() throws IOException {
        System.out.println(RECV_FORM + in.readLine());
    }

}
