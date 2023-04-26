package tk.camikase.TwitchIntegration.database.sql;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import org.bukkit.Bukkit;
import tk.camikase.TwitchIntegration.config.PluginConfig;
import tk.camikase.TwitchIntegration.database.connector.StorageDatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public final class SQLStorageDatabase implements StorageDatabaseConnector {
    private final ListeningExecutorService listeningExecutorService;

    private final HikariConfig hikariConfig;
    private HikariDataSource hikariDataSource;

    private final static String CONNECT_SUCCESSFUL = "[%s] Connected to %s%n";
    private final static String CONNECTION_ERROR = "Could not establish MySQL connection pool! Please check your plugin/MariaDB SQL Server settings.";

    public SQLStorageDatabase(final PluginConfig pluginConfig, final ListeningExecutorService executorService) {
        this.listeningExecutorService = executorService;

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(
                "jdbc:mysql://" +
                        pluginConfig.getDatabaseAddress() +
                        ":" + pluginConfig.getDatabasePort() +
                        "/" + pluginConfig.getDatabase()
        );

        hikariConfig.setUsername(pluginConfig.getUsername());
        hikariConfig.setPassword(pluginConfig.getPassword());

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("useServerPrepStmts", "true");

        hikariConfig.setPoolName("TwitchIntegrationPool");
        hikariConfig.setConnectionTestQuery("SELECT 1");

        this.hikariConfig = hikariConfig;
    }

    @Override
    public void connect() {
        try {
            hikariDataSource = new HikariDataSource(hikariConfig);
        } catch (final HikariPool.PoolInitializationException e) {
            e.printStackTrace();

            hikariDataSource = null;

            System.out.println(CONNECTION_ERROR);

            return;
        }

        try {
            hikariDataSource.getConnection();
        } catch (final SQLException e) {
            e.printStackTrace();

            System.out.println(CONNECTION_ERROR);

            return;
        }

        Bukkit.getLogger().log(Level.SEVERE, String.format(CONNECT_SUCCESSFUL, hikariConfig.getPoolName(), hikariConfig.getJdbcUrl()));
    }

    @Override
    public void disconnect() {
        if (isConnected()) hikariDataSource.close();
    }

    @Override
    public boolean isConnected() {
        return hikariDataSource != null;
    }

    @Override
    public void closeConnection(final Connection connection) {
        try {
            connection.close();
        } catch (SQLException closeException) {
            closeException.printStackTrace();
        }
    }

    @Override
    public void query(final String query) {
        Connection connection = null;
        try {
            connection = hikariDataSource.getConnection();
            final PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
    }

    @Override
    public void queryAsync(final String query) {
        listeningExecutorService.submit(() -> query(query));
    }

    @Override
    public ResultSet queryResult(final String query) {
        Connection connection = null;
        try {
            connection = hikariDataSource.getConnection();
            final PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException queryException) {
            queryException.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
    }

    @Override
    public ListenableFuture<ResultSet> queryResultAsync(final String query) {
        return listeningExecutorService.submit(() -> queryResult(query));
    }
}
