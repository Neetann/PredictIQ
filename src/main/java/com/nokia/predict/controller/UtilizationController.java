package com.nokia.predict.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.predict.model.UtilizationPoint;
import com.nokia.predict.service.UtilizationService;

@RestController
public class UtilizationController {

	@Autowired UtilizationService utilizationService;
    @GetMapping("/api/utilization")
    public Map<String, List<UtilizationPoint>> getUtilization() {
    	return utilizationService.getTimedDataPoints();
    }
   
}
