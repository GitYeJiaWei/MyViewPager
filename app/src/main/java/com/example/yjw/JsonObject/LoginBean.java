package com.example.yjw.JsonObject;

import java.util.List;

/**
 * @author YJW
 * @create 2018/7/27
 * @Describe
 */
public class LoginBean {
    /**
     * Id : 1
     * UserName : Admin
     * Password : 123
     * RealName : 管理员
     * SimpleSpelling : null
     * Gender : null
     * Mobile : null
     * Email : null
     * FirstVisitTime : null
     * PreviousVisitTime : null
     * LastVisitTime : null
     * LogOnCount : null
     * Status : 0
     * Description : 11
     * SysVersion : 3
     * CreateDate : null
     * CreateUser : null
     * LogDate : 2018-07-10T10:38:58
     * LogUser :
     * InCurrentGroup : false
     * Groups : null
     * Factorys : null
     * Warehouses : [{"Id":"dd","WHName":"32378","Status":0,"Description":"6666","SysVersion":16,"CreateDate":"2018-06-28T13:44:39","CreateUser":null,"LogDate":"2018-07-05T14:10:58","LogUser":"","InCurrentGroup":false}]
     */

    private String Id;
    private String UserName;
    private String Password;
    private String RealName;
    private Object SimpleSpelling;
    private Object Gender;
    private Object Mobile;
    private Object Email;
    private Object FirstVisitTime;
    private Object PreviousVisitTime;
    private Object LastVisitTime;
    private Object LogOnCount;
    private int Status;
    private String Description;
    private int SysVersion;
    private Object CreateDate;
    private Object CreateUser;
    private String LogDate;
    private String LogUser;
    private boolean InCurrentGroup;
    private Object Groups;
    private Object Factorys;
    private List<WarehousesBean> Warehouses;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public Object getSimpleSpelling() {
        return SimpleSpelling;
    }

    public void setSimpleSpelling(Object SimpleSpelling) {
        this.SimpleSpelling = SimpleSpelling;
    }

    public Object getGender() {
        return Gender;
    }

    public void setGender(Object Gender) {
        this.Gender = Gender;
    }

    public Object getMobile() {
        return Mobile;
    }

    public void setMobile(Object Mobile) {
        this.Mobile = Mobile;
    }

    public Object getEmail() {
        return Email;
    }

    public void setEmail(Object Email) {
        this.Email = Email;
    }

    public Object getFirstVisitTime() {
        return FirstVisitTime;
    }

    public void setFirstVisitTime(Object FirstVisitTime) {
        this.FirstVisitTime = FirstVisitTime;
    }

    public Object getPreviousVisitTime() {
        return PreviousVisitTime;
    }

    public void setPreviousVisitTime(Object PreviousVisitTime) {
        this.PreviousVisitTime = PreviousVisitTime;
    }

    public Object getLastVisitTime() {
        return LastVisitTime;
    }

    public void setLastVisitTime(Object LastVisitTime) {
        this.LastVisitTime = LastVisitTime;
    }

    public Object getLogOnCount() {
        return LogOnCount;
    }

    public void setLogOnCount(Object LogOnCount) {
        this.LogOnCount = LogOnCount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getSysVersion() {
        return SysVersion;
    }

    public void setSysVersion(int SysVersion) {
        this.SysVersion = SysVersion;
    }

    public Object getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Object CreateDate) {
        this.CreateDate = CreateDate;
    }

    public Object getCreateUser() {
        return CreateUser;
    }

    public void setCreateUser(Object CreateUser) {
        this.CreateUser = CreateUser;
    }

    public String getLogDate() {
        return LogDate;
    }

    public void setLogDate(String LogDate) {
        this.LogDate = LogDate;
    }

    public String getLogUser() {
        return LogUser;
    }

    public void setLogUser(String LogUser) {
        this.LogUser = LogUser;
    }

    public boolean isInCurrentGroup() {
        return InCurrentGroup;
    }

    public void setInCurrentGroup(boolean InCurrentGroup) {
        this.InCurrentGroup = InCurrentGroup;
    }

    public Object getGroups() {
        return Groups;
    }

    public void setGroups(Object Groups) {
        this.Groups = Groups;
    }

    public Object getFactorys() {
        return Factorys;
    }

    public void setFactorys(Object Factorys) {
        this.Factorys = Factorys;
    }

    public List<WarehousesBean> getWarehouses() {
        return Warehouses;
    }

    public void setWarehouses(List<WarehousesBean> Warehouses) {
        this.Warehouses = Warehouses;
    }
}
