package com.nokia.predict.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nokia.predict.model.KpiPortUtilization;
import com.nokia.predict.model.KpiPortUtilizationRepository;
import com.nokia.predict.model.UtilizationDO;
import com.nokia.predict.model.UtilizationPoint;
import com.nokia.predict.utils.CpuForecastWithCommonsMath;
import com.nokia.predict.utils.OutagePredictor;
import com.nokia.predict.utils.TeamsNotifier;
@Service
public class UtilizationService {
	@Autowired KpiPortUtilizationRepository repository;

	public UtilizationDO getTimedDataPoints() {
		UtilizationDO doObject = new UtilizationDO();
		LocalDateTime endTime = LocalDateTime.now();
		LocalDateTime startTime = endTime.minusDays(10);
		
		List<String> ofns = repository.findDistinctOfn();
		List<String> outageNodes = new ArrayList<String>();
		
		Map<String, List<UtilizationPoint>> dbStatsData = ofns.stream()
	    // fetch all data for all OFNs
	    .flatMap(ofn -> repository
	        .findByOfnAndTimeBetweenOrderByTimeAsc(ofn, startTime, endTime)
	        .stream())
	    // group all points by OFN
	    .collect(Collectors.groupingBy(
	        KpiPortUtilization::getOfn,
	        // for each OFN, convert the list of KpiPortUtilization -> List<TimedDataPoint>
	        Collectors.mapping(
	                (KpiPortUtilization kpi) -> new UtilizationPoint(kpi.getTime(), kpi.getValue()),
	                Collectors.toList()
	            )
	    ));
		
		dbStatsData.entrySet().stream().forEach(entry -> {
			List<UtilizationPoint> futureForecastPoints = CpuForecastWithCommonsMath.forecastCpu(
				    entry.getValue(),
				    10
				);
			
			String outageFlag = OutagePredictor.willOutageOccur(futureForecastPoints);
			if(null != outageFlag) {
				outageNodes.add(outageFlag+ "for node "+entry.getKey());
			}
			List<UtilizationPoint> fullSeries = new ArrayList<>(entry.getValue());
			fullSeries.addAll(futureForecastPoints);
			entry.setValue(fullSeries);
		});
		
			try {

				if (!CollectionUtils.isEmpty(outageNodes) && outageNodes.size() > 0) {
					TeamsNotifier teamsNotifier = new TeamsNotifier();
					teamsNotifier.sendOutageNotification(String.join("\n", outageNodes));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			doObject.setDataPoints(dbStatsData);
			doObject.setMessages(outageNodes);
		return doObject;
				
	}

}
