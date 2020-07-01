package com.shimhg02.solorestorant.network.Api

import com.shimhg02.solorestorant.Test.Data.GroupData
import com.shimhg02.solorestorant.Test.Data.GroupJoinData
import com.shimhg02.solorestorant.Test.Data.TestInfoData
import com.shimhg02.solorestorant.network.Data.LocationRepo
import com.shimhg02.solorestorant.network.Data.LogIn
import retrofit2.Call
import retrofit2.http.*

/**
 * @description API 관리 인터페이스
 */

interface API {

    @POST("/signin")
    @FormUrlEncoded
    fun logIn(@Field("id") id : String, @Field("passwd") pw : String) :  Call<LogIn>

    @POST("/signup")
    @FormUrlEncoded
    fun logUp(@Field("name") name: String?,
              @Field("id") id: String?,
              @Field("passwd") pw: String?,
              @Field("phone") phoneNum: String?,
              @Field("birth") birth: String?,
              @Field("email") email: String?,
              @Field("nick") nickName: String?,
              @Field("sex") sex: Boolean?) : Call<LogIn>


    @POST("/social/facebook")
    @FormUrlEncoded
    fun getFacebookAccess(@Field("token") accessToken : String) :   Call<Void>

    @POST("/social/google")
    @FormUrlEncoded
    fun getGoogleAccess(@Field("token") accessToken : String) :   Call<Void>

    @POST("/social/google")
    @FormUrlEncoded
    fun socialVerify(@Field("token") accessToken : String) :   Call<Void>

    @POST("/social/verifySave ")
    @FormUrlEncoded
    fun socialVerifySave(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("nick") nick : Number,
        @Field("phone") phone : String,
        @Field("birth") birth : String,
        @Field("sex") sex : String,
        @Field("uuid") uuid : String) :  Call<ArrayList<LocationRepo>>

    @POST("/termsCheck")
    @FormUrlEncoded
    fun term(@Field("token") token : String, @Field("terms") accept : Boolean) :  Call<LogIn>

    @POST("/duplicateChk")
    @FormUrlEncoded
    fun duplicate(@Field("id") id : String) :  Call<Void>

    @POST("/autoLogin")
    @FormUrlEncoded
    fun autoLogin(@Field("token") token : String) :  Call<LogIn>

    @POST("/getPlace")
    @FormUrlEncoded
    fun getPlace(@Field("lat") lat : String, @Field("lng") lng : String, @Field("range") range : Number) :  Call<ArrayList<LocationRepo>>

    @POST("/getDetail")
    @FormUrlEncoded
    fun getDetail(@Field("place_id") placeid : String) :   Call<TestInfoData>

    @POST("/getCategory ")
    @FormUrlEncoded
    fun getCategory(@Field("lat") lat : String, @Field("lng") lng : String, @Field("range") range : Number, @Field("category") category : String) :  Call<ArrayList<LocationRepo>>

    @POST("/addGroup")
    @FormUrlEncoded
    fun addGroup(@Field("token") token : String,
                 @Field("groupName") groupName : String,
                 @Field("maximum") maximun : String,
                 @Field("lat") lat : Number,
                 @Field("lng") lng : Number,
                 @Field("vicinity") vicinity : String,
                 @Field("time") time : String,
                 @Field("isAdult") isAdult : Boolean,
                 @Field("category") category : String
                 ) :   Call<GroupData>

    @POST("/readGroup/maxPage")
    @FormUrlEncoded
    fun getGroup(@Field("index") index : Number, @Field("token") token : String) :  Call<ArrayList<GroupData>>


    @POST("/joinGroup")
    @FormUrlEncoded
    fun joinGroup(@Field("token") token : String, @Field("groupUUID") groupUUID : String) :   Call<GroupJoinData>


    @POST("/searchGroup")
    @FormUrlEncoded
    fun searchGroup(@Field("token") token : String, @Field("searchText") searchText : String) :   Call<ArrayList<GroupData>>

}