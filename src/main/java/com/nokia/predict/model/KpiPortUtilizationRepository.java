package com.nokia.predict.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KpiPortUtilizationRepository extends JpaRepository<KpiPortUtilization, KpiPortUtilizationId>{
    
	@Query("SELECT DISTINCT k.ofn FROM KpiPortUtilization k")
    List<String> findDistinctOfn();
	
	List<KpiPortUtilization> findByOfnAndTimeBetweenOrderByTimeAsc(String ofn, LocalDateTime start, LocalDateTime end);
}
