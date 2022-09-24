package com.rocket.todolist.beam;

import java.util.Date;

public class Task {
    private int id;
    private int userId;
    private String title;
    private Date startTime;
    private Date endTime;
    private String color;
    private String category;
    private String content;
    private int state;
    private int repeat;
    private int remindMethod;
    private Date remindTime;
    private String address;

    public Task(int userId, Date startTime, Date endTime, String category, int state) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.state = state;
    }

    public Task(int id, int userId, String title, Date startTime,
                Date endTime, String color, String category, String content,
                int state, int repeat, int remindMethod, Date remindTime, String address) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.color = color;
        this.category = category;
        this.content = content;
        this.state = state;
        this.repeat = repeat;
        this.remindMethod = remindMethod;
        this.remindTime = remindTime;
        this.address = address;
    }

    public Task(int userId, String title, Date startTime, Date endTime,
                String color, String category, String content, int state,
                int repeat, int remindMethod, Date remindTime, String address) {
        this.userId = userId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.color = color;
        this.category = category;
        this.content = content;
        this.state = state;
        this.repeat = repeat;
        this.remindMethod = remindMethod;
        this.remindTime = remindTime;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getRemindMethod() {
        return remindMethod;
    }

    public void setRemindMethod(int remindMethod) {
        this.remindMethod = remindMethod;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
