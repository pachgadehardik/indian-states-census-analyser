package com.capg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.capg.censusanalyser.CensusAnalyserException.CensusExceptionType;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	private static final Logger logger = LogManager.getLogger(StateCensusAnalyser.class);

	@SuppressWarnings("unchecked")
	public static <T> int readCSVDataFile(String dataFile, Class<T> myClass)
			throws IOException, CensusAnalyserException {
		Iterable<T> csvToBean = null;
		Reader reader = null;
		int countEntries = 0;
		DOMConfigurator.configure("log4j.xml");
		try {
			reader = Files.newBufferedReader(Paths.get(dataFile));
			validateInputObjectType(myClass);
			CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType((Class) myClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			csvToBean = csvToBeanBuilder.build();
		}

		catch (CensusAnalyserException e) {
			logger.error("Exception occured regarding Incorrect Type of POJO");
			throw new CensusAnalyserException(CensusExceptionType.INCORRECT_TYPE, e.getMessage());
		} catch (NoSuchFileException e) {
			logger.error("No File Found!!");
			throw new CensusAnalyserException(CensusExceptionType.FILE_NOT_FOUND_TYPE, "NO FILE FOUND!!");
		} catch (RuntimeException r) {
			logger.error("Delimiter or header issue !!");
			throw new CensusAnalyserException(CensusExceptionType.DELIMITER_OR_HEADER_TYPE,
					"Runtime base Exception reagarding Delimiter or any Header Issue: " + r.getMessage());
		} catch (Exception E) {
			logger.error("Other Exception");
			throw new CensusAnalyserException(CensusExceptionType.OTHER_TYPE, E.getMessage());
		}
		
		Iterator<T> objectIterator = csvToBean.iterator();
		Iterable<T> objIterable = () -> objectIterator;
		countEntries = (int) StreamSupport.stream(objIterable.spliterator(), false).count();
		logger.info("Total Entries: " + countEntries);
		reader.close();
		return countEntries;

	}
	
	private static void validateInputObjectType(Object myClass) throws CensusAnalyserException {
		if (!(myClass.equals(CSVStateCensus.class) || myClass.equals(CSVStateCode.class))) {
			throw new CensusAnalyserException(CensusExceptionType.INCORRECT_TYPE, "Incorrect Type");
		}
	}

}
