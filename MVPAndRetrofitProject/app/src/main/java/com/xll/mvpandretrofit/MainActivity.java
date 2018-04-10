package com.xll.mvpandretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xll.mvpandretrofit.http.AppRetrofit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        new AppRetrofit().getFaceIDService().getTeachers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeacherBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(TeacherBean bean) {
                        Integer total = bean.getTotal();
                        Toast.makeText(MainActivity.this, "查到"+total+"条记录", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    //"xuliangliang","HAHA","男","DNF","1992-11-10"
    public void onClick2(View view) {
       startActivity(new Intent(this,AddTeacher.class));
    }
}
