package by.krutikov;

import by.krutikov.repository.account.AccountRepository;
import by.krutikov.repository.account.AccountRepositoryImpl;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.krutikov");
        context.refresh();
        AccountRepository repository = context.getBean(AccountRepositoryImpl.class);

        repository.findAll().forEach(System.out::println);

    }
}