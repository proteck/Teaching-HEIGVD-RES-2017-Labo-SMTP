package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    public ArrayList<String> getContent() throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        ArrayList<String> content = new ArrayList<>();
        
        while ((line = in.readLine()) != null) {
            content.add(line);
        }
        return content;
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
