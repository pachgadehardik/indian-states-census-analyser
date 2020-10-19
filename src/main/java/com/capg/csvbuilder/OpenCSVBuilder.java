package com.capg.csvbuilder;

import java.io.Reader;
import java.util.Iterator;

import com.capg.censusanalyser.CSVStateCensus;
import com.capg.censusanalyser.CSVStateCode;
import com.capg.censusanalyser.ICSVBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<T> implements ICSVBuilder {

	public Iterator<T> getCSVFileIterator(Reader reader, Class myClass) throws CSVBuilderException {
		try {
			validateInputObjectType(myClass);
			CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType((Class) myClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<T> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CSVBuilderException(e.getMessage());
		}
	}

	private static void validateInputObjectType(Object myClass) throws CSVBuilderException {
		if (!(myClass.equals(CSVStateCensus.class) || myClass.equals(CSVStateCode.class))) {
			throw new CSVBuilderException("Incorrect Type of Class");
		}
	}

}
