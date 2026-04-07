package db.data.result;

import java.util.List;
import db.data.Record;

public record ResultSet<T extends Record>(List<Result<T>> resultSet) {}
