package com.capg.censusanalysertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.capg.censusanalyser.CSVStateCensus;
import com.capg.censusanalyser.CSVStateCode;
import com.capg.censusanalyser.CensusAnalyserException;
import com.capg.censusanalyser.IncorrectPOJO;
import com.capg.censusanalyser.StateCensusAnalyser;

public class StateCensusAnalyserTest {

	final String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
	final String INCORRECT_DATA_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IncorrectFile.txt";
	final String INDIA_STATE_CODE_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCode - IndiaStateCode.csv";
	
	@Test
	public void givenStateCensusFileHasCorrectNumberEntries_ShouldReturnTrue()
			throws CensusAnalyserException, IOException {
		int count = 0;
		try {
			count = StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE, CSVStateCensus.class);
			assertEquals(29, count);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void givenIncorrectFilePathShouldReturnCustomException() throws CensusAnalyserException {

		assertThrows(CensusAnalyserException.class, () -> {
			StateCensusAnalyser.readCSVDataFile(INCORRECT_DATA_FILE, CSVStateCensus.class);
		});

		try {
			StateCensusAnalyser.readCSVDataFile(INCORRECT_DATA_FILE, CSVStateCensus.class);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.CensusExceptionType.FILE_NOT_FOUND_TYPE, e.type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenCorrectCSVFile_IncorrectType_ShouldReturnCustomExeption()
			throws CensusAnalyserException, IOException {
		try {
			StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE, IncorrectPOJO.class);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.INCORRECT_TYPE, e.type);
		}
	}

	/**
	 * @throws CensusAnalyserException
	 * @throws IOException             passing a file with Incorrect Delimiter
	 */
	@Test
	public void givenCorrectCSVFile_IncorrectDelimiter_ShouldReturnCustomException()
			throws CensusAnalyserException, IOException {
		try {
			StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE, CSVStateCensus.class);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}
	}

	/**
	 * @throws CensusAnalyserException
	 * @throws IOException
	 * Passing a CSV File with incorrect Header Info
	 */ 
	@Test
	public void givenCorrectCSVFile_InCorrectHeader_ShouldReturnCustomException()
			throws CensusAnalyserException, IOException {
		try {
			StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE, CSVStateCensus.class);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}

	}
	
	
	@Test //2.1
	public void givenStateCodeFileHasCorrectNumberEntries_ShouldReturnTrue() {
		int count = 0;
		try {
			 count = StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CODE_FILE, CSVStateCode.class);
		} catch (IOException | CensusAnalyserException e) {
			assertEquals(37, count);
		}
	} 

	@Test //2.2
	public void givenIncorrectFilePath_ShouldThrowException() {
		assertThrows(CensusAnalyserException.class, () -> {
			StateCensusAnalyser.readCSVDataFile(INCORRECT_DATA_FILE, CSVStateCode.class);
		});
	}
	
	@Test //2.3
	public void givenCorrectStateCSVFile_IncorrectType_ShouldReturnCustomExeption() throws IOException, CensusAnalyserException {
		try {
			StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CODE_FILE, IncorrectPOJO.class);
		}
		catch(CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.INCORRECT_TYPE, e.type);
		}
	}

	/**
	 * @throws CensusAnalyserException
	 * @throws IOException
	 * Added a different delimiter in the data file for testing 
	 */
	@Test
	public void givenCorrectStateCSVFile_IncorrectDelimiter_ShouldReturnCustomException()
			throws CensusAnalyserException, IOException {
		try {
			StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CODE_FILE, CSVStateCode.class);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}
	}
	
}
