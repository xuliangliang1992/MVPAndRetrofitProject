package com.xll.mvpandretrofit.http;

import com.xll.mvpandretrofit.TeacherBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 功能：
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */

public interface FaceIDService {

    @GET("queryTeachers")
    Observable<TeacherBean> getTeachers();

    @POST("ReadParams")
    Observable<Map<String,Object>> ReadParams(@Body Map<String ,Object> map);

    @POST("ReadParams")
    @FormUrlEncoded
    Observable<Map<String,Object>> ReadParams2(@FieldMap Map<String ,Object> map);


    @POST("addTeacher")
    @FormUrlEncoded
    Observable<Object> addTeacher(@FieldMap Map<String ,Object> map);
   /* @POST("ReadParams")
    @FormUrlEncoded
    Observable<Map<String,Object>> ReadParams2(@Field("username") String userName,@Field("password") String password,
                                               @Field("sex") String sex,
                                               @Field("hobby") String hobby, @Field("birthday") String birthday);*/
}
