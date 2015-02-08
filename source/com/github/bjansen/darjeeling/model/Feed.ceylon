import ceylon.time {
	DateTime,
	now
}

shared class Feed() {
	shared variable Integer id = 0;
	shared variable String url = "";
	shared variable DateTime lastChedkedDate = now().dateTime();
}