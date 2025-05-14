package it.unical.inf.ea.uniprjms.domain.dto.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto<T> implements Serializable {
    private String id;
    private String eventType;
    private LocalDateTime timestamp;
    private T data;
}