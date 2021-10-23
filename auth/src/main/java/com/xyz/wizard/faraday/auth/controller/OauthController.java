package com.xyz.wizard.faraday.auth.controller;

import com.xyz.wizard.faraday.auth.enums.OauthClientEnum;
import com.xyz.wizard.faraday.common.result.Result;
import com.xyz.wizard.faraday.common.utils.JsonUtils;
import com.xyz.wizard.faraday.common.utils.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 15:14:32
 */
@RestController
@RequestMapping("/oauth")
@Log4j2
public class OauthController {

    private TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public Object postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {

        /*
         * 获取登录认证的客户端ID
         *
         * 兼容两种方式获取Oauth2客户端信息（client_id、client_secret）
         * 方式一：client_id、client_secret放在请求路径中(注：当前版本已废弃)
         * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
         */
        String clientId = JwtUtils.getOauthClientId();

        log.info("OAuth认证授权 客户端ID:{}，请求参数：{}", clientId, JsonUtils.toJsonString(parameters));

        OauthClientEnum client = OauthClientEnum.getByClientId(clientId);
        if (client == OauthClientEnum.TEST) {
            // knife4j接口测试文档使用 client_id/client_secret : client/123456
            return tokenEndpoint.postAccessToken(principal, parameters).getBody();
        }
        return Result.success(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }
}
