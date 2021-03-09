package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

public interface RoleService {

    //查询所有角色
    public List<Role> findAllRole(Role role);

    // 根据角色信息查询当前 菜单信息
    public List<Integer> findMenuByRoleId(Integer roleid);

    //为角色分配菜单
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    //删除角色
    public void deleteRole(Integer roleid );


    //根据角色id 查询对应的资源信息

    public List<ResourceCategory> findResourceListByRoleId(Integer id);

    //为角色分配 资源信息
    public void RoleContextResource(RoleResourceVo roleResourceVo);



}
