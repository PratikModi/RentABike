package com.intuit.rentabike.model;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class Slot implements Serializable {
    private String slotId;
    private String slotName;

    public Slot(String slotId, String slotName) {
        this.slotId = slotId;
        this.slotName = slotName;
    }
}
