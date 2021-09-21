package com.duckbanglowofficial.duckbanglow.services.models.temp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainDivisions {
    @SerializedName("data")
    @Expose
    private List<Divisions> data = null;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("status")
    @Expose
    private int status;

    public MainDivisions(List<Divisions> data, boolean success, int status) {
        this.data = data;
        this.success = success;
        this.status = status;
    }

    public List<Divisions> getData() {
        return data;
    }

    public void setData(List<Divisions> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
