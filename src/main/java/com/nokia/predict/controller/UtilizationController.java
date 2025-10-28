package com.nokia.predict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.predict.model.UtilizationDO;
import com.nokia.predict.service.UtilizationService;

@RestController
public class UtilizationController {

	@Autowired UtilizationService utilizationService;
    @GetMapping("/api/utilization")
    public UtilizationDO getUtilization() {
    	return utilizationService.getTimedDataPoints();
    }
   
}
