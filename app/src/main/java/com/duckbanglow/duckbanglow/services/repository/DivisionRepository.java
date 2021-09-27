package com.duckbanglow.duckbanglow.services.repository;

import androidx.lifecycle.MutableLiveData;

import com.duckbanglow.duckbanglow.services.models.DivisionData;

import java.util.List;

public interface DivisionRepository {

    public MutableLiveData<List<DivisionData>> getDivisionList();

}
