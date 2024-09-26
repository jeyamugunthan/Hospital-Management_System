package com.dao;

import java.util.List;

import com.entity.MedicalHistory;

public interface MedicalHistoryDao {
	public void addMedicalHistory();

	public void viewMedicalHistoryDetail();

    List<MedicalHistory> getAllMedicalHistories();

    public void  updateMedicalHistory();

  
}