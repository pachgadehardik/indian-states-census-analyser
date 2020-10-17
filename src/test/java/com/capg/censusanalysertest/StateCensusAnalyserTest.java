package com.capg.censusanalysertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.capg.censusanalyser.CensusAnalyserException;
import com.capg.censusanalyser.StateCensusAnalyser;

public class StateCensusAnalyserTest {

	final String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
	final String INCORRECT_DATA_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IncorrectFile.txt";

	@Test
	public void givenStateCensusFileHasCorrectNumberEntries_ShouldReturnTrue() throws CensusAnalyserException {
		try {
			assertEquals(29, StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIncorrectFilePathShouldReturnCustomException() throws CensusAnalyserException {

		assertThrows(CensusAnalyserException.class, () -> {
			StateCensusAnalyser.readCSVDataFile(INCORRECT_DATA_FILE);
		});

		try {
			StateCensusAnalyser.readCSVDataFile(INCORRECT_DATA_FILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.CensusExceptionType.FILE_NOT_FOUND_TYPE, e.type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
