package com.twon.habiticaconsumer.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HabiticaTaskResponse {
    @Getter @Setter private Boolean success = false;
    @Getter @Setter private ArrayList<HabiticaTaskDataObject> data = new ArrayList<HabiticaTaskDataObject>();
    @Getter @Setter private ArrayList<Object> notifications = new ArrayList<Object>();
    @Getter @Setter private String appVersion = "";
}
