package mailrobot.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import mailrobot.Launcher;

/**
 * List of all mails adresse store in a file
 *
 * @author Maxime Guillod
 */
public class Emails {

    private final Queue<String> emails;

    public Emails(String loadPath) {
        this.emails = new LinkedBlockingDeque<>();
        List<String> tmpMail = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(loadPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                tmpMail.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException ex) {
            System.err.println("Error i/o");
        }

        // on randomise pour l'ajout dans notre liste
        Collections.shuffle(tmpMail);
        tmpMail.forEach((email) -> {
            emails.add(email);
        });
    }

    public int getNumberMails() {
        return emails.size();
    }

    /**
     *
     * @return
     */
    public Group pollGroup() {
        return pollGroup(Launcher.DEFAULT_SIZE_GROUP);
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
