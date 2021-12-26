package remora.remora.Common;

import java.io.IOException;

public class Cli {
    public static Boolean exec(String command, String args) {
        try {
            Process process = Runtime.getRuntime().exec(String.format(command, args));
            process.waitFor();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
