package tk.camikase.TwitchIntegration.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.UUID;

public class databaseManager {
    private HikariDataSource hikariDataSource;
    private Connection connection;

    public databaseManager(String JBDCurl, String username, String password) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(JBDCurl);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("autoReconnect", "true");
        config.addDataSourceProperty("leakDetectionThreshold", "true");
        config.addDataSourceProperty("verifyServerCertificate", "false");
        config.addDataSourceProperty("useSSL", "false");
        config.setConnectionTimeout(5000);

        hikariDataSource = new HikariDataSource(config);

        try {
            connection = hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS tblUsers (twitch_id varchar(10), minecraft_uuid varchar(40), PRIMARY KEY (minecraft_uuid))");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unlinkAll(String twitchId) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM tblUsers WHERE twitch_id = ?");

            statement.setString(1, twitchId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Member> getByTwitchId(String twitchId) {
        ArrayList<Member> res = new ArrayList<Member>();

        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM tblUsers WHERE twitch_id = ?");

            statement.setString(1, twitchId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Member member = new Member();
                member.minecraftUUID = UUID.fromString(result.getString("minecraft_uuid"));
                member.twitchId = twitchId;

                res.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Member> getByMCUUID(UUID minecraftUUID) {
        ArrayList<Member> res = new ArrayList<Member>();

        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM tblUsers WHERE minecraft_uuid = ?");

            statement.setString(1, minecraftUUID.toString());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Member member = new Member();
                member.twitchId = result.getString("twitch_id");
                member.minecraftUUID = minecraftUUID;

                res.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public void linkAccounts(String twitchId, UUID minecraftUUID) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "INSERT INTO tblUsers (minecraft_uuid, twitch_id) VALUE (?,?) ON DUPLICATE KEY UPDATE twitch_id = ?");

            statement.setString(1, minecraftUUID.toString());
            statement.setString(2, twitchId);
            statement.setString(3, twitchId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        hikariDataSource.close();
    }

    public Connection getConnection() {
        return connection;
    }
}