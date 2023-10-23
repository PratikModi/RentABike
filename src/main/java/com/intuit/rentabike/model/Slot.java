package com.intuit.rentabike.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Slot {
    private String slotId;
    private String slotName;

    public Slot(String slotId, String slotName) {
        this.slotId = slotId;
        this.slotName = slotName;
    }
}
