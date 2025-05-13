package com.todoPro.todoPro.vo;


public class Users {

    Long id;
    String userId;
    String userNm;
    String pw;
    String insertDt;
    String updateDt;

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public String getPw() {
        return pw;
    }

    public String getInsertDt() {
        return insertDt;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setInsertDt(String insertDt) {
        this.insertDt = insertDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }
}
