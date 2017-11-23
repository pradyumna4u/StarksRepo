package com.api.framework;

import org.apache.log4j.Logger;


public class Log {

    private static Logger Log = Logger.getLogger(Log.class.getName());

    public static void startScenario(String scenarioName){
        Log.info("# # # # # # # # Started Scenario : " + scenarioName + "# # # # # # # #");
    }

    public static void endScenario(String scenarioName){
        Log.info("# # # # # # # # Ended Scenario : " + scenarioName + "# # # # # # # #");
    }

    public static void info(String message){
        Log.info(message);
    }

    public static void debug(String message){
        Log.debug(message);
    }

    public static void fatal(String message){ Log.fatal(message); }

}
