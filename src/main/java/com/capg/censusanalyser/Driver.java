package com.capg.censusanalyser;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;

/**
 * Hello world!
 *
 */
public class Driver {
	public static void main(String[] args) throws CensusAnalyserException, CsvException {
		final String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
		final String INCORRECT_DATA_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IncorrectFile.txt";
		final String INDIA_STATE_CODE_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCode - IndiaStateCode.csv";

		
		try {
			StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CODE_FILE, CSVStateCode.class);
		} catch (CensusAnalyserException c) {
			c.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
