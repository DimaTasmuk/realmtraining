package com.tasmuk.realmtraining.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tasmuk.realmtraining.R;
import com.tasmuk.realmtraining.models.Person;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RetrofitActivity extends AppCompatActivity {

    TextView tvTextRetrofit;
    GitHubAPI api;
    Realm realm;

    private static final String GIT_HUB_TOKEN = "8ce44f9403a94057ea50c306377492311c3aaa13";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        tvTextRetrofit = (TextView) findViewById(R.id.tvTextRetrofit);

        realm = Realm.getDefaultInstance();

        api = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request modifiedRequest = originalRequest
                            .newBuilder()
                            .header("Authorization", String.format("token %s", GIT_HUB_TOKEN))
                            .method(originalRequest.method(), originalRequest.body())
                            .build();
                    return chain.proceed(modifiedRequest);}).build())
                .build()
        .create(GitHubAPI.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        showUserInfo();
    }

    private void showUserInfo() {
        realm.where(Person.class).findAllSorted("name").asObservable()
                .filter(RealmResults::isLoaded)
                .switchMap(Observable::from)
                .forEach(person ->
                        Observable.combineLatest(
                            api.user(person.getName()),
                            api.userRepos(person.getName()),
                            ((user, reposes) -> user.toString() + "\nRepositories:" + reposes.toString() + "\n")
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(info -> tvTextRetrofit.setText(tvTextRetrofit.getText().toString() + "\n" + info), Throwable::printStackTrace)
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void onClick(View view) {
        Toast.makeText(this, "Oh yeah", Toast.LENGTH_SHORT).show();
    }
}
