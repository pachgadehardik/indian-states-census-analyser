package com.capg.censusanalysertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.capg.censusanalyser.CSVStateCensus;
import com.capg.censusanalyser.CSVStateCode;
import com.capg.censusanalyser.CensusAnalyserException;
import com.capg.censusanalyser.StateCensusAnalyser;
import com.google.gson.Gson;
import com.opencsv.exceptions.CsvException;

public class StateCensusAnalyserTest {

	final String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
	final String INCORRECT_DATA_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IncorrectFile.txt";
	final String INDIA_STATE_CODE_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCode - IndiaStateCode.csv";

	@Test
	public void givenStateCensusFileHasCorrectNumberEntries_ShouldReturnTrue()
			throws CensusAnalyserException, IOException, CsvException {
		int count = 0;
		count = new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE,true);
		assertEquals(29, count);
	}

	@Test
	public void givenIncorrectFilePathShouldReturnCustomException() throws CensusAnalyserException, CsvException {

		assertThrows(CensusAnalyserException.class, () -> {
			new StateCensusAnalyser().loadIndiaCensusData(INCORRECT_DATA_FILE,true);
		});

		try {
			new StateCensusAnalyser().loadIndiaCensusData(INCORRECT_DATA_FILE,true);
		} catch (CensusAnalyserException e) {
//			e.printStackTrace();
			assertEquals(CensusAnalyserException.CensusExceptionType.FILE_NOT_FOUND_TYPE, e.type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenCorrectCSVFile_IncorrectType_ShouldReturnCustomExeption()
			throws CensusAnalyserException, IOException, CsvException {
		try {
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE,true);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.INCORRECT_TYPE, e.type);
		}
	}

	/**
	 * @throws CensusAnalyserException
	 * @throws IOException             passing a file with Incorrect Delimiter
	 * @throws CsvException
	 */
	@Test
	public void givenCorrectCSVFile_IncorrectDelimiter_ShouldReturnCustomException()
			throws CensusAnalyserException, IOException, CsvException {
		try {
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE,true);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}
	}

	/**
	 * @throws CensusAnalyserException
	 * @throws IOException             Passing a CSV File with incorrect Header Info
	 * @throws CsvException
	 */
	@Test
	public void givenCorrectCSVFile_InCorrectHeader_ShouldReturnCustomException()
			throws CensusAnalyserException, IOException, CsvException {
		try {
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE,true);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}

	}

	@Test // 2.1
	public void givenStateCodeFileHasCorrectNumberEntries_ShouldReturnTrue() throws CsvException {
		int count = 0;
		try {
			count = new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE,true);
		} catch (IOException | CensusAnalyserException e) {
			assertEquals(37, count);
		}
	}

	@Test // 2.2
	public void givenIncorrectFilePath_ShouldThrowException() {
		assertThrows(CensusAnalyserException.class, () -> {
			new StateCensusAnalyser().loadIndiaStateCode(INCORRECT_DATA_FILE,true);
		});
	}

	@Test // 2.3
	public void givenCorrectStateCSVFile_IncorrectType_ShouldReturnCustomExeption()
			throws IOException, CensusAnalyserException, CsvException {
		try {
			new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE,true);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.INCORRECT_TYPE, e.type);
		}
	}

	/**
	 * @throws CensusAnalyserException
	 * @throws IOException             Added a different delimiter in the data file
	 *                                 for testing
	 * @throws CsvException
	 */
	@Test // 2.4
	public void givenCorrectStateCSVFile_IncorrectDelimiter_ShouldReturnCustomException()
			throws CensusAnalyserException, IOException, CsvException {
		try {
			new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE,true);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}
	}

	/**
	 * @throws CensusAnalyserException
	 * @throws IOException             Changed the Header name in data file for
	 *                                 testing
	 * @throws CsvException
	 */
	@Test // 2.5
	public void givenCorrectStateCSVFile_InCorrectHeader_ShouldReturnCustomException()
			throws CensusAnalyserException, IOException, CsvException {
		try {
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CODE_FILE,true);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}

	}
	
	@Test
	public void givenCommonCSVLibraryShouldImplement_correctBuilderFunction() {
		int count = 0;
		try {
			count = new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE,true);
		} catch (IOException | CensusAnalyserException e) {
			assertEquals(37, count);
		}
	}
	
	/**
	 * @throws CensusAnalyserException
	 * @throws IOException
	 * Testing for 1st Value
	 */
	@Test
	public void givenCensusData_OnSortingStates_ShouldReturnSortedResult() throws CensusAnalyserException, IOException {
		 StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		 stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
		 String sortedCensusData = stateCensusAnalyser.getStateWiseSortedData();
		 CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		 assertEquals("Andhra Pradesh",censusCSV[0].getState());		 
	}
	
	/**
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * Testing for last sorted value
	 */
	@Test
	public void givenCensusData_OnSortingStates_ShouldReturnLastSortedResult() throws IOException, CensusAnalyserException {
		 StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		 stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
		 String sortedCensusData = stateCensusAnalyser.getStateWiseSortedData();
		 CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		 assertEquals("West Bengal",censusCSV[censusCSV.length-1].getState());		 
	}
	
	/**
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * FIrst And LAst State Code Checking
	 */
	@Test
	public void givenCensusCodeData_OnSortingStatesCode_ShouldReturnSortedResult() throws IOException, CensusAnalyserException {
		StateCensusAnalyser stateCodeCensusAnalyser = new StateCensusAnalyser();
		stateCodeCensusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_FILE, true);
		String sortedCensusData = stateCodeCensusAnalyser.getStateCodeWiseSortedData();
		CSVStateCode[] censusCodeCSV = new Gson().fromJson(sortedCensusData, CSVStateCode[].class);
		assertEquals("WB", censusCodeCSV[censusCodeCSV.length - 1].getStateCode());
		assertEquals("AD", censusCodeCSV[0].getStateCode());
	}
	
	
}
