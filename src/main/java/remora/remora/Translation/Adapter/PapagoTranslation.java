package remora.remora.Translation.Adapter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import remora.remora.Exception.TranslateException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import java.util.HashMap;
import java.io.InputStream;

@Component
public class PapagoTranslation implements TranslationAdapter {
    @Value("${papago.id}")
    private String clientId;
    @Value("${papago.pw}")
    private String clientSecret;

    private Logger log = LoggerFactory.getLogger(getClass());

    public String translate(String originText) throws Exception {
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;
        text = URLEncoder.encode(originText, StandardCharsets.UTF_8);

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String responseBody = post(apiURL, requestHeaders, text);
        log.info("Response Body = {}", responseBody);

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseBody);
        JSONObject jsonObj = (JSONObject) obj;
        return (String) ((JSONObject) ((JSONObject) jsonObj.get("message")).get("result")).get("translatedText");
    }

    private String post(String apiUrl, Map<String, String> requestHeaders, String text) {
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=en&target=ko&text=" + text;
        try {
            con.setRequestMethod("POST");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
            } else {
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new TranslateException("API Request or Response fail");
        } finally {
            con.disconnect();
        }
    }

    private HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new TranslateException("Invalid API URL. Request URL : " + apiUrl);
        } catch (IOException e) {
            throw new TranslateException("Connection fail. Request URL : " + apiUrl);
        }
    }

    private String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new TranslateException("API request read fail");
        }
    }
}
