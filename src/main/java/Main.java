import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient =
                HttpClientBuilder.create().setUserAgent("My Test Service").setDefaultRequestConfig(RequestConfig.custom()
                                .setConnectTimeout(5000)
                                .setSocketTimeout(30000)
                                .setRedirectsEnabled(false)
                                .build())
                        .build();
        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
        CloseableHttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        filteredByValueOfUpvotes(jsonToList(body));
    }

    public static List<FactOfCats> jsonToList(String json) {
        List<FactOfCats> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            for (Object fact : jsonArray) {
                FactOfCats factOfCats = gson.fromJson(String.valueOf(fact), FactOfCats.class);
                list.add(factOfCats);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void filteredByValueOfUpvotes(List<FactOfCats> inputValues) {
        inputValues.stream()
                .filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0)
                .forEach(System.out::println);

    }
}

