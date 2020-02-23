package com.shiro.realm;

import com.shiro.pojo.Permission;
import com.shiro.pojo.Role;
import com.shiro.pojo.User;
import com.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的Realm
 */
public class CustomRealm extends AuthorizingRealm {

    public void setName(String name){
        super.setName("customRealm");
    }
    @Autowired
    private UserService userService;

    /**
     *授权方法
     *  操作的时候，判断用户是否具有响应的权限
     *      先认证 -- 安全数据
     *      再授权 -- 根据安全数据获取用户具有的所有操作权限
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取已认证的用户数据
        User user = (User) principals.getPrimaryPrincipal();//得到唯一的安全数据
        //根据用户数据获取用户的权限信息(所有角色，所有权限)
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();//所有角色
        Set<String> perms = new HashSet<>();//所有权限
        for(Role role:user.getRoles()){
            roles.add(role.getName());
            for (Permission perm : role.getPermissions()){
                perms.add(perm.getCode());
            }
        }
        info.setStringPermissions(perms);
        info.setRoles(roles);
        return info;
    }

    /**
     *认证方法
     *  参数：传递的用户名密码
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取登录的用户名和密码(token)
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //根据用户名查询数据库
        User user = userService.findByName(username);
        //判断用户是否存在或者密码是否一致
        if(user!=null && user.getPassword().equals(password)){
            //如果一致返回安全数据
            //构造方法：安全数据，密码realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
            return info;
        }
        //不一致，返回null(抛出异常)
        return null;
    }
    public static void main(String[] args) {
        System.out.println(new Md5Hash("123456","wangwu",3).toString());
    }
}
