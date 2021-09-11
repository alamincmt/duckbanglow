package com.duckbanglowofficial.duckbanglow.services.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Division {
    @SerializedName("data")
    @Expose
    private List<DivisionData> divisionList;

    public List<DivisionData> getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(List<DivisionData> divisionList) {
        this.divisionList = divisionList;
    }
}
