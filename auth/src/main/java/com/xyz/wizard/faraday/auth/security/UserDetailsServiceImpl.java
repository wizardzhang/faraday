package com.xyz.wizard.faraday.auth.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 从数据库获取认证用户信息
 * 用于和前端传过来的用户信息进行密码判读
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 10:22:10
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //TODO
        return null;
    }
}
