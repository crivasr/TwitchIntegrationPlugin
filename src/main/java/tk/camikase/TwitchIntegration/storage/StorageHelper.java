package tk.camikase.TwitchIntegration.storage;

import lombok.AccessLevel;
import lombok.Getter;
import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;
import tk.camikase.TwitchIntegration.storage.controller.MemberDataController;

public final class StorageHelper implements IStorageHelper {
    @Getter(AccessLevel.PUBLIC) private final MemberDataController memberDataController;

    public StorageHelper(final TwitchIntegrationPlugin twitchIntegrationPlugin) {
        memberDataController = new MemberDataController(twitchIntegrationPlugin.getDatabaseHelper());
    }
}

