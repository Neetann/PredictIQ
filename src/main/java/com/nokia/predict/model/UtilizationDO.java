package com.nokia.predict.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class UtilizationDO {
	
	private List<String> messages;
	private Map<String, List<UtilizationPoint>> dataPoints;

}
