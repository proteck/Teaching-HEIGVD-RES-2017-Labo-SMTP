package main;

import java.io.IOException;

/**
 *
 * @author Maxime Guillod
 */
public class Launcher {

    public static final String CONTENT_PATH = "content/";
    public static final String MAILS_PATH = "mails.txt";
    public static final String SERVER_ADDR = "localhost";
    public static final int SERVER_PORT = 25;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // INIT
        Content content = new Content(CONTENT_PATH);
        Emails emails = new Emails(MAILS_PATH);

        Group group;
        Connection connection = new Connection(SERVER_ADDR, SERVER_PORT);

        Mail mail;

        while (true) {
            group = emails.pollGroup(4);
            if (!group.isValide()) {
                break;
            }
            content.update();
            mail = new Mail(group, content);
            connection.send(mail);
        }

        // CLOSE ALL
        connection.close();
    }
}
