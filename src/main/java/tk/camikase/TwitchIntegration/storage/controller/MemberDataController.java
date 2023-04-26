package tk.camikase.TwitchIntegration.storage.controller;

import org.bukkit.Bukkit;

import tk.camikase.TwitchIntegration.database.IDatabaseHelper;
import tk.camikase.TwitchIntegration.database.connector.StorageDatabaseConnector;
import tk.camikase.TwitchIntegration.storage.model.MemberModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class MemberDataController {
    private final StorageDatabaseConnector storageDatabaseConnector;

    private final static String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS tblUsers (" +
            "minecraft_uuid VARCHAR(36), " +
            "twitch_id VARCHAR(10)" +
            ");";
    private final static String ACCOUNT_LINK_QUERY = "INSERT INTO tblUsers(minecraft_uuid, twitch_id) VALUES ('%s', '%s')";
    private final static String ACCOUNT_UNLINK_QUERY = "DELETE FROM tblUsers WHERE twitch_id = %s";
    private final static String ACCOUNT_GET_UUID_QUERY = "SELECT minecraft_uuid, twitch_id FROM tblUsers WHERE minecraft_uuid = '%s'";
    private final static String ACCOUNT_GET_TWITCH_QUERY = "SELECT minecraft_uuid, twitch_id FROM tblUsers WHERE twitch_id = '%s'";

    private final static String TABLE_CREATION_ERROR = "Ha ocurrido un error al crear la tabla de usuarios.";

    public MemberDataController(final IDatabaseHelper databaseHelper) {
        storageDatabaseConnector = databaseHelper.getStorageDatabaseConnector();

        if (!initializeTable()) {
            Bukkit.getLogger().severe(TABLE_CREATION_ERROR);
            Bukkit.shutdown();
        }
    }

    public boolean initializeTable() {
        try {
            storageDatabaseConnector.query(CREATE_TABLE_QUERY);
            return true;
        } catch (final Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void linkAccount(final UUID uuid, final String twitchId) {
        try {
            storageDatabaseConnector.queryAsync(String.format(ACCOUNT_LINK_QUERY, uuid, twitchId));
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }

    public void unlinkAccount(final String twitchId) {
        try {
            storageDatabaseConnector.queryAsync(String.format(ACCOUNT_UNLINK_QUERY, twitchId));
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }

    public MemberModel getMemberByUniqueId(final UUID uuid) {
        try (final ResultSet resultSet = storageDatabaseConnector.queryResult(String.format(ACCOUNT_GET_UUID_QUERY, uuid))) {
            final MemberModel memberModel = new MemberModel(uuid);

            if (resultSet.next()) {
                memberModel.setTwitchId(resultSet.getString("twitch_id"));

                return memberModel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public MemberModel getMemberByTwitchId(final String twitchId) {
        try (final ResultSet resultSet = storageDatabaseConnector.queryResult(String.format(ACCOUNT_GET_TWITCH_QUERY, twitchId))) {
            if (resultSet.next()) {
                final MemberModel memberModel = new MemberModel(UUID.fromString(resultSet.getString("minecraft_uuid")));
                memberModel.setTwitchId(resultSet.getString("twitch_id"));

                return memberModel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<MemberModel> getMembersByUniqueId(final UUID uuid) {
        final List<MemberModel> memberModels = new ArrayList<>();

        try (final ResultSet resultSet = storageDatabaseConnector.queryResult(String.format(ACCOUNT_GET_UUID_QUERY, uuid))) {
            final MemberModel memberModel = new MemberModel(uuid);

            while (resultSet.next()) {
                memberModel.setTwitchId(resultSet.getString("twitch_id"));

                memberModels.add(memberModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberModels;
    }

    public List<MemberModel> getMembersByTwitchId(final String twitchId) {
        final List<MemberModel> memberModels = new ArrayList<>();

        try (final ResultSet resultSet = storageDatabaseConnector.queryResult(String.format(ACCOUNT_GET_TWITCH_QUERY, twitchId))) {
            while (resultSet.next()) {
                final MemberModel memberModel = new MemberModel(UUID.fromString(resultSet.getString("minecraft_uuid")));
                memberModel.setTwitchId(resultSet.getString("twitch_id"));

                memberModels.add(memberModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberModels;
    }
}
