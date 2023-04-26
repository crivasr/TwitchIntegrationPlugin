package tk.camikase.TwitchIntegration.database;

import lombok.Getter;
import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;
import tk.camikase.TwitchIntegration.database.connector.StorageDatabaseConnector;
import tk.camikase.TwitchIntegration.database.sql.SQLStorageDatabase;

public final class DatabaseHelper implements IDatabaseHelper {
    @Getter private final StorageDatabaseConnector storageDatabaseConnector;

    public DatabaseHelper(final TwitchIntegrationPlugin twitchIntegrationPlugin) {
        this.storageDatabaseConnector = new SQLStorageDatabase(twitchIntegrationPlugin.getPluginConfig(), twitchIntegrationPlugin.getExecutorService());
    }

    @Override
    public void init() {
        storageDatabaseConnector.connect();
    }

    @Override
    public void shutdown() {
        storageDatabaseConnector.disconnect();
    }
}
