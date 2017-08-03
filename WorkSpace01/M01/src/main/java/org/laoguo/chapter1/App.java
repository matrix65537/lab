package org.laoguo.chapter1;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class App 
{
    public static void main( String[] args )
    {
        Logger logger = Logger.getLogger(App.class);
        PropertyConfigurator.configure("c:\\work\\matrix65537\\lab\\trunk\\WorkSpace01\\M01\\log4j.properties");
        logger.setLevel(Level.DEBUG);
        for(int i = 0; i < 100; ++i){
            logger.info("info" + i);
            logger.debug("debug" + i);
            logger.warn("warn" + i);
        }
    }
}
