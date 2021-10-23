package com.xyz.wizard.faraday.admin.pojo.entity;

import com.xyz.wizard.faraday.common.base.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class SysUser extends BaseEntity {

    private Long id;

    private String username;

    private String nickname;

    private String mobile;

    private Integer gender;

    private String avatar;

    private String password;

    private String email;

    private Integer status;

    private Long deptId;

    private Integer deleted;

    private String deptName;

    private List<Long> roleIds;

    private String roleNames;

    private List<String> roles;


}