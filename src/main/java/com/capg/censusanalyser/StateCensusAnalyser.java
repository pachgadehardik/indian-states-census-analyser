package com.capg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import com.capg.censusanalyser.CensusAnalyserException.CensusExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	private final static String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
	private final String INCORRECT_DATA_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IncorrectFile.txt";

	public static int readCSVDataFile(String dataFile) throws IOException, CensusAnalyserException {
		int countEntries = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(dataFile));

			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(reader)
					.withType(CSVStateCensus.class).withIgnoreLeadingWhiteSpace(true).build();

			Iterator<CSVStateCensus> csvStateIterator = csvToBean.iterator();
			 
			while (csvStateIterator.hasNext()) {
				CSVStateCensus csvStateCensus = csvStateIterator.next();
				countEntries++;
			}
			System.out.println("Total Entries: " + countEntries);
			return countEntries;
		}
		catch(NoSuchFileException e) {
			throw new CensusAnalyserException(CensusExceptionType.FILE_NOT_FOUND_TYPE, "NO FILE FOUND!!");
		}
		catch (Exception E) {
			E.printStackTrace();
		}
		return countEntries;
	}

}
