package com.xyz.wizard.faraday.common.utils;

import com.google.gson.JsonObject;
import com.xyz.wizard.faraday.common.constant.AuthConstants;
import com.xyz.wizard.faraday.common.exception.BizException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class JwtUtils {

    @SneakyThrows
    public static JsonObject getJwtPayload() {
        String payload = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(AuthConstants.JWT_PAYLOAD_KEY);
        if (null == payload) {
            throw new BizException("请传入认证头");
        }
        return JsonUtils.toJsonObject(URLDecoder.decode(payload, StandardCharsets.UTF_8.name()));
    }

    /**
     * 解析JWT获取用户ID
     *
     */
    public static Long getUserId() {
        return getJwtPayload().get(AuthConstants.USER_ID_KEY).getAsLong();
    }

    /**
     * 解析JWT获取获取用户名
     *
     */
    public static String getUsername() {
        return getJwtPayload().get(AuthConstants.USER_NAME_KEY).getAsString();
    }

    /**
     * 获取登录认证的客户端ID
     * <p>
     * 兼容两种方式获取Oauth2客户端信息（client_id、client_secret）
     * 方式一：client_id、client_secret放在请求路径中
     * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
     *
     */
    @SneakyThrows
    public static String getOauthClientId() {
        String clientId;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 从请求路径中获取
        clientId = request.getParameter(AuthConstants.CLIENT_ID_KEY);
        if (StringUtils.isNotBlank(clientId)) {
            return clientId;
        }

        // 从请求头获取
        String basic = request.getHeader(AuthConstants.AUTHORIZATION_KEY);
        if (StringUtils.isNotBlank(basic) && basic.startsWith(AuthConstants.BASIC_PREFIX)) {
            basic = basic.replace(AuthConstants.BASIC_PREFIX, Strings.EMPTY);
            String basicPlainText = new String(Base64.getDecoder().decode(basic.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            clientId = basicPlainText.split(":")[0];
        }
        return clientId;
    }

    /**
     * JWT获取用户角色列表
     *
     * @return 角色列表
     */
    public static List<String> getRoles() {
        List<String> roles = null;
        JsonObject payload = getJwtPayload();
        if (payload.has(AuthConstants.JWT_AUTHORITIES_KEY)) {
            roles = JsonUtils.toObject(payload.get(AuthConstants.JWT_AUTHORITIES_KEY).getAsString(), List.class);
        }
        return roles;
    }
}