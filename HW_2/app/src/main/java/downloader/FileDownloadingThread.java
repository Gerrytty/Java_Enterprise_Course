package downloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileDownloadingThread extends Thread {
    // colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private String pathFromDownload;
    private String pathToSave;

    protected FileDownloadingThread(String pathFromDowloand, String pathToSave) {
        this.pathFromDownload = pathFromDowloand;
        this.pathToSave = pathToSave;
    }

    @Override
    public void run() {
        download(pathFromDownload, pathToSave);
    }

    private void download(String sourceURL, String targetDirectory) {

        try {
            URL url = new URL(sourceURL);
            String fileName = createFileName(sourceURL);
            Path targetPath = new File(targetDirectory + File.separator + fileName).toPath();
            Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println(ANSI_GREEN + "File from " + sourceURL + " saved to " + targetDirectory + " as "
                    + fileName + ANSI_RESET);

        } catch (IOException e) {
            System.out.println(ANSI_RED + "Incorrect path " + sourceURL + " file not saved" + ANSI_RESET);
        }

    }


    /***
     * creating unique fileName
     * if fileName is already exists return fileName_1
     * if fileName_1 is already exists return fileName_2
     ***/

    private String createFileName(String sourceURL) {
        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1);
        File file = new File(fileName);
        String s;

        if(file.exists()) {

            String name = fileName.substring(0, fileName.indexOf("."));
            String extension = fileName.substring(fileName.indexOf("."));
            char c = name.charAt(name.length() - 1);

            if(isNum(c)) {
                int n = Integer.parseInt("" + c);
                s = name.substring(0, name.lastIndexOf(String.valueOf(n))) + (n + 1) + extension;
            }
            else {
                s = name + "_1" + extension;
            }

            return createFileName(s);

        }

        else {
            return fileName;
        }

    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

}