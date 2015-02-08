import org.jooq {
	Converter
}
import ceylon.time {
	DateTime,
	Instant
}
import java.sql {
	Timestamp
}
import java.lang {
	Class
}
import ceylon.interop.java {
	javaClass
}

shared class DateTimeConverter() satisfies Converter<Timestamp, DateTime> {
	shared actual DateTime? from(Timestamp? t) => if (exists t) then Instant(t.time).dateTime() else null;
	
	shared actual Class<Timestamp> fromType() => javaClass<Timestamp>();
	
	shared actual Timestamp? to(DateTime? u) => if (exists u) then Timestamp(u.instant().millisecondsOfEpoch) else null;
	
	shared actual Class<DateTime> toType() => javaClass<DateTime>();
	
}
