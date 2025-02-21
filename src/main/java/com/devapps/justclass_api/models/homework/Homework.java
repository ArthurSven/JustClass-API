package com.devapps.justclass_api.models.homework;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID homeworkid;
    @Column
    private String homework;
    @Column
    private String book;
    @Column
    private int page;
    @Column
    private Date createdate;
    @Column
    private Date duedate;
    @Column
    private String classroom;
    @Column
    private String teacher;

    @PrePersist
    protected void onCreate() {
        createdate = Date.from(Instant.now());
    }

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
