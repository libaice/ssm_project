package com.lagou.service.Impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }


    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);

        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //请空关联关系
        roleMapper.deleteRoleContentMenu(roleMenuVo.getRoleId());


        // 为角色分配 菜单
        for (Integer mid: roleMenuVo.getMenuIdList()){
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContentMenu(role_menu_relation);

        }

    }

    @Override
    public void deleteRole(Integer roleid) {

        //根据roleid  清空中间表关联关系
       roleMapper.deleteRoleContentMenu(roleid);

       roleMapper.deleteRole(roleid);

    }




    //根据id  查询对应的资源信息
    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer id) {

        // 获取通过传入的角色id值 查询到的 所有资源分类 的集合
        List<ResourceCategory> resourceCategories = roleMapper.findResourceCategoryByRoleId(id);

        // 在这个集合中的每个元素中添加 其对应的 资源信息， 一个资源分类对应多个资源信息
        for (ResourceCategory resourceCategory: resourceCategories){
            List<Resource> resources = roleMapper.findResourceByCategoryId(resourceCategory.getId());

            resourceCategory.setResourceList(resources);
        }
        return resourceCategories;

    }

    // 分配资源列表
    @Override
    public void RoleContextResource(RoleResourceVo roleResourceVo) {
        // 删除对应记录
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());

        // 获取前台传入的资源列表
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();
        RoleResourceRelation roleResourceRelation = new RoleResourceRelation();

        for (Integer resourceId: resourceIdList ){

            // 补全需要的信息
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            roleResourceRelation.setResourceId(resourceId);

            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");

            // 将封装好的 一个中间表关系 插入到
            roleMapper.roleContextResource(roleResourceRelation);
        }



    }


}
