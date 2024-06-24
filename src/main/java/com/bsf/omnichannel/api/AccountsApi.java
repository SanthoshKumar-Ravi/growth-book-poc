package com.bsf.omnichannel.api;

import com.bsf.omnichannel.service.AccountsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class AccountsApi {
    private final AccountsService accountsService;

    public AccountsApi(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<String> getAccounts() {
        return ResponseEntity.ok(accountsService.getAccounts());
    }
}