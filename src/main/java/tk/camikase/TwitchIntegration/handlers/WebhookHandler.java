package tk.camikase.TwitchIntegration.handlers;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpsExchange;
import com.sun.net.httpserver.HttpHandler;

import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;
import tk.camikase.TwitchIntegration.HTTPS.HttpsRequests;
import tk.camikase.TwitchIntegration.utils.Crypto;

public class WebhookHandler implements HttpHandler {
    String secretKey = null;
    static private WebhookHandler webhookHandlerInstance = null;

    public WebhookHandler(String secretKey) {
        this.secretKey = secretKey;
        webhookHandlerInstance = this;
    }

    public static WebhookHandler getInstance() {
        return webhookHandlerInstance;
    }

    public void notifyState(String state) {
        ConsoleCommandSender logger = TwitchIntegrationPlugin.getInstance().getServer().getConsoleSender();
        logger.sendMessage(ChatColor.GREEN + secretKey);

        String httpsURL = "https://luisardito.camikase.tk/notify";
        try {

            int port = TwitchIntegrationPlugin.getInstance().getHttpsServer().getPort();
            String body = "state=" + URLEncoder.encode(state, "UTF-8");
            body += "&";
            body += "secret=" + URLEncoder.encode(secretKey, "UTF-8");
            body += "&";
            body += "port=" + URLEncoder.encode(Integer.toString(port), "UTF-8");

            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/x-www-form-urlencoded");

            HttpsRequests.post(httpsURL, body, headers);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void handle(HttpExchange tHttp) throws IOException {
        HttpsExchange t = (HttpsExchange) tHttp;
        Headers headers = t.getRequestHeaders();
        String method = t.getRequestMethod();

        if (!method.equalsIgnoreCase("post")) {
            respond(t, "method not suported", 405);
            return;
        }

        try {
            String reqSignature = headers.getFirst("hmacsha256-signature");
            String timestampStr = headers.getFirst("timestamp");

            if (timestampStr == null || timestampStr.equals("")) {
                respond(t, "Missing timestamp header", 400);
                return;
            }

            long timestamp = Long.parseLong(timestampStr);
            long currentTimeMillis = System.currentTimeMillis();
            int tenMinutes = 1000 * 60 * 10;

            // give a 20 minutes window for the request (10 before 10 after) in case of
            // time mismatch or lag
            if (timestamp < currentTimeMillis - tenMinutes || timestamp > currentTimeMillis + tenMinutes) {
                respond(t, "Invalid timestamp header", 400);
                return;
            }

            String body = getBody(t);
            String signature = Crypto.hmacSha256(secretKey, body + timestampStr);
            if (!signature.equals(reqSignature)) {
                respond(t, "Invalid Signature", 403);
                return;
            }

            String twitchId = getProperty(body, "twitchId");
            String state = getProperty(body, "state");

            if (twitchId == null || state == null || twitchId.equals("") || state.equals("")) {
                respond(t, "Missing property", 400);
                return;
            }
            respond(t, "Valid Signature", 200);
            TwitchIntegrationPlugin.getInstance().linkAccounts(twitchId, state);
            return;
        } catch (Exception e) {
            respond(t, "Internal Server Error", 500);
        }
    }

    private static void respond(HttpsExchange t, String response, int status) throws IOException {
        t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        t.sendResponseHeaders(status, response.getBytes().length);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static String getBody(HttpsExchange t) throws IOException {
        InputStreamReader isr = new InputStreamReader(t.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        br.close();
        isr.close();

        return buf.toString();
    }

    private static String getProperty(String body, String property) {
        try {
            JsonObject bodyJson = JsonParser.parseString(body).getAsJsonObject();
            JsonElement prop = bodyJson.get(property);
            return prop == null ? "" : prop.getAsString();
        } catch (Exception e) {
            return "";
        }
    }
}
