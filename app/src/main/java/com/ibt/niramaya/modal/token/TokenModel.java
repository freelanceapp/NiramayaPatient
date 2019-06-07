package com.ibt.niramaya.modal.token;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TokenModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<TokenDatum> data = null;
    public final static Parcelable.Creator<TokenModel> CREATOR = new Creator<TokenModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TokenModel createFromParcel(Parcel in) {
            return new TokenModel(in);
        }

        public TokenModel[] newArray(int size) {
            return (new TokenModel[size]);
        }

    }
            ;

    protected TokenModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (TokenDatum.class.getClassLoader()));
    }

    public TokenModel() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TokenDatum> getData() {
        return data;
    }

    public void setData(List<TokenDatum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}