package mailrobot.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import mailrobot.model.Mail;

/**
 * Connection which extends Sockets and add the posibility to send mail
 *
 * @author Maxime Guillod
 */
public class MySocket extends Socket implements ISmtp {

    public static final String SEND_FORM = " >>> ";
    public static final String RECV_FORM = " <<< ";

    private final BufferedReader in;
    private final BufferedWriter out;

    public MySocket(String host, int port) throws IOException {
        super(host, port);
        out = new BufferedWriter(
                new OutputStreamWriter(getOutputStream()));
        in = new BufferedReader(
                new InputStreamReader(getInputStream()));
        readLine();
    }

    /**
     * Send a mail with the smtp protocole
     *
     * @param mail
     * @throws IOException
     */
    public void send(Mail mail) throws IOException {
        // Init connection
        send(CMD_HELLO);
        readLine();
        readLine();
        readLine();

        while (!mail.getReceiver().isEmpty()) {
            /*
             * SEND MAIL
             */
            // Param for the server
            send(CMD_MAIL_FROM + mail.getSender());
            readLine();
            send(CMD_MAIL_RCP + mail.getReceiver().peek());
            readLine();
            send(CMD_DATA);
            readLine();
            // Header
            send(HEADER_FROM + mail.getSender());
            send(HEADER_TO + mail.getReceiver().poll());
            send(HEADER_SUBJECT + mail.getSubject());
            send(HEADER_END);
            // Content
            for (String line : mail.getContent()) {
                send(line);
            }
            // End Content & send mail
            send(CMD_END_DATA);
            readLine();
        }

    }

    /**
     *
     * @param content
     * @throws IOException
     */
    private void send(String content) throws IOException {
        out.write(content + "\r\n");
        out.flush();
        System.out.println(SEND_FORM + content);
    }

    /**
     * Print the input
     *
     * @throws IOException
     */
    private void readLine() throws IOException {
        System.out.println(RECV_FORM + in.readLine());
    }

}
