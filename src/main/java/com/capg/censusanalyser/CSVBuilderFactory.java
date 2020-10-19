package com.capg.censusanalyser;

import com.capg.csvbuilder.OpenCSVBuilder;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}

}
