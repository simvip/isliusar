package monitore;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Sliusar on 31.10.2017.
 * Red Line Soft corp.
 */
public class ParallerSearch {
    /**
     * Lock status.
     */
    private Object lock = new Object();

    /**
     * Result.
     */
    private List<String> result = new ArrayList<>();


    /**
     * Search file.
     *
     * @param root String
     * @param text String
     * @param exts List
     */
    public void searchFiles(String root, String text, List<String> exts) {

        File startFolder = new File(root);
        if (startFolder.exists()) {
            for (String ext : exts) {
                FilenameFilter filenameFilter = (dir, name) -> name.endsWith(ext);
                Thread thread = new Thread(() -> {
                    processFilesFromFolder(startFolder, text, filenameFilter);
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(this.result);
        }
    }

    private void processFilesFromFolder(File folder, String text, FilenameFilter filter) {

        File[] folderEntries = folder.listFiles(filter);

        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(entry, text, filter);
                continue;
            }

            if (fileContainsSearhcPhrase(entry, text)) {
                synchronized (this.lock) {
                    this.result.add(entry.getName());
                }
            }
        }
    }

    /**
     * Finde phrase in file.
     *
     * @param file   File
     * @param phrase String
     * @return boolean
     */
    private boolean fileContainsSearhcPhrase(File file, String phrase) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(phrase.toString())) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


}
