package com.twon.habiticaconsumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "habitica")
public class HabiticaAccess {
    /*
        habitica.userkey=xxx
        habitica.secretkey=xxx
     */
    @Getter @Setter String userkey;
    @Getter @Setter String secretkey;
}
