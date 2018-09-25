package com.weihan.ligong.mvpviews;

import android.support.annotation.StringRes;

import com.weihan.ligong.BaseMVP.IBaseView;

public interface SettingsMvpView extends IBaseView {
    void showSettingsParam(String serverIp,
                           String machineCode,
                           String tempArea,
                           String moCodeLength,
                           String warehouseCodeLength,
                           String batchCodeLength,
                           String poCodeLength,
                           String batchCodePrefixLength,
                           String materialCodeLength);

    void showMessage(String msg);

    void showErrorMessage(@StringRes int id);

    void saveToSharedPref(String settingsJson);
}
