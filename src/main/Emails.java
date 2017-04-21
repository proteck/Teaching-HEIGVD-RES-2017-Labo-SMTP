package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime Guillod
 */
public class Emails {

    private static final int DEFAULT_SIZE_GROUP = 3;

    private final Queue<String> emails;

    public Emails(String loadPath) {
        this.emails = new LinkedBlockingDeque<>();

        try (BufferedReader br = new BufferedReader(new FileReader(loadPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                emails.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
            Logger.getLogger(Emails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("Error i/o");
            Logger.getLogger(Emails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNumberMails() {
        return emails.size();
    }

    /**
     *
     * @return
     */
    public Group pollGroup() {
        return pollGroup(DEFAULT_SIZE_GROUP);
    }

    /**
     * Return a group mail and remove it form the main list
     *
     * @param groupSize
     * @return
     */
    public Group pollGroup(int groupSize) {
        Queue<String> retour = new LinkedBlockingDeque<>();
        for (int i = 0; i < groupSize; i++) {
            if (!emails.isEmpty()) {
                retour.add(emails.poll());
            } else {
                break;
            }
        }
        return new Group(retour);
    }

}
