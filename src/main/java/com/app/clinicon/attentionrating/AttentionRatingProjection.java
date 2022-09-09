package com.app.clinicon.attentionrating;

public class AttentionRatingProjection {
    
    public interface DashboardFilterData{
        String getFirstname();
        String getLastname();
        String getSpecialtyName();
        String getDoctorId();
        int getTotal();
    }

}
