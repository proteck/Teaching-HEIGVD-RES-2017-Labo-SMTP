package main;

import java.io.IOException;

/**
 *
 * @author Maxime Guillod
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // INIT
        Content content = new Content("content/");
        Emails emails = new Emails("mails.txt");
        Group g1 = emails.pollGroup(4);
        Connection connection = new Connection("localhost", 25);
        
        Mail mail = new Mail(g1, content);
        
        connection.send(mail);
        
        // CLOSE ALL
        connection.close();
    }
}
