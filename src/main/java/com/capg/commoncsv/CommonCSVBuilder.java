package com.capg.commoncsv;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.capg.csvbuilder.CSVBuilderException;
import com.capg.csvbuilder.ICSVBuilder;

public class CommonCSVBuilder<T> implements ICSVBuilder<T> {
	private static final Logger logger = LogManager.getLogger(CommonCSVBuilder.class);

	@Override
	public Iterator<T> getCSVFileIterator(Reader reader, Class myClass) throws CSVBuilderException {
		try {
			DOMConfigurator.configure("log4j.xml");
			Iterable<T> records = (Iterable<T>) CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
			logger.info("Inside Common CSV Builder");
			return records.iterator();
		}
		catch(IllegalStateException | IOException e) {
			logger.error(e.getMessage());
			throw new CSVBuilderException(e.getMessage());
		}
	}

	@Override
	public List<T> getCSVFileList(Reader reader, Class myClass) throws CSVBuilderException {
		return null;
	}
		
	
	
}
