package com.ou.foodie.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "`user_userconnection`")
public class UserUserconnection implements Serializable {
    @Id
    @Column(name = "`userId`")
    private String userid;

    @Id
    @Column(name = "`providerId`")
    private String providerid;

    @Id
    @Column(name = "`providerUserId`")
    private String provideruserid;

    @Column(name = "`social_rank`")
    private Integer socialRank;

    @Column(name = "`displayName`")
    private String displayname;

    @Column(name = "`profileUrl`")
    private String profileurl;

    @Column(name = "`imageUrl`")
    private String imageurl;

    @Column(name = "`accessToken`")
    private String accesstoken;

    @Column(name = "`secret`")
    private String secret;

    @Column(name = "`refreshToken`")
    private String refreshtoken;

    @Column(name = "`expireTime`")
    private Long expiretime;

    private static final long serialVersionUID = 1L;
}