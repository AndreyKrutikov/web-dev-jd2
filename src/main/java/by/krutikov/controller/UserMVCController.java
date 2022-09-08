package by.krutikov.controller;

import by.krutikov.controller.requests.UserCreateRequest;
import by.krutikov.controller.requests.UserSearchRequest;
import by.krutikov.entity.User;
import by.krutikov.service.UserService;

import static by.krutikov.util.RatingGenerator.generateRating;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

//@Controller
@RequiredArgsConstructor
//@RequestMapping("/users")
public class UserMVCController {
    private final UserService userService;

    @GetMapping("/users")
    public ModelAndView findAllUsers() {
        List<User> users = userService.findAll();

        ModelAndView model = new ModelAndView();
        model.addObject("user", "Andrey");
        model.addObject("users", users);
        model.setViewName("users");

        return model;
    }

    @GetMapping("/users/find")
    public ModelAndView findByFullName(@ModelAttribute UserSearchRequest searchRequest) {
        List<User> users = userService.findByFullName(searchRequest.getName(), searchRequest.getSurname());

        ModelAndView model = new ModelAndView();
        model.addObject("user", "Andrey");
        model.addObject("users", users);
        model.setViewName("users");

        return model;
    }

    @GetMapping("/users/{id}")
    public ModelAndView findUserById(@PathVariable("id") String idString) {
//        parsing necessary for handling wrong path parameter exception
        long userId = Long.parseLong(idString);
        User user = userService.findById(userId);

        ModelAndView model = new ModelAndView();
        model.addObject("userName", user.getName());
        model.addObject("user", user);
        model.setViewName("user");

        return model;
    }

    @PostMapping("/users")
    public ModelAndView addUser(@RequestBody UserCreateRequest createRequest) {
        User user = new User();

        user.setName(createRequest.getName());
        user.setSurname(createRequest.getSurname());
        user.setDateOfBirth(createRequest.getDateOfBirth());
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setModified(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setRating(createRequest.getRating());
        userService.create(user);

        List<User> users = userService.findAll();

        ModelAndView model = new ModelAndView();
        model.addObject("user", "New user created: " + user.getName());
        model.addObject("users", users);
        model.setViewName("users");

        return model;
    }

}
