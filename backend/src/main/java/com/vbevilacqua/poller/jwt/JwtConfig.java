package com.vbevilacqua.poller.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix= "application.jwt")
@Component
@NoArgsConstructor
@Getter
@Setter
public class JwtConfig {

    private String tokenPrefix; // "Bearer "
    private String secretKey;
    private Integer tokenExpirationAfterDays;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
