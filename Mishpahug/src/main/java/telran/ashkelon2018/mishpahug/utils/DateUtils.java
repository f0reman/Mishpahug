package telran.ashkelon2018.mishpahug.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtils {
	
	public static ZoneId zoneIdServer =ZoneId.of("UTC");

	public static ZoneId zoneIdIsrael =ZoneId.of("UTC+2");
	
	
    public static ZonedDateTime millsToZonedDateTime(long m){
//  	ZoneId.systemDefault();
        Instant instant = Instant.ofEpochSecond(m);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneIdIsrael);
        return zonedDateTime;
    }
    
    
    public static long zonedDateTimeToMills(ZonedDateTime zdt ){
        return zdt.toInstant().toEpochMilli();
    }
    
    public static long localDateTimeToMills(LocalDateTime ldt ){
        return ldt.atZone(zoneIdIsrael).toInstant().toEpochMilli();
    }
    
    public static long localDateToMills(LocalDate ldt ){
        return ldt.atTime(0, 0).atZone(zoneIdIsrael).toInstant().toEpochMilli();
    }
}
