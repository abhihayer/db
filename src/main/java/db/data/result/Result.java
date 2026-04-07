package db.data.result;

import db.data.Record;

public record Result<T extends Record> (T result){}
