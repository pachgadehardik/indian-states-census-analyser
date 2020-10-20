package com.capg.csvbuilder;

import com.capg.commoncsv.CommonCSVBuilder;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}

	public static ICSVBuilder createCommonCSVBuilder(){
		return new CommonCSVBuilder();
	}
}
