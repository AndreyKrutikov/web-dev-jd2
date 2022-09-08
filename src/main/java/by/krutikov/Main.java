package by.krutikov;

import by.krutikov.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
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
//
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.scan("by.krutikov");
//        context.refresh();
//        UserRepository repository = context.getBean("jdbcTemplateUserRepository", JdbcTemplateUserRepository.class);
//
////        User sasha = repository.create(User.builder()
////                .name("alexander")
////                .surname("krutikov")
////                .dateOfBirth(new Timestamp(new Date(1988, 3, 13).getTime()))
////                .created(new Timestamp(new Date(2020, 1, 1).getTime()))
////                .modified(new Timestamp(new Date(2020,1,1).getTime()))
////                .isDeleted(false)
////                .rating((byte)1)
////                .build());
////        logger.info(sasha);
//
//        repository.findByFullName("sasha_modified", "krutikov").forEach(logger::info);

        Account account = Account
                .builder()
                .build();
        logger.info(account);



    }
}