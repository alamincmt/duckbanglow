package com.duckbanglowofficial.duckbanglow.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.duckbanglowofficial.duckbanglow.services.models.DivisionData;
import com.duckbanglowofficial.duckbanglow.services.repository.DivisionRepositoryImpl;
import com.duckbanglowofficial.duckbanglow.services.repository.DivisionRepository;

import java.util.List;

public class DivisionViewModel extends AndroidViewModel {

    private DivisionRepository divisionRepository;

    public DivisionViewModel(@NonNull Application application) {
        super(application);
        divisionRepository = DivisionRepositoryImpl.getInstance(application);
    }

    public LiveData<List<DivisionData>> getTotalDivisionList(){
        return divisionRepository.getDivisionList();
    }

}
