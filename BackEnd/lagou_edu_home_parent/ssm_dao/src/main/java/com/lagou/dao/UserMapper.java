package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //    用户分页
    public List<User> findAllUserByPage(UserVo userVo);

    // 用户状态设置
    //public void updateUserStatus(@Param("id") int id, @Param("status") String status );
    public void updateUserStatus(User user);

    //用户登录：  (根据用户名查询具体的用户信息)
    public User login(User user);

    //根据用户id 来清空中间表

    public void deleteUserContextRole(Integer userId);
    // 分配角色

    public void userContextRole(User_Role_relation user_role_relation);


    // 1. 根据用户id 查询 关联的角色信息
    public List<Role> findUserRelationRoleById(Integer id);

    // 2. 根据角色ID 查询角色所拥有的顶级菜单 (-1)
    public List<Menu> findParentByRoleId( List<Integer> ids );

    // 3. 根据pid 查询子菜单信息
    public List<Menu> findSubMenuByPid(Integer pid);

    // 4. 获取用户所拥有的资源权限信息
    public List<Resource> findResourceByRoleId(List<Integer> ids);





}
