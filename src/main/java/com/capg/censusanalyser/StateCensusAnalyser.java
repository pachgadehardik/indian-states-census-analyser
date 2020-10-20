package com.capg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.capg.censusanalyser.CensusAnalyserException.CensusExceptionType;
import com.capg.csvbuilder.CSVBuilderFactory;
import com.capg.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;

public class StateCensusAnalyser {
	List<CSVStateCensus> censusCSVList = null;
	private static final Logger logger = LogManager.getLogger(StateCensusAnalyser.class);

	public int loadIndiaCensusData(String csvFilePath, boolean libFlag) throws IOException, CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = null;
			if (libFlag) // if true then using Opencsv
				csvBuilder = CSVBuilderFactory.createCSVBuilder();
			else // using CommonCSV library
				csvBuilder = CSVBuilderFactory.createCommonCSVBuilder();
			censusCSVList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
			return censusCSVList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(CensusExceptionType.FILE_NOT_FOUND_TYPE, e.getMessage());
		} catch (RuntimeException r) {
			logger.error("Delimiter or header issue !!");
			throw new CensusAnalyserException(CensusExceptionType.DELIMITER_OR_HEADER_TYPE,
					"Runtime base Exception reagarding Delimiter or any Header Issue: " + r.getMessage());
		} catch (Exception E) {
			logger.error("Other Exception");
			throw new CensusAnalyserException(CensusExceptionType.OTHER_TYPE, E.getMessage());
		}
	}

	public int loadIndiaStateCode(String csvFilePath, boolean libFlag) throws IOException, CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = null;
			if (libFlag) // if true then using Opencsv
				csvBuilder = CSVBuilderFactory.createCSVBuilder();
			else // using CommonCSV library
				csvBuilder = CSVBuilderFactory.createCommonCSVBuilder();
			Iterator<CSVStateCensus> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCode.class);
			return this.getCount(stateCensusIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(CensusExceptionType.FILE_NOT_FOUND_TYPE, e.getMessage());
		} catch (RuntimeException r) {
			logger.error("Delimiter or header issue !!");
			throw new CensusAnalyserException(CensusExceptionType.DELIMITER_OR_HEADER_TYPE,
					"Runtime base Exception reagarding Delimiter or any Header Issue: " + r.getMessage());
		} catch (Exception E) {
			logger.error("Other Exception");
			throw new CensusAnalyserException(CensusExceptionType.OTHER_TYPE, E.getMessage());
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		DOMConfigurator.configure("log4j.xml");
		Iterable<E> objIterable = () -> iterator;
		int countEntries = (int) StreamSupport.stream(objIterable.spliterator(), false).count();
		logger.info("Total Entries: " + countEntries);
		return countEntries;

	}

	public String getStateWiseSortedData() throws CensusAnalyserException {
		if(censusCSVList == null || censusCSVList.size() == 0)
			throw new CensusAnalyserException(CensusExceptionType.OTHER_TYPE,"No census Data!!");
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.getState());
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}

	private void sort(Comparator<CSVStateCensus> censusComparator) {
		for (int i = 0; i < censusCSVList.size(); i++) {
			for (int j = 0; j < censusCSVList.size() - i - 1; j++) {
				CSVStateCensus census1 = censusCSVList.get(j);
				CSVStateCensus census2 = censusCSVList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					censusCSVList.set(j, census2);
					censusCSVList.set(j + 1, census1);
				}

			}
		}
	}
}
