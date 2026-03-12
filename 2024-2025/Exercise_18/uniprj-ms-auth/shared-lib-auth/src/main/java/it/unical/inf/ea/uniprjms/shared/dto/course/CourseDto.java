package it.unical.inf.ea.uniprjms.shared.dto.course;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseDto implements Serializable {
    private Long id;
    private String title;
    private Long teacherId;
}