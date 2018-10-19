package com.weihan.ligong.activities;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weihan.ligong.BaseMVP.BaseActivity;
import com.weihan.ligong.R;
import com.weihan.ligong.entities.BinContentInfo;
import com.weihan.ligong.mvpviews.Func11MvpView;
import com.weihan.ligong.presenters.Func11PresenterImpl;
import com.weihan.ligong.utils.AdapterHelper;

import java.util.ArrayList;
import java.util.List;

public class Func11Activity extends BaseActivity<Func11PresenterImpl> implements Func11MvpView, View.OnClickListener {

    EditText etItemNo, etBincode;
    Button buttonItemNo, buttonBincode;
    RecyclerView recyclerView;

    private Func11PresenterImpl.BinContentListAdapter adapter;
    private List<BinContentInfo> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func11);

        findView();
        initWidget();
    }

    @Override
    protected Func11PresenterImpl buildPresenter() {
        return new Func11PresenterImpl();
    }

    @Override
    public void findView() {
        etItemNo = findViewById(R.id.et_func11_itemno);
        etBincode = findViewById(R.id.et_func11_bincode);
        recyclerView = findViewById(R.id.recycler_func11);
        buttonItemNo = findViewById(R.id.button_func11_check0);
        buttonBincode = findViewById(R.id.button_func11_check1);
    }

    @Override
    public void initWidget() {

        buttonItemNo.setOnClickListener(this);
        buttonBincode.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Func11PresenterImpl.BinContentListAdapter(datas);
        AdapterHelper.setAdapterEmpty(this, adapter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonItemNo) {
            presenter.acquireDatas0(etItemNo.getText().toString());
        } else if (view == buttonBincode) {
            presenter.acquireDatas1(etBincode.getText().toString());
        }
    }


    @Override
    public void clearDatas() {
        etItemNo.setText("");
        etBincode.setText("");
        datas.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void exitActivity() {

    }

    @Override
    public void fillRecycler(List<BinContentInfo> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_func, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_clear) {
            clearDatas();
        }
        return super.onOptionsItemSelected(item);
    }

}
