package tk.camikase.TwitchIntegration.bridges;

import java.io.IOException;

import tk.camikase.TwitchIntegration.https.HttpsRequests;
import tk.camikase.TwitchIntegration.https.Response;

public final class TwitchBridge {
    public Boolean isSub(String twitchId, String channelId) throws IOException {
        Response resp = HttpsRequests.get("https://luisardito.camikase.tk/checksub?id=" + twitchId + "&channel=" + channelId, null);
        return resp.code == 202;
    }
}
