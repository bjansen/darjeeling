import ceylon.time {
    DateTime,
    now
}

shared class TimestampedModel() {
    shared variable DateTime createdAt = now().dateTime();
    shared variable DateTime updatedAt = now().dateTime();
}