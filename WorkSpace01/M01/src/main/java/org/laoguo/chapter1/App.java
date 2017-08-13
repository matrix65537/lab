package org.laoguo.chapter1;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

class A1{
	
}

class A2 extends A1{
	
}

public class App 
{
    public static void main( String[] args )
    {
        Logger logger = Logger.getLogger(App.class);
        PropertyConfigurator.configure("c:\\work\\matrix65537\\lab\\trunk\\WorkSpace01\\M01\\src\\main\\resources\\log4j.properties");
        logger.setLevel(Level.DEBUG);
        for(int i = 0; i < 1; ++i){
            logger.info("info" + i);
            logger.debug("debug" + i);
            logger.warn("warn" + i);
        }
        logger.info(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println(A2.class.isAssignableFrom(A2.class));
        System.out.println(A1.class.isAssignableFrom(A2.class));
    }
}
