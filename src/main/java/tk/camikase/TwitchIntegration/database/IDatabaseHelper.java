package tk.camikase.TwitchIntegration.database;

import tk.camikase.TwitchIntegration.database.connector.StorageDatabaseConnector;

public interface IDatabaseHelper {
    void init();
    void shutdown();

    StorageDatabaseConnector getStorageDatabaseConnector();
}
