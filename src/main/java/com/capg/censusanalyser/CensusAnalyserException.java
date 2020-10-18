package com.capg.censusanalyser;

public class CensusAnalyserException extends Exception {

	
	private static final long serialVersionUID = -3378841586469716396L;

	public enum CensusExceptionType{
		FILE_NOT_FOUND_TYPE, INCORRECT_TYPE, DELIMITER_TYPE, OTHER_TYPE
	}
	
	public CensusExceptionType type;
	private String msg;
	
	  public CensusAnalyserException() {
	    }

	public CensusAnalyserException(CensusExceptionType type, String msg) {
		super(msg);
		this.type = type;
	}
	
}
