package com.capg.censusanalysertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.capg.censusanalyser.StateCensusAnalyser;

public class StateCensusAnalyserTest {

	private static final String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";

	@Test
	public void givenStateCensusFileHasCorrectNumberEntries_ShouldReturnTrue() {
		try {
			assertEquals(29, StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
