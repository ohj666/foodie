package com.ou.foodie.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`persistent_logins`")
public class PersistentLogins implements Serializable {
    @Id
    @Column(name = "`series`")
    private String series;

    @Column(name = "`username`")
    private String username;

    @Column(name = "`token`")
    private String token;

    @Column(name = "`last_used`")
    private Date lastUsed;

    private static final long serialVersionUID = 1L;
}