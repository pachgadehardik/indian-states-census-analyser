package com.capg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.capg.censusanalyser.CensusAnalyserException.CensusExceptionType;
import com.capg.csvbuilder.CSVBuilderFactory;
import com.capg.csvbuilder.ICSVBuilder;

public class StateCensusAnalyser {

	private static final Logger logger = LogManager.getLogger(StateCensusAnalyser.class);

	public int loadIndiaCensusData(String csvFilePath, boolean libFlag) throws IOException, CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = null;
			if (libFlag) //if true then using Opencsv
				csvBuilder = CSVBuilderFactory.createCSVBuilder();
			else //using CommonCSV library
				csvBuilder = CSVBuilderFactory.createCommonCSVBuilder();
			Iterator<CSVStateCensus> censusIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
			return this.getCount(censusIterator);
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
			if (libFlag) //if true then using Opencsv
				csvBuilder = CSVBuilderFactory.createCSVBuilder();
			else	//using CommonCSV library
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

}
