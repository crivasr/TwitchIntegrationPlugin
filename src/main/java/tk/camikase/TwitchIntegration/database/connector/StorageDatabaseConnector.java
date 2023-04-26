package tk.camikase.TwitchIntegration.database.connector;

import com.google.common.util.concurrent.ListenableFuture;

import java.sql.Connection;
import java.sql.ResultSet;

public interface StorageDatabaseConnector {
    void connect();
    void disconnect();

    boolean isConnected();

    void closeConnection(final Connection connection);

    void query( final String query);

    void queryAsync( final String query);

    ResultSet queryResult(final String query);

    ListenableFuture<ResultSet> queryResultAsync(final String query);
}
