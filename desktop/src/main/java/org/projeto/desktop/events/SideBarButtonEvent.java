package org.projeto.desktop.events;

import javafx.event.Event;
import lombok.Getter;

@Getter
public class SideBarButtonEvent extends Event {
    private final String buttonId;

    public SideBarButtonEvent(String buttonId) {
        super(Event.ANY);
        this.buttonId = buttonId;
    }
}
