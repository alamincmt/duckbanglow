package com.duckbanglowofficial.duckbanglow.services.repository;

import androidx.lifecycle.MutableLiveData;

import com.duckbanglowofficial.duckbanglow.services.models.Division;
import com.duckbanglowofficial.duckbanglow.services.models.DivisionData;

import java.util.List;

public interface DivisionRepository {

    public MutableLiveData<List<DivisionData>> getDivisionList();

}
