package com.ou.foodie.enums;

import lombok.AllArgsConstructor;


public enum YesOrNo {
    YES(1,"Yes"),
    NO(0,"No");

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public final Integer type;
    public final String value;

}
