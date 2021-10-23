package com.xyz.wizard.faraday.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 15:02:31
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
