package com.dstevens.time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateToLocalDateTransformer {

	private static final ZoneId UTC = ZoneId.of("Z");
	
	public static LocalDate asLocalDateInUTC(Date date) {
		return date.toInstant().atZone(UTC).toLocalDate();
	}
	
	public static LocalDate asLocalDateIn(Date date, ZoneId timeZone) {
		return date.toInstant().atZone(timeZone).toLocalDate();
	}
}
