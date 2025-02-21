package com.devapps.justclass_api.models.homework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkResponse {

    private UUID homeworkid;
    private String homework;
    private String book;
    private int page;
    private Date createdate;
    private Date duedate;
    private String classroom;
    private String teacher;
    private String message;
}
