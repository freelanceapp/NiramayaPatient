package com.ibt.niramaya.retrofit;

import com.ibt.niramaya.adapter.invoices_adapter.PathologyInvoiceListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal.OpdInvoiceMainModal;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PahologyInvoiceListMainModal;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.PharmacyInvoiceMainModal;
import com.ibt.niramaya.modal.otp_verifacation_modal.OtpVerificationMainModal;
import com.ibt.niramaya.modal.patient_modal.PatientMainModal;
import com.ibt.niramaya.modal.prescription.PrescritionListModel;
import com.ibt.niramaya.modal.prescription.detail.PrescriptionDetailModel;

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


    @FormUrlEncoded
    @POST(Constant.USER_LOGIN)
    Call<ResponseBody> usersLogin(@Field("contact") String contact);

    @FormUrlEncoded
    @POST(Constant.OTP_VERIFICATION)
    Call<OtpVerificationMainModal> fatchOtp(@Field("contact") String contact, @Field("otp_number") String otp_number);

    @FormUrlEncoded
    @POST(Constant.CREATE_PATIENT_PROFILE)
    Call<ResponseBody> createPatientProfie(@Field("name") String name,
                                           @Field("bloodgroup") String bloodgroup,
                                           @Field("contact") String contact,
                                           @Field("date_of_birth") String date_of_birth,
                                           @Field("email") String email,
                                           @Field("house_number") String house_number,
                                           @Field("street_name") String street_name,
                                           @Field("city") String city,
                                           @Field("state") String state,
                                           @Field("country") String country,
                                           @Field("zipcode") String zipcode,
                                           @Field("gender") String gender,
                                           @Field("gardian_name") String gardian_name,
                                           @Field("relationship_with_gardian") String relationship_with_gardian,
                                           @Field("gardian_contact") String gardian_contact,
                                           @Field("gardian_address") String gardian_address,
                                           @Field("aadhar_number") String aadhar_number,
                                           @Field("user_id") String user_id,
                                           @Field("relationship_status") String relationship_status,
                                           @Field("profile_picture") String profile_picture);

    @FormUrlEncoded
    @POST(Constant.PATIENT_LIST)
    Call<PatientMainModal> patientList(@Field("user_id") String user_id);

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

    @FormUrlEncoded
    @POST(Constant.PATIENT_PRESCRIPTION_LIST)
    Call<PrescritionListModel> patientPrescriptionList(@Field("user_id") String user_id,
                                           @Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST(Constant.PATIENT_PRESCRIPTION_DETAIL)
    Call<PrescriptionDetailModel> patientPrescriptionDetail(@Field("user_id") String user_id,
                                                            @Field("patient_id") String patient_id,
                                                          @Field("opd_id") String opd_id);


    @POST(Constant.UPDATE_PATIENNT_PROFILE)
    Call<ResponseBody> updatePatientProfie(@Field("name") String name,
                                           @Field("bloodgroup") String bloodgroup,
                                           @Field("contact") String contact,
                                           @Field("date_of_birth") String date_of_birth,
                                           @Field("email") String email,
                                           @Field("house_number") String house_number,
                                           @Field("street_name") String street_name,
                                           @Field("city") String city,
                                           @Field("state") String state,
                                           @Field("country") String country,
                                           @Field("zipcode") String zipcode,
                                           @Field("gender") String gender,
                                           @Field("gardian_name") String gardian_name,
                                           @Field("relationship_with_gardian") String relationship_with_gardian,
                                           @Field("gardian_contact") String gardian_contact,
                                           @Field("gardian_address") String gardian_address,
                                           @Field("aadhar_number") String aadhar_number,
                                           @Field("user_id") String user_id,
                                           @Field("relationship_status") String relationship_status,
                                           @Field("patient_id") String patient_id,
                                           @Field("profile_picture") String profile_picture);

    @FormUrlEncoded
    @POST(Constant.PHARMACY_INVOICE_LIST)
    Call<PharmacyInvoiceMainModal> pharmacyInvoiceList(@Field("patient_id") String patient_id,
                                                       @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(Constant.PATHOLOGY_INVOICE_LIST)
    Call<PahologyInvoiceListMainModal> pathologyInvoiceList(@Field("patient_id") String patient_id,
                                                            @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(Constant.OPD_INVOICE_LIST)
    Call<OpdInvoiceMainModal> opdInvoiceList(@Field("patient_id") String patient_id,
                                             @Field("user_id") String user_id);
}