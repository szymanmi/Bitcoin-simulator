package repository;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Bitcoin {
	public static double getBitcoinValue() throws IOException {
		String url = "https://bitmarket24.pl/api/BTC_PLN/status.json";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
		in.close();

		//Wypisanie stringa (zawartości strony)
		//System.out.println(response.toString());

		//Czytanie zawartości JSON i wypisanie je
		JSONObject myResponse = new JSONObject(response.toString());

		return Double.parseDouble(myResponse.getString("last"));
	}
}
