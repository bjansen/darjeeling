import com.github.bjansen.gyokuro.json {
	CustomSerializer
}
import ceylon.time {
	DateTime
}
import ceylon.json {
	Builder
}

object dateTimeSerializer satisfies CustomSerializer {
	
	shared actual void serialize(Object obj, Builder builder) {
		if (is DateTime obj) {
			builder.onString(obj.string);
		}
	}
	
	shared actual Boolean supports(Object obj) {
		return (obj is DateTime);
	}
}