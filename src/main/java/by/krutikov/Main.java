package by.krutikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements Runnable{
    static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        new Thread(new Main()).start();
        new Thread(new Main2()).start();
    }
    @Override
    public void run() {
        for(int i =0; i<8 ; i++){
            logger.info("running 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }
}
class Main2 implements Runnable{
    static final Logger logger = LogManager.getLogger(Main2.class);
    @Override
    public void run() {
        for(int i =0; i<8 ; i++){
            logger.info("running 2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }
}