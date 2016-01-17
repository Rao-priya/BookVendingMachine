package db;

import java.sql.Connection;

public class ConnectionFactory {

	public Connection createSqlConnection(String type) {
		switch (type) {
		case "mysql":

			return MySqlConnection.getConnection().createConnection();

		case "oracle":
			break;
		}
		return null;

	}

}
