package app.component.foundation.host;



import app.component.simple.http.entity.BaseResponseEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by cample on 2018/6/12.
 */

public interface IUserService {
    @Headers({"Domain-Name:domain"})
    @GET("/http/test.txt")
    Observable<BaseResponseEntity<String>> getArticleCategory();

}
