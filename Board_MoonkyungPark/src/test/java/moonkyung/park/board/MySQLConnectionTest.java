package moonkyung.park.board;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	// MySQL Driver 5 이하->com.mysql.jdbc.Driver

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/board_moonkyung";
	// MySQL
	// 5.7이상->jdbc:mysql://127.0.0.1:3306/book_ex?useSSL=false&serverTimezone=UTC

	private static final String USER = "moonkyung";

	private static final String PW = "moonkyung";

	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);

		try (Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
