package it.unical.inf.ea.jpa.entity.basic;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@NoArgsConstructor
public class Lesson {

    /*
        RANDOM – generate UUID based on random numbers (version 4 in RFC)
        TIME – generate time-based UUID (version 1 in RFC)
        AUTO – this is the default option and is the same as RANDOM
     */
    @Id
    @UuidGenerator // genera un uuid
    private String id;

    @Column(name = "TITLE", unique = true)
    private String title;
}
