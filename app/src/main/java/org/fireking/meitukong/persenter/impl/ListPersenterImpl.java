package org.fireking.meitukong.persenter.impl;

import android.content.Context;
import android.util.Log;

import org.fireking.meitukong.bean.ITuBaBean;
import org.fireking.meitukong.model.IListModel;
import org.fireking.meitukong.model.impl.ListModelImpl;
import org.fireking.meitukong.persenter.IListPersenter;
import org.fireking.meitukong.ui.view.IListView;

import java.util.List;

import cn.bmob.v3.listener.FindListener;

/**
 * Created by wangg on 16/1/5.
 */
public class ListPersenterImpl implements IListPersenter {

    private Context mContext;
    private IListModel iListModel;
    private IListView mIListView;

    public ListPersenterImpl(Context context, IListView iListView) {
        this.mContext = context;
        this.iListModel = new ListModelImpl(mContext);
        this.mIListView = iListView;
    }

    @Override
    public void getList(int pageNo, String type) {
        mIListView.showLoading();
        iListModel.getList(pageNo, type, new FindListener<ITuBaBean>() {
            @Override
            public void onSuccess(List<ITuBaBean> list) {
                mIListView.hideLoading();
                if(list == null || list.size() == 0){
                    mIListView.showEmpty();
                    return;
                }
                mIListView.setList(list);
            }

            @Override
            public void onError(int i, String s) {
                mIListView.hideLoading();
                mIListView.showError();
            }
        });
    }

}
