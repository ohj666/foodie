package com.ou.foodie.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "`spring_session_attributes`")
public class SpringSessionAttributes implements Serializable {
    @Id
    @Column(name = "`SESSION_ID`")
    private String sessionId;

    @Id
    @Column(name = "`ATTRIBUTE_NAME`")
    private String attributeName;

    @Column(name = "`ATTRIBUTE_BYTES`")
    private byte[] attributeBytes;

    private static final long serialVersionUID = 1L;
}