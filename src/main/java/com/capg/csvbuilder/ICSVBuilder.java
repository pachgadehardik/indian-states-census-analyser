package com.capg.csvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<T> {
	public Iterator<T> getCSVFileIterator(Reader reader, Class myClass) throws CSVBuilderException;
    public List<T> getCSVFileList(Reader reader, Class myClass) throws CSVBuilderException;
}
