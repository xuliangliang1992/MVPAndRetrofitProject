package com.xll.mvpandretrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xll.mvpandretrofit.http.AppRetrofit;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能：
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */

public class AddTeacher extends AppCompatActivity {


    EditText mEditName;

    EditText mEditAge;
    EditText mEditTel;
    EditText mEditProject;
    Button mBtnAdd;

    CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_teacher);
        mEditName = findViewById(R.id.edit_name);
        mEditAge = findViewById(R.id.edit_age);
        mEditProject = findViewById(R.id.edit_project);
        mEditTel = findViewById(R.id.edit_tel);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mEditName.getText().toString().trim();
                String age = mEditAge.getText().toString().trim();
                String sex = "男";
                String tel = mEditTel.getText().toString().trim();
                String project = mEditProject.getText().toString().trim();

                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("age", age);
                map.put("sex", sex);
                map.put("tel", tel);
                map.put("project", project);
                new AppRetrofit().getFaceIDService().addTeacher(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                mCompositeSubscription.add(d);
                            }

                            @Override
                            public void onNext(Object stringObjectMap) {
                                if ("success".equals(stringObjectMap.toString())) {
                                    Toast.makeText(AddTeacher.this, "添加成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            }
        });
    }

}
