package com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PahologyInvoiceListMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("bill_data")
    @Expose
    private List<BillDatum> billData = new ArrayList<BillDatum>();
    public final static Parcelable.Creator<PahologyInvoiceListMainModal> CREATOR = new Creator<PahologyInvoiceListMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PahologyInvoiceListMainModal createFromParcel(Parcel in) {
            return new PahologyInvoiceListMainModal(in);
        }

        public PahologyInvoiceListMainModal[] newArray(int size) {
            return (new PahologyInvoiceListMainModal[size]);
        }

    };

    protected PahologyInvoiceListMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.billData, (com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.BillDatum.class.getClassLoader()));
    }

    public PahologyInvoiceListMainModal() {
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

    public List<BillDatum> getBillData() {
        return billData;
    }

    public void setBillData(List<BillDatum> billData) {
        this.billData = billData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(billData);
    }

    public int describeContents() {
        return 0;
    }

}