package com.ibt.niramaya.retrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitApiClient {


    @Multipart
    @POST("Add_Property.php")
    Call<ResponseBody> addProperty(@Part MultipartBody.Part Image1,
                                   @Part MultipartBody.Part Image2,
                                   @Part MultipartBody.Part Image3,
                                   @Part MultipartBody.Part Image4,
                                      @Part("WardName") RequestBody WardName,
                                      @Part("ZoneName") RequestBody ZoneName,
                                      @Part("BlockNumber") RequestBody BlockNumber,
                                      @Part("PropertyNumber") RequestBody PropertyNumber,
                                      @Part("PropertyName") RequestBody PropertyName,
                                      @Part("Address") RequestBody Address,

                                      @Part("CorporationPropertyNumber") RequestBody CorporationPropertyNumber,
                                      @Part("PropertyUsageType") RequestBody PropertyUsageType,
                                      @Part("PropertyType") RequestBody PropertyType,
                                      @Part("ResidentialCount") RequestBody ResidentialCount,
                                      @Part("CommercialCount") RequestBody CommercialCount,
                                      @Part("Remark1") RequestBody Remark1,
                                      @Part("Remark2") RequestBody Remark2,

                                      @Part("Longitude") RequestBody Longitude,
                                      @Part("Latitude") RequestBody Latitude,
                                      @Part("UserId") RequestBody UserId);

    @FormUrlEncoded
    @POST("Add_Property.php")
    Call<ResponseBody> uploadPropertyDataToServer(@Field("Image1") String Image1,
                                                    @Field("Image2") String Image2,
                                                    @Field("Image3") String Image3,
                                                    @Field("Image4") String Image4,

                                                    @Field("WardName") String WardName,
                                                    @Field("ZoneName") String ZoneName,
                                                    @Field("BlockNumber") String BlockNumber,
                                                    @Field("PropertyNumber") String PropertyNumber,
                                                    @Field("PropertyName") String PropertyName,
                                                    @Field("Address") String Address,

                                                    @Field("CorporationPropertyNumber") String CorporationPropertyNumber,
                                                    @Field("PropertyUsageType") String PropertyUsageType,
                                                    @Field("PropertyType") String PropertyType,
                                                    @Field("ResidentialCount") String ResidentialCount,
                                                    @Field("CommercialCount") String CommercialCount,
                                                    @Field("Remark1") String Remark1,
                                                    @Field("Remark2") String Remark2,
                                                    @Field("DeviceId") String DeviceId,

                                                    @Field("Longitude") String Longitude,
                                                    @Field("Latitude") String Latitude,
                                                    @Field("UserId") String UserId);

}