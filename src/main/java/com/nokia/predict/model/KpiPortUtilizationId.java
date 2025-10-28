package com.nokia.predict.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class KpiPortUtilizationId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ofn;
    private LocalDateTime time;
    private double value;

    public KpiPortUtilizationId() {}

    public KpiPortUtilizationId(String ofn, LocalDateTime time, double value) {
        this.ofn = ofn;
        this.time = time;
        this.value = value;
    }

    public String getOfn() {
        return ofn;
    }

    public void setOfn(String ofn) {
        this.ofn = ofn;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KpiPortUtilizationId)) return false;
        KpiPortUtilizationId that = (KpiPortUtilizationId) o;
        return Objects.equals(ofn, that.ofn) &&
               Objects.equals(time, that.time) &&
               Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ofn, time, value);
    }
}
