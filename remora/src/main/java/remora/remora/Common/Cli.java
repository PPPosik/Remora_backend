package remora.remora.Common;

import java.io.IOException;

public class Cli {
    public static Boolean exec(String command, String args) {
        try {
            Runtime.getRuntime().exec(String.format(command, args));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
