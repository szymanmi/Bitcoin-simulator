package app;

import org.junit.Test;
import model.Bitcoin;
import model.DataBaseJDBC;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MavenTest {

	@Test
	public void checkUserLogin() {
		DataBaseJDBC baza = new DataBaseJDBC();
		int result = baza.checkUser("yyy", "yyy");
		assertTrue(result >= 0);
	}

	@Test
	public void checkgetingUserBTC() {
		DataBaseJDBC baza = new DataBaseJDBC();
		double result = baza.getUserBitcoins(24);
		assertTrue(result == 3);
	}

	@Test
	public void checkGettingBTCValue() throws IOException{
		assertTrue(Bitcoin.getBitcoinValue() > 0);
	}

	@Test
	public void checkgetingUserPLN() {
		DataBaseJDBC baza = new DataBaseJDBC();
		double result = baza.getUserPLN(25);
		assertTrue(result == 123456);
	}

	@Test
	public void checkgetingUserName() {
		DataBaseJDBC baza = new DataBaseJDBC();
		String result = baza.getUserName(25);
		assertEquals(result , "mavenjunit2");
	}
}