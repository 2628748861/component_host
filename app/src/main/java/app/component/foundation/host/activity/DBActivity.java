package app.component.foundation.host.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import app.component.foundation.host.R;
import app.component.foundation.host.presenter.DbPresenter;
import app.component.foundation.host.view.DBContractView;
import app.component.simple.image.ImageLoaderProxy;
import app.component.simple.mvp.activity.BaseMvpActivity;
import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/activity/db")
public class DBActivity extends BaseMvpActivity<DbPresenter> implements DBContractView.View {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.image)
    ImageView image;

    @Override
    public void refreshView(String data) {
        textView.setText(data);
    }

    @Override
    public int applyContent() {
        return R.layout.simple_activity_db;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoaderProxy.getInstance().display(mContext,"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1843306489,2212684147&fm=58&bpow=480&bpoh=480",image);
    }

    @Override
    public DbPresenter createPresenter() {
        return new DbPresenter(this);
    }

    @OnClick({R.id.addBtn,R.id.deleteBtn,R.id.updateBtn,R.id.selectBtn})
    void click(View view)
    {
        switch (view.getId())
        {
            case R.id.addBtn:
                p.add();
                break;
            case R.id.deleteBtn:
                p.delete();
                break;
            case R.id.updateBtn:
                p.update();
                break;
            case R.id.selectBtn:
                p.select();
                break;
        }
    }
}
