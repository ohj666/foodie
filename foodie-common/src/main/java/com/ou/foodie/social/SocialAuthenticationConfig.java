package com.ou.foodie.social;

import lombok.Data;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
@Data
public class SocialAuthenticationConfig extends SpringSocialConfigurer {
    private String socialAuthenticationProcessingUrl;

    public SocialAuthenticationConfig(String socialAuthenticationProcessingUrl) {
        this.socialAuthenticationProcessingUrl = socialAuthenticationProcessingUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter t = (SocialAuthenticationFilter) super.postProcess(object);
        t.setFilterProcessesUrl(socialAuthenticationProcessingUrl);
        return (T) t;
    }
}
