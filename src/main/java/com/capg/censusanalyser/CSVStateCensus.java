package com.capg.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
	
	@CsvBindByName(column = "State", required = true)
	private String state;
	@CsvBindByName(column = "Population", required = true)
	private String population;
	@CsvBindByName(column = "AreaInSqKm", required = true)
	private String area;
	@CsvBindByName(column = "DensityPerSqKm", required = true)
	private String density;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	@Override
	public String toString() {
		return "CSVStateCensus [state=" + state + ", population=" + population + ", area=" + area + ", density="
				+ density + "]";
	}

}
