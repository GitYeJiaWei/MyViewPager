package com.example.yjw.OkHttp3Utils;

import java.util.List;

/**
 * Created by kimhillzhang on 2017-11-21.
 */

public class sys_users {
    public String uid;
    public String username;
    public String PASSWORD;
    public String email;
    public String truename;
    public String groupid;
    public String idcard;
    public String birthday;
    public String qq;
    public String mobile;
    public String headerImg;
    public String hometel;
    public String homeaddr;
    public String officeaddr;
    public String officename;
    public String remark;
    public String roleid;
    public String JobNumber;
    public String Code;
    public String GroupName;
    /// <summary>
    /// 账户失败期限
    /// </summary>
    public String UseLimitDate;
    /// <summary>
    /// 角色编号
    /// </summary>
    public String RoleCode;
    /// <summary>
    /// 角色名称
    /// </summary>
    public String RoleName;
    /**
     * 该账户下可查看的其它账户
     * */
    public List<sys_group> SubGroupList;
}
