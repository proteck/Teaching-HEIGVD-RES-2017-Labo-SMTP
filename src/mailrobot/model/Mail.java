package mailrobot.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Represent a mail with a group (sender & receiver) and the content
 *
 * @author Maxime Guillod
 */
public class Mail {

    private final Group group;
    private final Content content;

    public Mail(Group victim, Content content) {
        this.group = victim;
        this.content = content;
    }

    public String getSender() {
        return group.getSender();
    }

    public Queue<String> getReceiver() {
        return group.getReceiver();
    }

    public ArrayList<String> getContent() throws IOException {
        return content.getContent();
    }

    public String getSubject() {
        return content.getSubject();
    }
}
