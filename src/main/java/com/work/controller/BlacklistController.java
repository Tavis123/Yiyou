package com.work.controller;

import com.work.service.BlacklistService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blacklist")
public class BlacklistController {

    @Resource
    private BlacklistService BlacklistService;

}
