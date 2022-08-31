package com.ou.foodie.session;

import lombok.Data;

@Data
public class Session {
    private Integer maximumSessions=1;
    private Boolean maxSessionsPreventsLogin=false;
}
