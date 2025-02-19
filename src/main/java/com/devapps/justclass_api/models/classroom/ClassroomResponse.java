package com.devapps.justclass_api.models.classroom;

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
public class ClassroomResponse {
    private UUID classid;
    private String classname;
    private String level;
    private Date startdate;
    private Date enddate;
    private Double price;
    private Date createdate;
    private String createdby;
    private String responseMessage;
}
