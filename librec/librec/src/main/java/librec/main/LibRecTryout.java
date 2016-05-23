package librec.main;

import librec.util.Logs;

public class LibRecTryout {

    public static void main(String[] args) throws Exception {
        // config logger
        Logs.config("librec/src/main/resources/log4j.xml", true);
        final long startTime = System.currentTimeMillis();
        String configFile = "librec/src/main/resources/librec.conf";
        LibRec librec = new LibRec();
        librec.setConfigFiles(configFile);
        librec.execute(args);
        final long elapsedTimeMillis = System.currentTimeMillis() - startTime;
        System.out.println("took " + elapsedTimeMillis + " ms");
    }

}
