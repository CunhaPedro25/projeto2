package org.projeto.desktop.events;

import lombok.Getter;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    @Getter
    private static final EventBus instance = new EventBus();
    private final List<EventHandler<? super Event>> subscribers = new ArrayList<>();

    private EventBus() {}

    public void subscribe(EventHandler<? super Event> subscriber) {
        subscribers.add(subscriber);
    }

    public void publish(Event event) {
        for (EventHandler<? super Event> subscriber : subscribers) {
            subscriber.handle(event);
        }
    }
}