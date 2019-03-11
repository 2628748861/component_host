package app.component.foundation.host.presenter;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import app.component.foundation.host.IUserService;
import app.component.foundation.host.db.Album;
import app.component.foundation.host.view.DBContractView;
import app.component.foundation.host.view.MainContractView;
import app.component.simple.db.DbProxy;
import app.component.simple.http.HttpApiProxy;
import app.component.simple.http.entity.BaseResponseEntity;
import app.component.simple.http.response.AppResultObservable;
import app.component.simple.mvp.presenter.MvpPresenter;
import app.component.simple.util.RxUtils;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class DbPresenter extends MvpPresenter<DBContractView.View> implements DBContractView.Presenter
{
    public DbPresenter(DBContractView.View view) {
        super(view);
    }

    @Override
    public void add() {
        Album album = new Album();
        album.setName("albumx");
        album.setPrice(90.19f);
        DbProxy.getInstance().save(album);
    }

    @Override
    public void delete() {
        DbProxy.getInstance().delete(Album.class,"name=\"albumx\"");
    }

    @Override
    public void update() {
        ContentValues contentValues=new ContentValues();
        contentValues.put("price",100);
        DbProxy.getInstance().update(Album.class,contentValues,"name=\"albumx\"");
    }

    @Override
    public void select() {
        List<Album> datas=DbProxy.getInstance().find(Album.class);
        v.refreshView(datas.toString());
    }
}
