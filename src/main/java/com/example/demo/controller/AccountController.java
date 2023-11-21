package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Account;
import com.example.demo.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private AccountRepo accountsRepo;


    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        try {
            List<Account> accountList = new ArrayList<>();
            accountsRepo.findAll().forEach(accountList::add);
            if(accountList.isEmpty()) {
                return new ResponseEntity<>( HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(accountList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> accountData = accountsRepo.findById(id);

        if(accountData.isPresent()) {
            return new ResponseEntity<>(accountData.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Account>  addAccount(@RequestBody Account accounts) {
        Account accountObj = accountsRepo.save(accounts);

        return new ResponseEntity<>(accountObj, HttpStatus.OK);
    }

    @PostMapping("updateAccountById/{id}")
    public ResponseEntity<Account> updateAccountById(@PathVariable Long id,@RequestBody Account newAccountData ) {
        Optional<Account> oldAccountData = accountsRepo.findById(id);

        if(oldAccountData.isPresent()) {
            Account updatedAccountData = oldAccountData.get();
            updatedAccountData.setUsername(newAccountData.getUsername());
            updatedAccountData.setPassword(newAccountData.getPassword());
            updatedAccountData.setRole(newAccountData.getRole());

            Account accountObj =  accountsRepo.save(updatedAccountData);
            return new ResponseEntity<>(accountObj, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteAccountById/{id}")
    public ResponseEntity<HttpStatus> deleteAccountById(@PathVariable Long id) {
        accountsRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody LoginRequest loginRequest) {
        try {
            Account account = accountsRepo.findByUsernameAndPassword(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );

            if (account != null) {
                return new ResponseEntity<>(account, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
