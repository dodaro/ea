package it.unical.inf.ea.uniprjms.domain.dto.course;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseDto implements Serializable {
    private Long id;
    private String title;
    private Long teacherId;
}