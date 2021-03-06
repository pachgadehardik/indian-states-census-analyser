package com.capg.censusanalysertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.capg.censusanalyser.CSVStateCensus;
import com.capg.censusanalyser.CSVStateCode;
import com.capg.censusanalyser.CensusAnalyserException;
import com.capg.censusanalyser.SortingaFieldType;
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

		List<CSVStateCensus> list = new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
		assertEquals(29, list.size());
	}

	@Test
	public void givenIncorrectFilePathShouldReturnCustomException() throws CensusAnalyserException, CsvException {

		assertThrows(CensusAnalyserException.class, () -> {
			new StateCensusAnalyser().loadIndiaCensusData(INCORRECT_DATA_FILE, true);
		});

		try {
			new StateCensusAnalyser().loadIndiaCensusData(INCORRECT_DATA_FILE, true);
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
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
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
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
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
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}

	}

	@Test // 2.1
	public void givenStateCodeFileHasCorrectNumberEntries_ShouldReturnTrue() throws CsvException {
		List<CSVStateCode> list = null;
		try {
			list = new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE, true);
		} catch (IOException | CensusAnalyserException e) {
			assertEquals(37, list.size());
		}
	}

	@Test // 2.2
	public void givenIncorrectFilePath_ShouldThrowException() {
		assertThrows(CensusAnalyserException.class, () -> {
			new StateCensusAnalyser().loadIndiaStateCode(INCORRECT_DATA_FILE, true);
		});
	}

	@Test // 2.3
	public void givenCorrectStateCSVFile_IncorrectType_ShouldReturnCustomExeption()
			throws IOException, CensusAnalyserException, CsvException {
		try {
			new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE, true);
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
			new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE, true);
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
			new StateCensusAnalyser().loadIndiaCensusData(INDIA_STATE_CODE_FILE, true);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.type);
		}

	}

	@Test
	public void givenCommonCSVLibraryShouldImplement_correctBuilderFunction() {
		List<CSVStateCode> list = null;
		try {
			list = new StateCensusAnalyser().loadIndiaStateCode(INDIA_STATE_CODE_FILE, false);
		} catch (IOException | CensusAnalyserException e) {
			assertEquals(37, list.size());
		}
	}

	/**
	 * @throws CensusAnalyserException
	 * @throws IOException             Testing for 1st Value
	 */
	@Test
	public void givenCensusData_OnSortingStates_ShouldReturnSortedResult() throws CensusAnalyserException, IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		List<CSVStateCensus> censusList = stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
		String sortedCensusData = stateCensusAnalyser.getCensusCSVSortedData(censusList, SortingaFieldType.STATENAME);
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Andhra Pradesh", censusCSV[0].getState());
	}

	/**
	 * @throws IOException
	 * @throws CensusAnalyserException Testing for last sorted value
	 */
	@Test
	public void givenCensusData_OnSortingStates_ShouldReturnLastSortedResult()
			throws IOException, CensusAnalyserException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		List<CSVStateCensus> censusList = stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
		String sortedCensusData = stateCensusAnalyser.getCensusCSVSortedData(censusList, SortingaFieldType.STATENAME);
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].getState());
	}

	/**
	 * @throws IOException
	 * @throws CensusAnalyserException FIrst And LAst State Code Checking
	 */
	@Test
	public void givenCensusCodeData_OnSortingStatesCode_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException {
		StateCensusAnalyser stateCodeCensusAnalyser = new StateCensusAnalyser();
		stateCodeCensusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_FILE, true);
		String sortedCensusData = stateCodeCensusAnalyser.getStateCodeWiseSortedData();
		CSVStateCode[] censusCodeCSV = new Gson().fromJson(sortedCensusData, CSVStateCode[].class);
		assertEquals("WB", censusCodeCSV[censusCodeCSV.length - 1].getStateCode());
		assertEquals("AD", censusCodeCSV[0].getStateCode());
	}

	@Test
	public void givenStateCensusData_OnSortingPopulation_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		List<CSVStateCensus> censusList = stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
		String sortedCensusData = stateCensusAnalyser.getCensusCSVSortedData(censusList, SortingaFieldType.POPULATION);
		CSVStateCensus[] censusState = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("199812341", censusState[0].getPopulation());
		assertEquals("607688", censusState[censusState.length - 1].getPopulation());
	}

	@Test
	public void givenStateCensusData_OnSortingPopulationDensity_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		List<CSVStateCensus> censusList = stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
		String sortedCensusData = stateCensusAnalyser.getCensusCSVSortedData(censusList, SortingaFieldType.DENSITY);
		CSVStateCensus[] censusState = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("1102", censusState[0].getDensity());
		assertEquals("50", censusState[censusState.length - 1].getDensity());
	}

	@Test
	public void givenStateCensusData_OnSortingArea_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		List<CSVStateCensus> censusList = stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_FILE, true);
		String sortedCensusData = stateCensusAnalyser.getCensusCSVSortedData(censusList, SortingaFieldType.AREA);
		CSVStateCensus[] censusState = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("342239", censusState[0].getArea());
		assertEquals("3702", censusState[censusState.length - 1].getArea());
	}

}
