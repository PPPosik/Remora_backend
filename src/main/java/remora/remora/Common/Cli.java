package remora.remora.Common;

public class Cli {
    public static Boolean exec(String command, String args) {
        if (command.toLowerCase().contains("mock")) {
            return true;
        }

        try {
            Process process = Runtime.getRuntime().exec(command + " " + args);
            process.waitFor();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
