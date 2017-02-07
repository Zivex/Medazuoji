package com.capinfo.omp.ws.model;

import java.util.List;

public class KeyCount {
	private String landLineNumber;

	private List<CountMessge> CountMessge;

	private String daysCount;

	private String commitTime;

	public void setLandLineNumber(String landLineNumber) {
		this.landLineNumber = landLineNumber;
	}

	public String getLandLineNumber() {
		return this.landLineNumber;
	}

	public void setCountMessge(List<CountMessge> CountMessge) {
		this.CountMessge = CountMessge;
	}

	public List<CountMessge> getCountMessge() {
		return this.CountMessge;
	}

	public void setDaysCount(String daysCount) {
		this.daysCount = daysCount;
	}

	public String getDaysCount() {
		return this.daysCount;
	}

	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}

	public String getCommitTime() {
		return this.commitTime;
	}
}
