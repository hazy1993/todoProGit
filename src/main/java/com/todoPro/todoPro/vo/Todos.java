package com.todoPro.todoPro.vo;

public class Todos {

    long id;
    String title;
    String content;
    String regId;
    String modId;
    String execDt;  //실행일자
    String insertDt;
    String updateDt;

    public Todos() {

    }
    public Todos(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public void setMod_id(String modId) {
        this.modId = modId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getReg_id() {
        return regId;
    }

    public String getRegId() {
        return regId;
    }

    public String getModId() {
        return modId;
    }

    public String getExecDt() {
        return execDt;
    }

    public String getInsertDt() {
        return insertDt;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public void setExecDt(String execDt) {
        this.execDt = execDt;
    }

    public void setInsertDt(String insertDt) {
        this.insertDt = insertDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }
}
