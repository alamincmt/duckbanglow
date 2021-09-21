package com.duckbanglowofficial.duckbanglow.services.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Division {
    @SerializedName("data")
    @Expose
    private List<DivisionData> divisionList;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("status")
    @Expose
    private int status;

    public Division(List<DivisionData> divisionList, boolean success, int status) {
        this.divisionList = divisionList;
        this.success = success;
        this.status = status;
    }

    public List<DivisionData> getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(List<DivisionData> divisionList) {
        this.divisionList = divisionList;
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
