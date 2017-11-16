package com.neurotechnology.Fingers;

import java.util.EnumSet;

/// <summary>Specifies type of ridge counts contained in NFRecord.</summary>
/// <remarks>Extracted template with EightNeighborsWithIndexes parameter is bigger than the template extracted with EightNeighbors parameter. Templates extracted with EightNeighborsWithIndexes parameter is faster than the templates extracted with EightNeighbors parameter. Extracted template with FourNeighborsWithIndexes parameter is bigger than the template extracted with FourNeighbors parameter. Templates extracted with FourNeighborsWithIndexes parameter is faster than the templates extracted with FourNeighbors parameter.</remarks>
public enum RidgeCountsType {
	None (0, "None"), //< The NFRecord does not contain ridge counts.
	FourNeighbors (1, "Four neighbors"), //< The NFRecord contains ridge counts to closest minutia in each of the four sectors of each minutia. First sector starts at minutia angle.
	EightNeighbors (2, "Eight neighbors"), //< The NFRecord contains ridge counts to closest minutia in each of the eight sectors of each minutia. First sector starts at minutia angle.
	FourNeighborsWithIndexes (5, "Four neighbors with indexes"), //< The NFRecord contains ridge counts to four neighbors of each minutia.
	EightNeighborsWithIndexes (6, "Eight neighbors with indexes"), //< The NFRecord contains ridge counts to eight neighbors of each minutia.
	Unspecified (128 + 4, "Unspecified"); //< For internal use.
	
	private final int index;
	private final String desc;
	RidgeCountsType(int index, String desc)
	{
		this.index = index;
		this.desc = desc;
	}
	
	public int eval() {
		return index;
	}
	
	public String toString() {
		return desc;
	}
	
	public static RidgeCountsType parse(int value) throws Exception {
		for (RidgeCountsType it : EnumSet.allOf(RidgeCountsType.class)) {
			if (it.index == value) {
				return it;
			}
		}
		throw new Exception("value out of bounds");
	}
}
