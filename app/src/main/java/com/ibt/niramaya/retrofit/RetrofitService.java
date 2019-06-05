package com.ibt.niramaya.retrofit;


import android.app.Dialog;

import com.ibt.niramaya.BuildConfig;
import com.ibt.niramaya.modal.doctor_opd.DoctorOpdModel;
import com.ibt.niramaya.modal.doctor_opd_model.DoctorOpdDataModel;
import com.ibt.niramaya.modal.finance.PatientFinanceListModel;
import com.ibt.niramaya.modal.home.HomeDataModel;
import com.ibt.niramaya.modal.hospital.HospitalListModel;
import com.ibt.niramaya.modal.hospital_detail.HospitalDetailModel;
import com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal.OpdInvoiceMainModal;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PahologyInvoiceListMainModal;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.PharmacyInvoiceMainModal;
import com.ibt.niramaya.modal.otp_verifacation_modal.OtpVerificationMainModal;
import com.ibt.niramaya.modal.patient_modal.PatientMainModal;
import com.ibt.niramaya.modal.prescription.PrescritionListModel;
import com.ibt.niramaya.modal.prescription.detail.PrescriptionDetailModel;
import com.ibt.niramaya.modal.specialization.all.SpecialistDoctorModel;
import com.ibt.niramaya.modal.specialization.hospital.HospitalSpecialistDoctorModel;
import com.ibt.niramaya.utils.AppProgressDialog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public static RetrofitApiClient client;

    public static String BASE_URL = "http://niramaya.infobitetechnology.tech/api/";

    public RetrofitService() {

        HttpLoggingInterceptor mHttpLoginInterceptor = new HttpLoggingInterceptor();

        mHttpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder mOkClient = new OkHttpClient.Builder().readTimeout(300,
                TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).connectTimeout(300, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            mOkClient.addInterceptor(mHttpLoginInterceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        client = retrofit.create(RetrofitApiClient.class);
    }

    public static RetrofitApiClient getRxClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return retrofit.create(RetrofitApiClient.class);
    }

    public static RetrofitApiClient getRetrofit() {
        if (client == null)
            new RetrofitService();

        return client;
    }

    public static void getServerResponse(final Dialog dialog, final Call<ResponseBody> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getOtpResponse(final Dialog dialog, final Call<OtpVerificationMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<OtpVerificationMainModal>() {
            @Override
            public void onResponse(Call<OtpVerificationMainModal> call, Response<OtpVerificationMainModal> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<OtpVerificationMainModal> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getPatientList(final Dialog dialog, final Call<PatientMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<PatientMainModal>() {
            @Override
            public void onResponse(Call<PatientMainModal> call, Response<PatientMainModal> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<PatientMainModal> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void patientPrescriptionList(final Dialog dialog, final Call<PrescritionListModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<PrescritionListModel>() {
            @Override
            public void onResponse(Call<PrescritionListModel> call, Response<PrescritionListModel> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<PrescritionListModel> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void patientPrescriptionDetail(final Dialog dialog, final Call<PrescriptionDetailModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<PrescriptionDetailModel>() {
            @Override
            public void onResponse(Call<PrescriptionDetailModel> call, Response<PrescriptionDetailModel> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<PrescriptionDetailModel> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }
    public static void getPharmacyInvoiceList(final Dialog dialog, final Call<PharmacyInvoiceMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<PharmacyInvoiceMainModal>() {
            @Override
            public void onResponse(Call<PharmacyInvoiceMainModal> call, Response<PharmacyInvoiceMainModal> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<PharmacyInvoiceMainModal> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }
    public static void getOpdInvoiceList(final Dialog dialog, final Call<OpdInvoiceMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<OpdInvoiceMainModal>() {
            @Override
            public void onResponse(Call<OpdInvoiceMainModal> call, Response<OpdInvoiceMainModal> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<OpdInvoiceMainModal> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }
    public static void getPathologyInvoiceList(final Dialog dialog, final Call<PahologyInvoiceListMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<PahologyInvoiceListMainModal>() {
            @Override
            public void onResponse(Call<PahologyInvoiceListMainModal> call, Response<PahologyInvoiceListMainModal> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<PahologyInvoiceListMainModal> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getHospitalList(final Dialog dialog, final Call<HospitalListModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<HospitalListModel>() {
            @Override
            public void onResponse(Call<HospitalListModel> call, Response<HospitalListModel> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<HospitalListModel> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getHomePageData(final Dialog dialog, final Call<HomeDataModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getOpdDoctorList(final Dialog dialog, final Call<HospitalDetailModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<HospitalDetailModel>() {
            @Override
            public void onResponse(Call<HospitalDetailModel> call, Response<HospitalDetailModel> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<HospitalDetailModel> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void patientFinanceList(final Dialog dialog, final Call<PatientFinanceListModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<PatientFinanceListModel>() {
            @Override
            public void onResponse(Call<PatientFinanceListModel> call, Response<PatientFinanceListModel> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<PatientFinanceListModel> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void doctorDetail(final Dialog dialog, final Call<DoctorOpdDataModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<DoctorOpdDataModel>() {
            @Override
            public void onResponse(Call<DoctorOpdDataModel> call, Response<DoctorOpdDataModel> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<DoctorOpdDataModel> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getSpecializationDoctor(final Dialog dialog, final Call<SpecialistDoctorModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<SpecialistDoctorModel>() {
            @Override
            public void onResponse(Call<SpecialistDoctorModel> call, Response<SpecialistDoctorModel> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<SpecialistDoctorModel> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getHospitalSpecializationDoctor(final Dialog dialog, final Call<HospitalSpecialistDoctorModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<HospitalSpecialistDoctorModel>() {
            @Override
            public void onResponse(Call<HospitalSpecialistDoctorModel> call, Response<HospitalSpecialistDoctorModel> response) {
                AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<HospitalSpecialistDoctorModel> call, Throwable throwable) {
                AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

}