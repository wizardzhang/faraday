package com.xyz.wizard.faraday.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/29 14:17:04
 */
@RestController
public class ProviderController {

    @GetMapping("/hello")
    public String hello(){
        return "hello, im provider!";
    }

}
