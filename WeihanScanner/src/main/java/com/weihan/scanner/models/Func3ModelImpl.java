package com.weihan.scanner.models;

import com.weihan.scanner.BaseMVP.IBaseModel;
import com.weihan.scanner.WApp;
import com.weihan.scanner.entities.BinContentInfo;
import com.weihan.scanner.entities.Polymorph;
import com.weihan.scanner.entities.WarehousePutAwayAddon;
import com.weihan.scanner.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Func3ModelImpl implements IBaseModel {

    private Func3ModelImpl() {
    }

    public static Polymorph<WarehousePutAwayAddon, BinContentInfo> createPoly(String itemno, String bincode, String locationCode, String quantity) {

        WarehousePutAwayAddon addon = new WarehousePutAwayAddon();

        addon.setItemNo(itemno);
        addon.setBinCode(bincode);
        addon.setLocationCode(locationCode);
        addon.setTerminalID(WApp.userInfo.getUserid());
        addon.setCreationDate(AllFuncModelImpl.getCurrentDatetime());
        addon.setQuantity(quantity);

        return new Polymorph<>(addon, null, Polymorph.State.UNCOMMITTED);
    }

    public static List<BinContentInfo> filtTempReceiptItem(List<BinContentInfo> datas) {
        List<BinContentInfo> tempList = new ArrayList<>();
        for (BinContentInfo info : datas) {
            String quantity = info.getQuantity_Base();
            if (quantity == null || quantity.isEmpty() || !TextUtils.isIntString(quantity))
                continue;
            if (info.isTemp_Receipt() && Integer.valueOf(quantity) != 0)
                tempList.add(info);
        }
        return tempList;
    }
    public static List<Polymorph<WarehousePutAwayAddon, BinContentInfo>> createPolymorphList(List<BinContentInfo> datas, String binCode) {
        List<Polymorph<WarehousePutAwayAddon, BinContentInfo>> polymorphs = new ArrayList<>();
        for (BinContentInfo info : datas) {

            String quantity = info.getQuantity_Base();
            if (quantity == null || quantity.isEmpty() || !TextUtils.isNumeric(quantity))
                continue;
            if (Double.valueOf(quantity) == 0) continue;

            if (!info.isTemp_Receipt()) continue;

            WarehousePutAwayAddon addon = new WarehousePutAwayAddon();
            addon.setItemNo(info.getItem_No());
            addon.setTerminalID(WApp.userInfo.getUserid());
            addon.setQuantity(quantity);
            addon.setBinCode(binCode);
            polymorphs.add(new Polymorph<>(addon, info, Polymorph.State.UNCOMMITTED));
        }
        return polymorphs;
    }


}
