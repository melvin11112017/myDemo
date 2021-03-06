package com.weihan.scanner.models;

import com.weihan.scanner.BaseMVP.IBaseModel;
import com.weihan.scanner.WApp;
import com.weihan.scanner.entities.PhysicalInvtAddon;
import com.weihan.scanner.entities.Polymorph;

public class Func2ModelImpl implements IBaseModel {

    private Func2ModelImpl() {
    }

    public static Polymorph<PhysicalInvtAddon, PhysicalInvtAddon> createPoly(String itemno, String bincode, String locationCode) {

        PhysicalInvtAddon addon = new PhysicalInvtAddon();

        addon.setItemNo(itemno);
        addon.setLoaction_Code(locationCode);
        addon.setBinCode(bincode);
        addon.setTerminalID(WApp.userInfo.getUserid());
        addon.setCreationDate(AllFuncModelImpl.getCurrentDatetime());
        addon.setQuantity("1");

        return new Polymorph<>(addon, null, Polymorph.State.UNCOMMITTED);
    }

}
