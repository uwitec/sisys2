package com.sisys.bean;

import java.math.BigDecimal;

public class WorkHoursTab {
	private double workHours;
	private double backWorkHours;
	private double salary;
	private double totalWorkHours;
	public double getWorkHours() {
		return workHours;
	}
	public void setWorkHours(double workHours) {
		this.workHours = workHours;
	}
	public double getBackWorkHours() {
		return backWorkHours;
	}
	public void setBackWorkHours(double backWorkHours) {
		this.backWorkHours = backWorkHours;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getTotalWorkHours() {
		return new BigDecimal(workHours + backWorkHours).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public void setTotalWorkHours(double totalWorkHours) {
		this.totalWorkHours = totalWorkHours;
	}
	@Override
	public String toString() {
		return "WorkHoursTab [backWorkHours=" + backWorkHours + ", salary="
				+ salary + ", totalWorkHours=" + totalWorkHours
				+ ", workHours=" + workHours + "]";
	}
	
}
