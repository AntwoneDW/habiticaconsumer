package com.twon.habiticaconsumer.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class HabiticaTaskDataObject {
    @Getter @Setter private String id;
    @Getter @Setter private String _id;
    @Getter @Setter private String frequency;
    @Getter @Setter private String type;
    @Getter @Setter private String notes;
    @Getter @Setter private Double value;
    @Getter @Setter private Boolean isDue;
    @Getter @Setter private Boolean completed;
    @Getter @Setter private String text;
}
