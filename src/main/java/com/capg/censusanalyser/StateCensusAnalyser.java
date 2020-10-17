package com.capg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	

	public static int readCSVDataFile(String dataFile) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(dataFile));

		
		CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(reader)
				.withType(CSVStateCensus.class).withIgnoreLeadingWhiteSpace(true).build();
		
		Iterator<CSVStateCensus> csvStateIterator = csvToBean.iterator();
		int countEntries = 0;
		while(csvStateIterator.hasNext()) {
			CSVStateCensus csvStateCensus = csvStateIterator.next();
			countEntries++;
		}
		System.out.println("Total Entries: "+countEntries);
		return countEntries;
	}
	
	

}
