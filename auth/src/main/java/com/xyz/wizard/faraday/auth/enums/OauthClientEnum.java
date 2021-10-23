package com.xyz.wizard.faraday.auth.enums;

import lombok.Getter;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 15:54:16
 */
public enum OauthClientEnum {

    /**
     * 测试客户端
     */
    TEST("client", "测试客户端"),

    /**
     * 系统管理端
     */
    ADMIN("youlai-admin", "系统管理端"),

    /**
     * 微信小程序端
     */
    WECHAT_APP("youlai-weapp", "微信小程序端");


    @Getter
    private String clientId;

    @Getter
    private String  desc;

    OauthClientEnum(String clientId,String desc){
        this.clientId=clientId;
        this.desc=desc;
    }

    public static OauthClientEnum getByClientId(String clientId) {
        for (OauthClientEnum client : OauthClientEnum.values()) {
            if(client.getClientId().equals(clientId)){
                return client;
            }
        }
        return null;
    }
}
