package com.gfbdev.controller;

import com.gfbdev.entity.Response;
import com.gfbdev.entity.Transaction;
import com.gfbdev.entity.dto.TransactioDTO;
import com.gfbdev.session.TransactionSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Headtrap on 28/08/2017.
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final
    TransactionSession session;

    @Autowired
    public TransactionController(TransactionSession session) {
        this.session = session;
    }

    @RequestMapping("/addTransaction")
    public Response add(@RequestBody Transaction transaction) {
        return session.add(transaction);
    }

}
