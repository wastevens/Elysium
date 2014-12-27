package com.dstevens.time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateTimeUtilities {

	private static final ZoneId UTC = ZoneId.of("Z");
	
	public static LocalDate asLocalDateInUTC(Date date) {
		return date.toInstant().atZone(UTC).toLocalDate();
	}
	
	public static LocalDate asLocalDateIn(Date date, ZoneId timeZone) {
		return date.toInstant().atZone(timeZone).toLocalDate();
	}
	
	public static Date fromLocalDateInUTC(LocalDate date) {
		return Date.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
	}
	
	public static Date fromLocalDateIn(LocalDate date, ZoneOffset timeZone) {
		return Date.from(date.atStartOfDay().toInstant(timeZone));
	}
}
