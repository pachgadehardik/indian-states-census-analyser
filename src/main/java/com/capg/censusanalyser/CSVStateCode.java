package com.capg.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {

	@CsvBindByName(column="SrNO", required = true)
	private String sr_no;
	@CsvBindByName(column="State Name", required = true)
	private String state;
	@CsvBindByName(column="TIN", required = true)
	private String tinNumber;
	@CsvBindByName(column="StateCode", required = true)
	private String stateCode;
	public String getSr_no() {
		return sr_no;
	}
	public void setSr_no(String sr_no) {
		this.sr_no = sr_no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTinNumber() {
		return tinNumber;
	}
	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	@Override
	public String toString() {
		return "CSVStateCode [sr_no=" + sr_no + ", state=" + state + ", tinNumber=" + tinNumber + ", stateCode="
				+ stateCode + "]";
	}
	
	
	
	
}
