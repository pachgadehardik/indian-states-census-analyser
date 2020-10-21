package com.capg.csvbuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.capg.censusanalyser.CSVStateCensus;
import com.capg.censusanalyser.CSVStateCode;

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
	public List<T> getCSVFileList(Reader reader, Class myClass) throws CSVBuilderException, IOException {
		 CSVParser csvParser = null;
		csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
//		Iterable<T> records = (Iterable<T>) CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
		List<T> listPojo = new ArrayList<>();
	    Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	    CSVStateCode csvStateCode = null;
	    CSVStateCensus csvStateCensus = null;
		for(CSVRecord csvRecord : csvRecords){
			if (myClass.equals(CSVStateCode.class)) {
				csvStateCode = new CSVStateCode();
				csvStateCode.setSr_no(csvRecord.get("SrNO"));
				csvStateCode.setState(csvRecord.get("State Name"));
				csvStateCode.setTinNumber(csvRecord.get("TIN"));
				csvStateCode.setStateCode(csvRecord.get("StateCode"));
				listPojo.add((T) csvStateCode);
			}
			else {
				csvStateCensus = new CSVStateCensus();
				csvStateCensus.setState(csvRecord.get("State"));
				csvStateCensus.setPopulation(csvRecord.get("Population"));
				csvStateCensus.setArea(csvRecord.get("AreaInSqKm"));
				csvStateCensus.setDensity(csvRecord.get("DensityPerSqKm"));
				listPojo.add((T) csvStateCensus);
			}
			
		}
		return listPojo;
	}
		
	
	
}
