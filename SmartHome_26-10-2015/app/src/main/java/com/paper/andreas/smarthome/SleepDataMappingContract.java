package com.paper.andreas.smarthome;

import android.provider.BaseColumns;

public class SleepDataMappingContract {
	
	 public SleepDataMappingContract() {}
	 
	  /* Inner class that defines the table contents */
	  public static abstract class SleepDataEntry implements BaseColumns {
		  public static final String TABLE_NAME = "activity";
		  public static final String COLUMN_NAME_PERSON_ID = "person_id";
		  public static final String COLUMN_NAME_END_TIME = "end_time";
		  public static final String COLUMN_NAME_START_TIME = "start_time";
		  public static final String COLUMN_NAME_TYPE = "type";
		  public static final String COLUMN_NAME_DATE = "date";
		  public static final String COLUMN_NAME_DURATION = "duration";
		  public static final String COLUMN_NAME_ISHIGHLEVEL = "is_high_level";
		  public static final String COLUMN_NAME_PLAUSABILITY = "plausability";
		  public static final String COLUMN_NAME_PROVIDER = "provider";
	  }

}
