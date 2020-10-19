package com.capg.censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<T> {
	public Iterator<T> getCSVFileIterator(Reader reader, Class myClass) throws CensusAnalyserException;
}
