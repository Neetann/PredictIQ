package com.nokia.predict.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "kpi_port_utilization")
public class KpiPortUtilization {

	@EmbeddedId
	private KpiPortUtilizationId id;

	@Column(name = "ofn", nullable = false, length = 50, insertable = false, updatable = false)
	private String ofn;

	@Column(name = "time", nullable = false, insertable = false, updatable = false)
	private LocalDateTime time;

	@Column(insertable = false, updatable = false)
	private Double value;

	public KpiPortUtilization() {
	}

	public KpiPortUtilization(KpiPortUtilizationId id) {
		this.id = id;
		this.ofn = id.getOfn();
		this.time = id.getTime();
		this.value = id.getValue();
	}

	public KpiPortUtilizationId getId() {
		return id;
	}

	public void setId(KpiPortUtilizationId id) {
		this.id = id;
		if (id != null) {
			this.ofn = id.getOfn();
			this.time = id.getTime();
			this.value = id.getValue();
		}
	}

	public String getOfn() {
		return ofn;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public double getValue() {
		return value;
	}
}
