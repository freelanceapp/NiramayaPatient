package com.ibt.niramaya.retrofit;

import retrofit2.Response;

public interface WebResponse {

    void onResponseSuccess(Response<?> result);

    void onResponseFailed(String error);
}