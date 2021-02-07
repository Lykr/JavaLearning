package com.learning.service;

import com.learning.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired(required = false)
    private BookDao bookDao2;

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao2=" + bookDao2 +
                '}';
    }
}
