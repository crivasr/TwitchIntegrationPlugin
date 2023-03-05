package tk.camikase.TwitchIntegration.HTTPS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpsRequests {
    public static Response get(String url, Map<String, String> headers) throws IOException {
        URL myurl = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();

        if (headers != null)
            headers.forEach((key, value) -> {
                con.setRequestProperty(key, value);
            });

        con.setRequestMethod("GET");
        con.setDoInput(true);

        DataInputStream input = new DataInputStream(con.getInputStream());

        StringBuilder sb = new StringBuilder();

        for (int c = input.read(); c != -1; c = input.read())
            sb.append(c);

        input.close();

        Response res = new Response();
        res.content = sb.toString();
        res.code = con.getResponseCode();

        return res;
    }

    public static Response post(String url, String body, Map<String, String> headers) throws IOException {
        URL myurl = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-length", String.valueOf(body.getBytes().length));

        if (headers != null)
            headers.forEach((key, value) -> {
                con.setRequestProperty(key, value);
            });

        con.setDoOutput(true);
        con.setDoInput(true);

        DataOutputStream output = new DataOutputStream(con.getOutputStream());

        output.writeBytes(body);
        output.close();

        DataInputStream input = new DataInputStream(con.getInputStream());
        StringBuilder sb = new StringBuilder();

        for (int c = input.read(); c != -1; c = input.read())
            sb.append(c);

        input.close();

        Response res = new Response();
        res.content = sb.toString();
        res.code = con.getResponseCode();

        return res;
    }
}
