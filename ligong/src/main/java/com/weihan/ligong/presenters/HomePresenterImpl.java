package com.weihan.ligong.presenters;

import com.weihan.ligong.BaseMVP.BasePresenter;
import com.weihan.ligong.models.HomeIconModelImpl;
import com.weihan.ligong.mvpviews.HomeMvpView;

import java.util.List;
import java.util.Map;

public class HomePresenterImpl extends BasePresenter<HomeMvpView> implements HomeIconModelImpl.Callback {

    private HomeIconModelImpl model = new HomeIconModelImpl();

    public void getData() {
        model.generateDataList(this);
    }

    public void processClickEvent(int position) {
        model.findClass(this, position);
    }

    @Override
    public void onListComplete(List<Map<String, Object>> data) {
        getView().initGridAdapter(data);
    }

    @Override
    public void onClassFound(Class<?> clazz) {
        getView().toCorrespondingActivity(clazz);
    }


}
