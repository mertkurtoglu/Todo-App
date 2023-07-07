package com.mertkurtoglu.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
abstract public class AuditingAwareBaseDto implements Serializable {

    public static final Long serialVersionUID=1L;
    //Global
    private Long id;
    @Builder.Default
    private Date systemDate=new Date(System.currentTimeMillis()); // DATE

    //Auditing
    @JsonIgnore
    protected String createdUser;

    @JsonIgnore
    protected String createdDate;
}
