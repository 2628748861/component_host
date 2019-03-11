package app.component.foundation.host.view;

import app.component.simple.mvp.view.MvpView;

public interface DBContractView
{
    interface View extends MvpView
    {
        void refreshView(String data);
    }
    interface Presenter
    {
        void add();
        void delete();
        void update();
        void select();
    }
}
