package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

//    用户分页   & d多表查询

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findUserByPage(@RequestBody UserVo userVo){
        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        return new ResponseResult(true, 200, "分页多条件查询成功", pageInfo);
    }

    //用户状态修改
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam String status){

        userService.updateUserStatus(id, status);

        return new ResponseResult(true, 200, "状态修改成功", null);
    }






    //用户登录
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User user1 = userService.login(user);

        if(user1 != null ){


            //保存用户的id 和 access_token 到session 中

            //User login = userService.login(user);
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", user1.getId());

            // 将查询出来的信息响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", user1.getId());

            return new ResponseResult(true, 200, "登录成功", map);

        }else {
            return new ResponseResult(true, 200, "用户或密码错误", null  );
        }

    }

    //分配角色回显
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);

        return new ResponseResult(true, 200, "分配角色回显成功", roleList);
    }

    //分配角色
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole( @RequestBody UserVo userVo ){
        userService.userContextRole(userVo);

        return new ResponseResult(true, 200, "分配角色成功",null );

    }

    //获取用户权限， 并动态显示菜单
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //1. 获取请求头  中的token
        String header_token = request.getHeader("Authorization");

        //2. 获取session 中的token
        String  session_token = (String) request.getSession().getAttribute("access_token");

        //判断token 是否一致
        if (header_token.equals(session_token)){
            // 获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");

            // 调用service 进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);

            return responseResult;

        }else {
            ResponseResult responseResult = new ResponseResult(false, 400, "获取菜单信息失败", null);
            return responseResult;
        }

    }



}
