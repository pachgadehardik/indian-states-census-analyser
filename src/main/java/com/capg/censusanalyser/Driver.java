package com.capg.censusanalyser;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Driver {
	public static void main(String[] args) throws CensusAnalyserException {
		final String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
		final String INCORRECT_DATA_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IncorrectFile.txt";

		try {
			StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE, CSVStateCensus.class);
		} catch (CensusAnalyserException c) {
			c.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
