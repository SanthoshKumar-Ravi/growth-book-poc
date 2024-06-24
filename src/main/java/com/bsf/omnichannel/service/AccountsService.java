package com.bsf.omnichannel.service;

import growthbook.sdk.java.GrowthBook;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {
    private final GrowthBook growthBook;

    public AccountsService(GrowthBook growthBook) {
        this.growthBook = growthBook;
    }

    public String getAccounts(){
        if(growthBook.isOn("savings-account")){
            return "feature flag is ON so, savings accounts can be fetched";
        }else{
            return "feature flag is OFF so, savings accounts can't be fetched";
        }

    }
}
