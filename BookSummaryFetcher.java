package com.bookrecommendation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookSummaryFetcher {

    private static final String API_URL = "https://openlibrary.org/search.json?title=";
    private static final String WORKS_URL = "https://openlibrary.org";

    public static String getBookSummary(String bookTitle) {
        try {
            String jsonResponse = fetchJsonFromUrl(API_URL + bookTitle.replace(" ", "%20"));
            JSONObject jsonObject = new JSONObject(jsonResponse);

            JSONArray docs = jsonObject.getJSONArray("docs");
            if (docs.length() == 0) {
                return "❌ خلاصه‌ای برای این کتاب یافت نشد.";
            }

            String workKey = docs.getJSONObject(0).getString("key");

            String bookDetailsResponse = fetchJsonFromUrl(WORKS_URL + workKey + ".json");
            JSONObject bookDetails = new JSONObject(bookDetailsResponse);

            if (bookDetails.has("description")) {
                Object description = bookDetails.get("description");
                if (description instanceof String) {
                    return (String) description;
                } else if (description instanceof JSONObject) {
                    return ((JSONObject) description).getString("value");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ خطا در دریافت خلاصه کتاب.";
        }

        return "❌ خلاصه‌ای برای این کتاب یافت نشد.";
    }

    private static String fetchJsonFromUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }
}
