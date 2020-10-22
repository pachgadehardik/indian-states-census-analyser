package com.capg.censusanalyser;

import java.util.Comparator;

public enum SortingaFieldType {

	
	STATENAME {
		@Override
			public Comparator<CSVStateCensus> getComparator(){ 
			Comparator<CSVStateCensus> comparator = Comparator.comparing(CSVStateCensus::getState);
			return comparator;
		}
	},

	POPULATION{
		@Override
		public Comparator<CSVStateCensus> getComparator(){
			Comparator<CSVStateCensus> comparator = Comparator.comparing(census -> Integer.parseInt(census.getPopulation()));
			return comparator.reversed();
		}
		
		},
	DENSITY{
			@Override
			public Comparator<CSVStateCensus> getComparator(){
				Comparator<CSVStateCensus> comparator = Comparator.comparing(census -> Integer.parseInt(census.getDensity()));
				return comparator.reversed();
			}
		},
	AREA{
			@Override
			public Comparator<CSVStateCensus> getComparator(){
				Comparator<CSVStateCensus> comparator = Comparator.comparing(census -> Integer.parseInt(census.getArea()));
				return comparator.reversed();
			}
		};
	
	public abstract Comparator<CSVStateCensus> getComparator();
	
}
