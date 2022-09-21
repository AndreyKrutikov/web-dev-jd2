package by.krutikov.controller;

import by.krutikov.repository.accountentity.AccountEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hibernate/accounts")
public class HibernateController {

    private final AccountEntityRepository accountEntityRepository; //TODO refactor this

    @GetMapping
    public ResponseEntity<Object> getAllAccounts() {
        Map<String, Object> model = new HashMap<>();
        model.put("result", accountEntityRepository.findAll());

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("result", accountEntityRepository.findById(id));

        return new ResponseEntity<>(model, HttpStatus.OK);
    }


}
