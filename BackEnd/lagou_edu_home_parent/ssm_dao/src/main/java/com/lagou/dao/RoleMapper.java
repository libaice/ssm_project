package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

    //查询所有角色
    public List<Role> findAllRole(Role role);

    //根据角色id 查询 该角色对应的 菜单信息
    public List<Integer> findMenuByRoleId( Integer roleid );

    //根据roleid  清空中间表 关联关系
    public void deleteRoleContentMenu(Integer rid);

    //为角色分配菜单信息
    public void roleContentMenu(Role_menu_relation role_menu_relation);

    //删除角色
    public void deleteRole(Integer roleid);



    //获取当前角色拥有的 资源信息

    // 查询当前角色拥有的资源分类信息
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer id);

    // 查询当前角色所拥有的资源信息, 传入资源分类的id 值， 查询当前资源分类下的所有资源信息
    public List<Resource> findResourceByCategoryId(Integer categoryId);



    //根据 role_id 删除对应资源中间表 (role_resource_relation) 的记录
    public void deleteRoleContextResource(Integer roleId);

    //为角色分配资源列表, 插入新的关联关系
    public void roleContextResource(RoleResourceRelation roleResourceRelation );


}
