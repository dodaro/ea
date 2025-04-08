package it.unical.inf.ea.uniprj.data.entities;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Slf4j
public class StudentAuditTrailListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Student student) {
        if (student.getId() == null) {
            log.info("[STUDENT AUDIT] About to add a student");
        } else {
            log.info("[STUDENT AUDIT] About to update/delete student: " + student.getId());
        }
    }
    
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Student student) {
        log.info("[STUDENT AUDIT] add/update/delete complete for student: " + student.getId());
    }
    
    @PostLoad
    private void afterLoad(Student student) {
        log.info("[STUDENT AUDIT] user loaded from database: " + student.getId());
    }
}