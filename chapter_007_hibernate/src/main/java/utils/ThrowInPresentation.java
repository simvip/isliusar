package utils;

import org.apache.log4j.Logger;

/**
 * Created by Ivan Sliusar on 23.05.2018.
 * Red Line Soft corp.
 */
public class ThrowInPresentation extends Exception{
    private static final Logger LOGGER = Logger.getLogger(LogFilter.class);
    public ThrowInPresentation() {
        LOGGER.error(this);

    }
    public ThrowInPresentation(String message) {
        LOGGER.error(message);

    }

}
