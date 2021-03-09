package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    //查询所有菜单信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true, 200, "查询所有菜单信息成功", allMenu);
    }



    //回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuByInfoId(Integer id){

        //根据id 的值来判断当前是 增加  还是更新操作
        if (id == -1){
            //添加 回显信息中 不需要查询 menu 信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据、
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", subMenuListByPid);

            return new ResponseResult(true, 200, "添加回显成功", map);
        }else {
            //修该操作  回显所有的menu 信息

             Menu menu =  menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", subMenuListByPid);

            return new ResponseResult(true, 200, "修改回显成功", map);


        }

    }

}
