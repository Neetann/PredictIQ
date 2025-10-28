package com.nokia.predict.utils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.nokia.predict.model.UtilizationPoint;

public class CpuForecastWithCommonsMath {

    /**
     * Forecasts future CPU usage using simple linear regression.
     * @param cpuDataPoints List of Object[] {timestampMillis, cpuValue}
     * @param futureSteps Number of future points to forecast
     * @return List of Object[] {futureTimestampMillis, predictedCpuValue}
     */
    public static List<UtilizationPoint> forecastCpu(List<UtilizationPoint> utilizationPoints, int futureSteps) {
        SimpleRegression regression = new SimpleRegression();

        // Add data points with index as independent variable (x), cpuValue as dependent (y)
        for (int i = 0; i < utilizationPoints.size(); i++) {
            regression.addData(i, utilizationPoints.get(i).getUtilization());
        }

        LocalDateTime lastDateTime = utilizationPoints.get(utilizationPoints.size() - 1).getTimestamp();

        List<UtilizationPoint> forecast = new ArrayList<>();
        for (int i = 1; i <= futureSteps; i++) {
            double predictedValue = regression.predict(utilizationPoints.size() + i - 1);
            // Add i days to the last LocalDateTime
            LocalDateTime futureDateTime = lastDateTime.plusDays(i); // or plusHours(i)
            forecast.add(new UtilizationPoint(futureDateTime, predictedValue));
        }

        return forecast;
    }
}
