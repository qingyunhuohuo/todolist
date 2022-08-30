package com.rocket.todolist.beam;

import java.sql.Date;

public class Assignment {
    private int id;
    private String title;
    private Date expectTime;
    private String tag;
    private String content;
    private int userId;
    private String state;


    public Assignment(int id, String title, Date expectTime, String tag, String content, String state) {
        this.id = id;
        this.title = title;
        this.expectTime = expectTime;
        this.tag = tag;
        this.content = content;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", expectTime=" + expectTime +
                ", tag='" + tag + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", state='" + state + '\'' +
                '}';
    }

    public Assignment(int id, String title, Date expectTime, int userId, String state) {
        this.id = id;
        this.title = title;
        this.expectTime = expectTime;
        this.userId = userId;
        this.state = state;
    }

    public Assignment(String title, Date expectTime, int userId, String state) {
        this.title = title;
        this.expectTime = expectTime;
        this.userId = userId;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Assignment() {
    }
}
