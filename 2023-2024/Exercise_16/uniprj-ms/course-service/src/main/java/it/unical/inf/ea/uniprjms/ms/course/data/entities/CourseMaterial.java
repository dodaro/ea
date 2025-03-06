package it.unical.inf.ea.uniprjms.ms.course.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "COURSE_MATERIAL", uniqueConstraints =
    {
        @UniqueConstraint(columnNames = { "COURSE_CODE", "RSS_NO" })
    }
)
public class CourseMaterial {

    @Id
    private Long id;

    @Column(name="URL", unique = true)
    private String url;

    @Column
    private String rss_no;

    @Column
    private String course_code;

    @OneToOne(optional = false)
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    private Course course;

}