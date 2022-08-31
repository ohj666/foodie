package com.ou.foodie.pojo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`spring_session`")
public class SpringSession implements Serializable {
    @Id
    @Column(name = "`SESSION_ID`")
    private String sessionId;

    @Column(name = "`CREATION_TIME`")
    private Long creationTime;

    @Column(name = "`LAST_ACCESS_TIME`")
    private Long lastAccessTime;

    @Column(name = "`MAX_INACTIVE_INTERVAL`")
    private Integer maxInactiveInterval;

    @Column(name = "`PRINCIPAL_NAME`")
    private String principalName;

    private static final long serialVersionUID = 1L;
}