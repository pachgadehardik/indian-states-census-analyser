package com.capg.censusanalysertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.capg.censusanalyser.CSVStateCensus;
import com.capg.censusanalyser.CensusAnalyserException;
import com.capg.censusanalyser.IncorrectPOJO;
import com.capg.censusanalyser.StateCensusAnalyser;

public class StateCensusAnalyserTest {

	final String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
	final String INCORRECT_DATA_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IncorrectFile.txt";

	@Test
	public void givenStateCensusFileHasCorrectNumberEntries_ShouldReturnTrue() throws CensusAnalyserException, IOException {
			assertEquals(29, StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE,CSVStateCensus.class));
	}

	@Test
	public void givenIncorrectFilePathShouldReturnCustomException() throws CensusAnalyserException {

		assertThrows(CensusAnalyserException.class, () -> {
			StateCensusAnalyser.readCSVDataFile(INCORRECT_DATA_FILE,CSVStateCensus.class);
		});

//		try {
//			StateCensusAnalyser.readCSVDataFile(INCORRECT_DATA_FILE,CSVStateCensus.class);
//		} catch (CensusAnalyserException e) {
//			e.printStackTrace();
//			assertEquals(CensusAnalyserException.CensusExceptionType.FILE_NOT_FOUND_TYPE, e.type);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void givenCorrectCSVFile_IncorrectType_ShouldReturnCustomExeption() throws CensusAnalyserException, IOException {
		try {
			StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE, IncorrectPOJO.class);
		}
		catch(CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.INCORRECT_TYPE, e.type);
		}
	}

}
