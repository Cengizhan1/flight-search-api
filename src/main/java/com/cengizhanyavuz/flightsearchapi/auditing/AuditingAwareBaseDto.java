package com.cengizhanyavuz.flightsearchapi.auditing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter @Setter
abstract  public class AuditingAwareBaseDto  implements Serializable {

    public static final Long serialVersionUID=1L;

    private Long id;

    @Builder.Default
    private Date systemDate=new Date(System.currentTimeMillis());

    @JsonIgnore
    protected String createdUser;

    @JsonIgnore
    protected Date createdDate;

    @JsonIgnore
    protected String updatedUser;

    @JsonIgnore
    protected Date updatedDate;
}