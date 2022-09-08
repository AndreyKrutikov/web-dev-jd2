package by.krutikov.controller;

import by.krutikov.controller.requests.UserCreateRequest;
import by.krutikov.controller.requests.UserSearchRequest;
import by.krutikov.entity.User;
import by.krutikov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/users")
public class UserRestController {
    static final Logger log = LogManager.getLogger(UserRestController.class);
    private final UserService userService;

    @GetMapping
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllUsers() {
        return new ResponseEntity<>(Collections.singletonMap("result", userService.findAll()), HttpStatus.OK);
        // return Collections.singletonMap("result", userService.findAll()); Another way to transfer result to JSON out
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findByFullName(@ModelAttribute UserSearchRequest searchRequest) {
        List<User> users = userService.findByFullName(searchRequest.getName(), searchRequest.getSurname());
        Map<String, List<User>> model = Collections.singletonMap("result", users);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest createRequest) {
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

        Map<String, Object> model = new HashMap<>();
        model.put("user", "New user created: " + user.getName());
        model.put("users", users);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findUserById(@PathVariable String id) {
        long idVerified = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("user", userService.findById(idVerified)), HttpStatus.OK);
    }



}
