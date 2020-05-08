package com.twon.habiticaconsumer.entities;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.twon.habiticaconsumer.HabiticaAccess;
import com.twon.habiticaconsumer.api.TheHandler;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Map;

@Component
public class HabiticaTaskResponse {
    @Getter @Setter private Boolean success = false;
    @Getter @Setter private ArrayList<HabiticaTaskDataObject> data = new ArrayList<HabiticaTaskDataObject>();
    @Getter @Setter private ArrayList<Object> notifications = new ArrayList<Object>();
    @Getter @Setter private String appVersion = "";

    @Autowired
    private HabiticaAccess habiticaAccess;
    private static final Logger logger = LoggerFactory.getLogger(HabiticaTaskResponse.class);



    @Autowired
    public HabiticaTaskResponse() {
        logger.info("HabiticaTaskResponse BEING CREATED");
    }

}
