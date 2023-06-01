package tk.camikase.TwitchIntegration.https;

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
            headers.forEach(con::setRequestProperty);

        con.setRequestMethod("GET");
        con.setDoInput(true);

        final DataInputStream input = new DataInputStream(con.getInputStream());

        final StringBuilder sb = new StringBuilder();

        for (int c = input.read(); c != -1; c = input.read()) sb.append(c);

        input.close();

        final Response res = new Response();

        res.content = sb.toString();
        res.code = con.getResponseCode();

        return res;
    }

    public static Response post(String url, String body, Map<String, String> headers) throws IOException {
        final URL myurl = new URL(url);
        final HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-length", String.valueOf(body.getBytes().length));

        if (headers != null) headers.forEach(con::setRequestProperty);

        con.setDoOutput(true);
        con.setDoInput(true);

        final DataOutputStream output = new DataOutputStream(con.getOutputStream());

        output.writeBytes(body);
        output.close();

        final DataInputStream input = new DataInputStream(con.getInputStream());
        final StringBuilder sb = new StringBuilder();

        for (int c = input.read(); c != -1; c = input.read())
            sb.append(c);

        input.close();

        final Response res = new Response();

        res.content = sb.toString();
        res.code = con.getResponseCode();

        return res;
    }
}
