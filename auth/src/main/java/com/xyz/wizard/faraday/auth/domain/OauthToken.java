package com.xyz.wizard.faraday.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 15:08:12
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OauthToken {

    private String accessToken;

    private String tokenType = "bearer";

    public OauthToken accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public OauthToken tokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }
}
