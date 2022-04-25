package mk.ukim.finki.domashna2_191124.model.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

public class CreatedBookEvent extends ApplicationEvent {

    private LocalDateTime time;


    public CreatedBookEvent(Object source) {
        super(source);
    }

    public CreatedBookEvent(Object source, Clock clock) {
        super(source, clock);
    }

}