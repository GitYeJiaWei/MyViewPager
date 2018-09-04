package com.example.yjw.JsonObject;

/**
 * @author YJW
 * @create 2018/7/27
 * @Describe
 */
public class WarehousesBean {
        /**
         * Id : dd
         * WHName : 32378
         * Status : 0
         * Description : 6666
         * SysVersion : 16
         * CreateDate : 2018-06-28T13:44:39
         * CreateUser : null
         * LogDate : 2018-07-05T14:10:58
         * LogUser :
         * InCurrentGroup : false
         */

        private String Id;
        private String WHName;
        private int Status;
        private String Description;
        private int SysVersion;
        private String CreateDate;
        private Object CreateUser;
        private String LogDate;
        private String LogUser;
        private boolean InCurrentGroup;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getWHName() {
            return WHName;
        }

        public void setWHName(String WHName) {
            this.WHName = WHName;
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

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
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



}
