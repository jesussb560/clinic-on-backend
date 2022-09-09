package com.app.clinicon.attentionrating;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.clinicon.attentionrating.AttentionRatingProjection.DashboardFilterData;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AttentionRatingServiceImpl implements AttentionRatingService {

    private final AttentionRatingDAO attentionRatingDAO;

    @Override
    public List<AttentionRating> findAll() {
        return attentionRatingDAO.findAll();
    }

    @Override
    public AttentionRating findById(long id) {
        return attentionRatingDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("AttentionRating not found"));
    }

    @Override
    public AttentionRating save(AttentionRating attentionRating) {
        return attentionRatingDAO.save(attentionRating);
    }

    @Override
    public long countByQualificationId(int id) {
        return attentionRatingDAO.countByQualificationId(id);
    }

    @Override
    public List<DashboardFilterData> findTop5ByAttentionRatingQualification() {
        return attentionRatingDAO.findTop5ByAttentionRatingQualification();
    }
    
}
