package com.twon.habiticaconsumer.entities;

import org.springframework.context.annotation.Bean;
import lombok.Getter;
import lombok.Setter;

public class HabiticaTaskDataObject {
    @Getter @Setter private String frequency;
    @Getter @Setter private String type;
    @Getter @Setter private String notes;
    @Getter @Setter private Double value;
    @Getter @Setter private Boolean isDue;
    @Getter @Setter private Boolean completed;
    @Getter @Setter private String text;

}
