package by.krutikov.controller;

import by.krutikov.controller.requests.AccountCreateRequest;
import by.krutikov.controller.requests.AccountFindOffsetLimitRequest;
import by.krutikov.controller.requests.AccountUpdateRequest;
import by.krutikov.entity.Account;
import by.krutikov.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts") //localhost:8080/test-project/accounts
public class AccountController {
    static final Logger log = Logger.getLogger(AccountController.class);
    private final AccountService accountService;

    @GetMapping
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllAccounts() {
        return new ResponseEntity<>(Collections.singletonMap("result", accountService.findAll()), HttpStatus.OK);
        // return Collections.singletonMap("result", userService.findAll()); Another way to transfer result to JSON out
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findAllOffsetLimit(@ModelAttribute AccountFindOffsetLimitRequest findLimitOffsetRequest) {
        int verifiedOffset = Integer.parseInt(findLimitOffsetRequest.getOffset());
        int verifiedLimit = Integer.parseInt(findLimitOffsetRequest.getLimit());

        List<Account> accounts = accountService.findAll(verifiedOffset, verifiedLimit);
        Map<String, List<Account>> model = Collections.singletonMap("result", accounts);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findAccountById(@PathVariable String id) {
        long idVerified = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("result", accountService.findById(idVerified)), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAccountById(@PathVariable String id) {
        long idVerified = Long.parseLong(id);

        accountService.delete(idVerified);
        return new ResponseEntity<>(Collections.singletonMap("result", accountService.findAll()), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Object> createAccount(@RequestBody AccountCreateRequest createRequest) {
        Account account = new Account();
        account.setLogin(createRequest.getLogin());
        account.setPassword(createRequest.getPassword());
        account.setEmail(createRequest.getEmail());

        accountService.create(account);

        List<Account> accounts = accountService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("account created", account.getLogin());
        model.put("accounts", accounts);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody AccountUpdateRequest updateRequest,
                                                @PathVariable String id) {
        long idVerified = Long.parseLong(id);

        Account account = accountService.findById(idVerified);
        account.setLogin(updateRequest.getLogin());
        account.setPassword(updateRequest.getPassword());
        account.setEmail(updateRequest.getEmail());

        accountService.update(account);

        List<Account> accounts = accountService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("updated account", account.getLogin());
        model.put("accounts", accounts);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }
}
