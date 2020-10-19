package com.capg.censusanalyser;

import java.io.Reader;
import java.util.Iterator;

import com.capg.censusanalyser.CensusAnalyserException.CensusExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder {

	public <T> Iterator<T> getCSVFileIterator(Reader reader, Class<T> myClass) throws CensusAnalyserException {
		try {
			validateInputObjectType(myClass);
			CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType((Class) myClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<T> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(CensusExceptionType.DELIMITER_OR_HEADER_TYPE, e.getMessage());
		}
	}

	private static void validateInputObjectType(Object myClass) throws CensusAnalyserException {
		if (!(myClass.equals(CSVStateCensus.class) || myClass.equals(CSVStateCode.class))) {
			throw new CensusAnalyserException(CensusExceptionType.INCORRECT_TYPE, "Incorrect Type");
		}
	}

}
