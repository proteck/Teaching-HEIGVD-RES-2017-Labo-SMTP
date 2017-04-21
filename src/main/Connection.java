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
        in = new BufferedReader(
                new InputStreamReader(getInputStream()));
        out = new BufferedWriter(
                new OutputStreamWriter(getOutputStream()));
    }

    public void send(Mail mail) throws IOException {
        send("HELO world");
        readLine();
    }

    private void send(String content) throws IOException {
        out.write(content + '\n');
        out.flush();
    }

    private void readLine() throws IOException {
        System.out.println(in.readLine());
    }

}
