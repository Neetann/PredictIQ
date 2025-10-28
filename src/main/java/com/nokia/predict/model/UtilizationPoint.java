package com.nokia.predict.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UtilizationPoint {
	private LocalDateTime timestamp;
    private double utilization;
    
}
