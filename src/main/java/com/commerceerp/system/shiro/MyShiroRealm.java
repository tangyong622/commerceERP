package com.commerceerp.system.shiro;

import com.commerceerp.system.entity.SysUser;
import com.commerceerp.system.service.LoginService;
import com.commerceerp.system.util.MD5Tools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * shiro身份校验核心类
 */

public class MyShiroRealm extends AuthorizingRealm {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private LoginService loginService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        //密码进行加密处理  明文为  password
        String pawDES = MD5Tools.getSHA256StrJava(password);
        //从数据库获取对应用户名密码的用户
        SysUser systemUser = loginService.doLogin(name, pawDES);
        if (systemUser == null) {
            throw new AccountException("帐号或密码不正确！");
        }
        logger.info("身份认证成功，登录用户：" + name);
        return new SimpleAuthenticationInfo(systemUser, password, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        logger.info("权限认证方法：MyShiroRealm.doGetAuthorizationInfo()");
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String userId = user.getId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //根据用户ID查询角色（role），放入到Authorization里。
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("SystemId", userId);
        Set<String> roleSet = new HashSet<String>();
        roleSet.add(user.getUsername());
        info.setRoles(roleSet);

        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加:权限删除");
        permissionSet.add("uadd22");
        info.setStringPermissions(permissionSet);
        return info;
    }
}