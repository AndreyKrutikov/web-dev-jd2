package by.krutikov;

import by.krutikov.entity.User;
import by.krutikov.repository.user.UserRepository;
import by.krutikov.repository.user.impl.UserRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class Main{
    static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
//        User user = new User();
//
//        user.setName("alesya");
//        user.setSurname("hiryat");
//        user.setDateOfBirth(new Timestamp(new Date().getTime()));
//        user.setCreated(new Timestamp(new Date().getTime()));
//        user.setModified(new Timestamp(new Date().getTime()));
//        user.setDeleted(false);
//        user.setRating((byte) 1);
        List <User> all;
        UserRepository repository = new UserRepositoryImpl();
        all = repository.findByFullName("alesya", "hiryat");

        System.out.println(all.isEmpty());

        all.forEach(System.out::println);

    }
}

class Main2 implements Runnable {
    static final Logger logger = LogManager.getLogger(Main2.class);

    @Override
    public void run() {
        for (int i = 0; i < 8; i++) {
            logger.info("running 2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }
}