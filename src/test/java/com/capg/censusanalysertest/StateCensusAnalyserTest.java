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
import com.opencsv.exceptions.CsvException;

public class StateCensusAnalyserTest {

	final String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
	final String INCORRECT_DATA_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IncorrectFile.txt";
	final String INDIA_STATE_CODE_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCode - IndiaStateCode.csv";

	@Test
	public void givenStateCensusFileHasCorrectNumberEntries_ShouldReturnTrue()
			throws CensusAnalyserException, IOException, CsvException {
		int count = 0;
		count = new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE);
		assertEquals(29, count);
	}

	@Test
	public void givenIncorrectFilePathShouldReturnCustomException() throws CensusAnalyserException, CsvException {

		assertThrows(CensusAnalyserException.class, () -> {
			new StateCensusAnalyser().loadIndiaCensusData(INCORRECT_DATA_FILE);
		});

		try {
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE);
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
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE);
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
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE);
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
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}

	}

	@Test // 2.1
	public void givenStateCodeFileHasCorrectNumberEntries_ShouldReturnTrue() throws CsvException {
		int count = 0;
		try {
			count = new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE);
		} catch (IOException | CensusAnalyserException e) {
			assertEquals(37, count);
		}
	}

	@Test // 2.2
	public void givenIncorrectFilePath_ShouldThrowException() {
		assertThrows(CensusAnalyserException.class, () -> {
			new StateCensusAnalyser().loadIndiaStateCode(INCORRECT_DATA_FILE);
		});
	}

	@Test // 2.3
	public void givenCorrectStateCSVFile_IncorrectType_ShouldReturnCustomExeption()
			throws IOException, CensusAnalyserException, CsvException {
		try {
			new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE);
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
			new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE);
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
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CODE_FILE);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}

	}

}
