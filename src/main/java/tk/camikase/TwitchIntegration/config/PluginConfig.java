package tk.camikase.TwitchIntegration.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public final class PluginConfig {
    private final String databaseAddress;
    private final int databasePort;
    private final String database;
    private final String username;
    private final String password;

    private final String broadcasterId;

    private final int httpServerPort;
}
