package com.lagou.controller;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuMapper menuMapper;




    //    查询所有角色

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> allRole = roleService.findAllRole(role);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", allRole);

        return responseResult;

    }

//    查询所有角色信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        //-1 表示查询所有的 父级菜单
        List<Menu> menuList = menuMapper.findSubMenuListByPid(-1);

        //响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menuList);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有的父子菜单", map);
        return responseResult;

    }

    //根据角色id查询当前关联的菜单信息id
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色关联的菜单信息", menuByRoleId);

        return responseResult;

    }

    //为角色分配菜单
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.roleContextMenu(roleMenuVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);

        return responseResult;
    }


    //删除角色
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);
        return responseResult;
    }







    // 根据 角色的id ,查询当前角色所拥有的资源信息，并返回  GET请求
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        List<ResourceCategory> resourceCategories = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true, 200, "资源请求返回成功", resourceCategories);

    }

    // 为角色分配菜单
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo ){
       roleService.RoleContextResource(roleResourceVo);

       return new ResponseResult(true, 200, "分配资源成功", null);
    }



}
