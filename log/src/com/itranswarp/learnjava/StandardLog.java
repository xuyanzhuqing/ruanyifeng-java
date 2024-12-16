package com.itranswarp.learnjava;

import java.util.logging.Logger;

// jdk 自带 log 库

public class StandardLog {
    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");
    }
}
