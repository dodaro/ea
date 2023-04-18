package it.unical.inf.ea.uniexample.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CourseMaterial {

    @Id
    private Long id;

    @Column(name="URL")
    private String url;

    @OneToOne(mappedBy = "material", optional = false)
    private Course course;

}