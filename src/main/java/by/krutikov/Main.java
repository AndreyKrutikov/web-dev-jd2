package by.krutikov;

import by.krutikov.entity.User;
import by.krutikov.repository.user.UserRepository;
import by.krutikov.repository.user.impl.UserRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
//        List <User> all;
//        UserRepository repository = new UserRepositoryImpl();
//        all = repository.findByFullName("alesya", "hiryat");
//
//        System.out.println(all.isEmpty());
//
//        all.forEach(System.out::println);
//        all.forEach(logger::info);

//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
//                "application-context.xml");
//
//        User user = applicationContext.getBean("user", User.class);
//
//        System.out.println(user);
//
//        applicationContext.close();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("by.krutikov");
        //context.refresh();
        UserRepository repository = context.getBean("userRepositoryImpl", UserRepository.class);

        for (User u : repository.findAll()) {
            System.out.println(u);
        }
    }
}