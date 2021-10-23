package com.xyz.wizard.faraday.admin.client;

import com.xyz.wizard.faraday.admin.pojo.entity.SysUser;
import com.xyz.wizard.faraday.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 11:23:18
 */
@FeignClient(name = "admin-service")
public interface UserClient {

    @GetMapping("/api/v1/users/{username}")
    Result<SysUser> getUserByUsername(@PathVariable("username") String username);
}
