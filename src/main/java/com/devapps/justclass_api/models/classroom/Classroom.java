package com.devapps.justclass_api.models.classroom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID classid;

    @Column
    private String classname;

    @Column
    private String level;

    @Column
    private Date startdate;

    @Column
    private Date enddate;

    @Column
    private Double price;

    @Column
    private Date createdate;

    @Column
    private String createdby;

    @PrePersist
    protected void onCreate() {
        createdate = Date.from(Instant.now());
    }

    public UUID getClassid() {
        return classid;
    }

    public void setClassid(UUID classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }
}
