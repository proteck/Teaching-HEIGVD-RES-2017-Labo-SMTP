package main;

import java.io.File;
import java.util.LinkedList;

/**
 *
 * @author Maxime Guillod
 */
public class Content {

    String folderPath = null;
    File file = null;

    public Content(String folderPath) {
        this.folderPath = folderPath;
        file = getAleaFile();
    }

    public String getSubject() {
        return file.getName().replace(".txt", "");
    }

    public String[] getAleaContent() {
        // TODO
        return null;
    }

    private File getAleaFile() {
        LinkedList<File> listFile = new LinkedList<>();
        File folder = new File(folderPath);

        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                listFile.add(file);
            }
        }
        return listFile.get((int) (Math.random() * listFile.size()));
    }

}
