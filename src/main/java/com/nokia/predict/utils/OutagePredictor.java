package com.nokia.predict.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.nokia.predict.model.UtilizationPoint;

public class OutagePredictor {
	
	private static final double THRESHOLD = 60.0;

	public static String willOutageOccur(List<UtilizationPoint> forecastedUtilization) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		for (int i = 0; i < forecastedUtilization.size(); i++) {
			if (((double) forecastedUtilization.get(i).getUtilization()) >= THRESHOLD) {
				LocalDateTime dateTime = forecastedUtilization.get(i).getTimestamp();

				// Convert to LocalDate if you just want the date
				LocalDate date = dateTime.toLocalDate();
				return String.format("Outage risk on Day %s: Forecasted Utilization = %.2f%% ", date.format(formatter),
						forecastedUtilization.get(i).getUtilization());
			}
		}
		return null;
	}

}
