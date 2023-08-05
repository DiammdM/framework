package org.example.guava.event;

import lombok.Data;

/**
 * BEvent
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
@Data
public class BEvent {
    private String eventId;
    private String message;
}
