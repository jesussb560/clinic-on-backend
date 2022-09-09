package com.app.clinicon.attentionrating;

import java.util.List;

import com.app.clinicon.attentionrating.AttentionRatingProjection.DashboardFilterData;

public interface AttentionRatingService {

    List<AttentionRating> findAll();
    AttentionRating findById(long id);
    AttentionRating save(AttentionRating attentionRating);
    long countByQualificationId(int id);

    List<DashboardFilterData> findTop5ByAttentionRatingQualification();
    
}
