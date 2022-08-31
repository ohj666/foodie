package com.ou.foodie.pojo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

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