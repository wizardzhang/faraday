package com.xyz.wizard.faraday.admin.client;

import com.xyz.wizard.faraday.admin.pojo.entity.SysOauthClient;
import com.xyz.wizard.faraday.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 14:17:47
 */
@FeignClient("admin-service")
public interface OauthClientInfoClient {
    @GetMapping("/api/v1/oauth-clients/{clientId}")
    Result<SysOauthClient> getOAuthClientById(@PathVariable String clientId);
}
