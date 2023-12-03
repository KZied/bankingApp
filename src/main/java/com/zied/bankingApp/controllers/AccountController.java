package com.zied.bankingApp.controllers;

import com.zied.bankingApp.dto.AccountDto;
import com.zied.bankingApp.services.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@Tag(name = "account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(AccountDto accountDto){
        return ResponseEntity.ok(accountService.save(accountDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDto> findById(@PathVariable Integer accountId){
        return ResponseEntity.ok(accountService.findById(accountId));
    }

    @DeleteMapping("/{account-id}")
    public ResponseEntity<Void> delete(@PathVariable Integer accountId){
        accountService.delete(accountId);
        return ResponseEntity.accepted().build();
    }
}
