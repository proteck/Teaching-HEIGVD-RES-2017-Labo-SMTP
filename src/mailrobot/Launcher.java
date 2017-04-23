package mailrobot;

import mailrobot.model.Mail;
import mailrobot.model.Emails;
import mailrobot.model.Content;
import mailrobot.model.Group;
import mailrobot.communication.Connection;
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
    public static final int DEFAULT_SIZE_GROUP = 5;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // INIT
        Content content = new Content(CONTENT_PATH);
        Emails emails = new Emails(MAILS_PATH);

        try (Connection connection = new Connection(SERVER_ADDR, SERVER_PORT)) {
            Mail mail;
            Group group;
            while (true) {
                // select a new group
                group = emails.pollGroup();
                if (!group.isValide()) {
                    break;
                }
                // update the content
                content.update();
                // init the mail
                mail = new Mail(group, content);
                // send it
                connection.send(mail);
                
                // Avoid to receive many mail in the same seconde
                Thread.sleep(200);
            }
            // CLOSE ALL
            connection.close();
        }
    }
}
