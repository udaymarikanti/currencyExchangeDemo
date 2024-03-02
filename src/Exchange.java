import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exchange {
    private static final String BASE_URL = "https://open.er-api.com/v6/latest/USD";
    private ExchangeCoventer converter;

    public Exchange(ExchangeCoventer converter) {
        this.converter = converter;
    }

    public double convertCurrency(double amount) throws Exception {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode == 200) { // success
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonResponse = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
            reader.close();

            // parse the JSON response and extract the exchange rates
            // for simplicity, we're just assuming that the exchange rate from USD to the target currency is the first one in the list
            String json = jsonResponse.toString();
            String exchangeRateString = json.substring(json.indexOf("\"USD\":") + "\"USD\":".length(), json.indexOf(",", json.indexOf("\"USD\":")));
            double exchangeRate = Double.parseDouble(exchangeRateString);

            // use the exchange rate to convert the amount
            double result = converter.convertCurrency(exchangeRate, amount);
            return result;

        } else {
            System.out.println("Error: unable to connect to the API. Response code: " + responseCode);
            return 0.0;
        }
    }


}
