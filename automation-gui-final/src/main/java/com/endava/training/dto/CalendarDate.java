package com.endava.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarDate {
    private String event_name;
    private String location;
    private String hour;
    private String minute;
    private String second;
}
