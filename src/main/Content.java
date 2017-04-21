package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maxime Guillod
 */
public final class Content {

    String folderPath = null;
    ArrayList<String> content;
    File file = null;

    public Content(String folderPath) throws IOException {
        this.folderPath = folderPath;
        this.content = new ArrayList<>();
        update();
    }

    /**
     * Update the file and the content. In other words, we take another alea content
     */
    public void update() throws IOException {
        updateFile();
        updateContent();
    }

    public String getSubject() {
        return file.getName().replace(".txt", "");
    }

    public ArrayList<String> getContent() {
        return content;
    }
    
    /**
     * Update the content
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void updateContent() throws FileNotFoundException, IOException {
        content.clear();

        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file)));

        while ((line = in.readLine()) != null) {
            content.add(line);
        }
    }

    /**
     * Select a new file alea
     */
    private void updateFile() {
        List<File> listFile = new ArrayList<>();
        File folder = new File(folderPath);

        for (File currentFile : folder.listFiles()) {
            if (currentFile.isFile()) {
                listFile.add(currentFile);
            }
        }
        // select one of the file into the list
        file = listFile.get((int) (Math.random() * listFile.size()));
    }

}
