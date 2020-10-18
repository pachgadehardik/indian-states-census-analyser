package com.capg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

import com.capg.censusanalyser.CensusAnalyserException.CensusExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	
	private final static String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
	private final String INCORRECT_DATA_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IncorrectFile.txt";

	public static <T> int readCSVDataFile(String dataFile, Class<T> myClass)
			throws IOException, CensusAnalyserException {
		int count = 0;				
		int countEntries =0;

		try {
			Reader reader = Files.newBufferedReader(Paths.get(dataFile));
			validateInputObjectType(myClass);

			CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType((Class) myClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<T> csvToBean = csvToBeanBuilder.build();
			Iterator<T> objectIterator = csvToBean.iterator();
 			while (objectIterator.hasNext()) {	
				if(objectIterator.next()!=null) 
					countEntries++;
			}
			System.out.println("Total Entries: " + countEntries);
			reader.close();
			return countEntries;
		}

		catch (CensusAnalyserException e) {
			throw new CensusAnalyserException(CensusExceptionType.INCORRECT_TYPE, e.getMessage());
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(CensusExceptionType.FILE_NOT_FOUND_TYPE, "NO FILE FOUND!!");
		} catch (RuntimeException r) {
			throw new CensusAnalyserException(CensusExceptionType.DELIMITER_OR_HEADER_TYPE,
					"Runtime base Exception reagarding Delimiter or any Header Issue: " + r.getMessage());
		} catch (Exception E) {
			throw new CensusAnalyserException(CensusExceptionType.OTHER_TYPE, E.getMessage());
		}
	}

	private static void validateInputObjectType(Object myClass) throws CensusAnalyserException {
		if (!myClass.equals(CSVStateCensus.class)) {
			throw new CensusAnalyserException(CensusExceptionType.INCORRECT_TYPE, "Incorrect Type");
		}
	}

}
