package com.devapps.justclass_api.models.homework;

import java.util.Date;
import java.util.UUID;

public class HomeworkRequest {

    private UUID homeworkid;
    private String homework;
    private String book;
    private int page;
    private Date createdate;
    private Date duedate;
    private String classroom;
    private String teacher;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public UUID getHomeworkid() {
        return homeworkid;
    }

    public void setHomeworkid(UUID homeworkid) {
        this.homeworkid = homeworkid;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
