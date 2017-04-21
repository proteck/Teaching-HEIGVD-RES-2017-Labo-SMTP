package main;

/**
 *
 * @author Maxime
 */
public class Mail {

    private Group group;
    private Content content;

    public Mail(Group victim, Content content) {
        this.group = victim;
        this.content = content;
    }
}
