package com.work.controller;

import com.work.common.Result;
import com.work.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@RestController
@RequestMapping("/blacklist")
public class BlacklistController {

    @Autowired
    private BlacklistService BlacklistService;



}
