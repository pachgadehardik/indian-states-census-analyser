package com.capg.censusanalyser;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Driver 
{
    public static void main( String[] args )
    {
    	final String INDIA_STATE_CENSUS_FILE = "H:\\Capgemini\\Capg_Training\\census-analyser\\IndiaStateCensusData - IndiaStateCensusData.csv";
        try {
			StateCensusAnalyser.readCSVDataFile(INDIA_STATE_CENSUS_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
