import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) {

        if(args.length == 0) {
            System.out.println("Empty args");
            System.exit(0);
        }

        if (!netIsAvailable()) {
            System.out.println("\u001B[31m" + "NO INTERNET" + "\u001B[0m");
            System.exit(0);
        }

        System.out.println("Start dowloanding");
        String pathToSaveFile = createFolder();

        for (String arg : args) {
            FileDowloandingThread thread = new FileDowloandingThread(arg, pathToSaveFile);
            thread.run();
        }

    }

    private static boolean netIsAvailable() {

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
     * creating folder "dowloands" in current directory for saving files
     *
     * @return path to this folder
     ***/
    private static String createFolder() {
        String currentPath = System.getProperty("user.dir");
        String pathToSaveFile = currentPath + "/dowloands";

        File file = new File(pathToSaveFile);
        if(!file.isDirectory()) {
            file.mkdir();
        }

        return pathToSaveFile;
    }

}