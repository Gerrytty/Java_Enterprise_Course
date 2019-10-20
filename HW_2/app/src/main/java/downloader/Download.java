package downloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Download {
    private String[] args;


    public Download(String... args) {
        this.args = args;
    }


   public void download() {
        if (netIsAvailable()) {

            if(args.length != 0) {
                System.out.println("Start downloading");
                String pathToSaveFile = createFolder();

                for (String arg : args) {
                    FileDownloadingThread thread = new FileDownloadingThread(arg, pathToSaveFile);
                    thread.run();
                }
            }

            else {
                System.out.println("Empty args");
            }

        }

        else {
            System.out.println("\u001B[31m" + "NO INTERNET" + "\u001B[0m");
        }

    }

    private boolean netIsAvailable() {

        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }


    /***
     * creating folder "downloads" in current directory for saving files
     *
     * @return path to this folder
     ***/
    private String createFolder() {
        String currentPath = System.getProperty("user.dir");
        String pathToSaveFile = currentPath + "/downloads";

        File file = new File(pathToSaveFile);
        if(!file.isDirectory()) {
            file.mkdir();
        }

        return pathToSaveFile;
    }

}
