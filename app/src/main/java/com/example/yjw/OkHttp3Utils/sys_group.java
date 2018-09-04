package com.example.yjw.OkHttp3Utils;

/**
 * Created by kimhillzhang on 2018-01-15.
 */

public class sys_group {
    public String ID;
    public String Code;
    public String groupname ;
    public String parentid ;
    public String remark;
    public String groupmaster;
    /// <summary>
    /// 级数
    /// </summary>
    public int Tlevel;

    /// <summary>
    /// 所有父级
    /// </summary>
    public String AllParents;
    /// <summary>
    /// 数据来源  导入的：IMPORT
    /// </summary>
    public String FromStr;
    public boolean IsEdited;
    /// <summary>
    /// 授权码
    /// </summary>
    public String AuditCode;
    public boolean isCurrent;
}
