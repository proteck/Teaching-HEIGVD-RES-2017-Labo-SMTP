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
public class Connection extends Socket {

    BufferedReader in;
    BufferedWriter out;

    public Connection(String host, int port) throws IOException {
        super(host, port);
        out = new BufferedWriter(
                new OutputStreamWriter(getOutputStream()));
        in = new BufferedReader(
                new InputStreamReader(getInputStream()));
        readLine();
    }

    public void send(Mail mail) throws IOException {
        send("EHLO maxime");
        readLine();
        readLine();
        readLine();

        while (!mail.getReceiver().isEmpty()) {
            send("MAIL FROM: " + mail.getSender());
            readLine();
            send("RCPT TO: " + mail.getReceiver().peek());
            readLine();
            send("DATA");
            readLine();
            send("From:" + mail.getSender());
            send("To:" + mail.getReceiver().poll());
            send("Subject:" + mail.getSubject());
            send("");
            for (String line : mail.getContent()) {
                send(line);
            }
            send(".");
            readLine();
        }

    }

    private void send(String content) throws IOException {
        out.write(content + "\r\n");
        out.flush();
        System.out.println(" >>> " + content);
    }

    private void readLine() throws IOException {
        System.out.println(" <<< " + in.readLine());
    }

}
