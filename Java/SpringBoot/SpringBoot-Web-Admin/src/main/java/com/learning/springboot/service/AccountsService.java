package com.learning.springboot.service;

import com.learning.springboot.bean.Account;
import com.learning.springboot.dao.AccountsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

    @Autowired
    AccountsMapper accountsMapper;

    public Account getAccountById(int id) {
        return accountsMapper.getById(id);
    }
}
