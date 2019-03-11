package app.component.foundation.host.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;


import com.alibaba.android.arouter.launcher.ARouter;

import app.component.foundation.host.R;
import app.component.foundation.host.presenter.MainPresenter;
import app.component.foundation.host.view.MainContractView;
import app.component.simple.mvp.activity.BaseMvpActivity;
import butterknife.BindView;
import ezy.ui.layout.LoadingLayout;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContractView.View
{
    @BindView(R.id.loadingLayout)
    LoadingLayout loadingLayout;
    @BindView(R.id.contentTextView)
    TextView contentTextView;

    @Override
    public int applyContent() {
        return R.layout.simple_activity_main;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingLayout.setRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.getDataFromNet();
                ARouter.getInstance().build("/activity/db").navigation();
            }
        });
        p.getDataFromNet();
    }

    @Override
    public void refreshView(String data) {
        showContent();
        contentTextView.setText(data);
    }
}
