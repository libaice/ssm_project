package com.lagou.domain;

import java.util.List;

// 用来接受前台传来的参数
public class RoleResourceVo {

    private Integer roleId;
    private List<Integer> resourceIdList;

    @Override
    public String toString() {
        return "RoleResourceVo{" +
                "roleId=" + roleId +
                ", resourceIdList=" + resourceIdList +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Integer> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
