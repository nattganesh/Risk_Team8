package com.risk.MapUtill;

import java.awt.Point;

/**
 * Card Enums for card exchange.
 * @author DJ
 * @version 1.0.0
 */
public class MapConstant {
	public static final int numbNorthAmerica = 9;
	public static final int numbSouthAmerica = 4;
	public static final int numbAfrica = 6;
	public static final int numbAsia = 12;
	public static final int numbEurope = 7;
	public static final int numbAustralia = 4;
	
	public static final double [][] NorthAmerica = {
	   		{1,1},
    		{2,0.5}, {2,1.5}, {2,2.5},
    		{2.5,3.5},
    		{3, 1.5} , {3, 2.5} , {4,2},
    		{6, 1},
    			};
	public static final double [][] SouthAmerica = {
			{3,7},
			{4,7},
			{3.5,8},
			{4.5,8},
	};
	public static final double [][] Africa = {
			{8,5},	
			{7.5,6},	
			{8.5,6},	
			{8,7},
			{9,7},	
			{11,8}
	};

	public static final double [][] Europe = {
			{8,2},
			{11,2.5},
			{12,3},
			{10,4},
			{10,5},
			{11,4},
			{11,5},
			
	};
	public static final double [][] Asia ={ 
			{12,4},
			{12,5},
			{13,2},
			{13,3},
			{13,4},
			{13,5},
			{14,1.5},
			{14,2.5},
			{14,3.5},
			{14,4.5},
			{15, 3},
			{16, 5},
			
	};
	public static final double [][] Australia = {
			{17, 7},
			{19,7},
			{18, 9},
			{19,9},
	};
}