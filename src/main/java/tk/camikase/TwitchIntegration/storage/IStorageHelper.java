package tk.camikase.TwitchIntegration.storage;

import tk.camikase.TwitchIntegration.storage.controller.MemberDataController;

public interface IStorageHelper {
    MemberDataController getMemberDataController();
}
