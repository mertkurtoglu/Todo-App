package com.mertkurtoglu.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;

// LOMBOK
@Data

//SUPER CLASS
@MappedSuperclass
@JsonIgnoreProperties(value = {"created_date"},allowGetters = true)
abstract public class AuditingAwareBaseEntity {

    @CreatedBy
    @Column(name="created_user")
    protected String createdUser;

    @CreatedDate
    @Column(name="created_date")
    protected Date createdDate;
} //end class