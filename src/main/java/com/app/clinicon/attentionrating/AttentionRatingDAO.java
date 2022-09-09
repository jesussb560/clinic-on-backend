package com.app.clinicon.attentionrating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.app.clinicon.attentionrating.AttentionRatingProjection.DashboardFilterData;

public interface AttentionRatingDAO extends JpaRepository<AttentionRating, Long>, JpaSpecificationExecutor<AttentionRating> {
    
    long countByQualificationId(long id);

    //traer el top 5 de usuarios filtrados por: la cantidad de attentionRating con la mayor suma de  Qualification positivos
    //stored procedure
    @Query(value = "SELECT u.firstname, u.lastname, s.name as specialty_name, ar.doctor_id, Count(*) AS total FROM attention_ratings ar INNER JOIN users u ON ar.doctor_id = u.id INNER JOIN users_specialties us ON ar.doctor_id = us.doctor AND us.principal = 1 INNER JOIN specialties s ON us.specialty = s.id GROUP BY u.firstname, u.lastname, ar.doctor_id, specialty_name ORDER BY total DESC LIMIT 5", nativeQuery = true)
    List<DashboardFilterData> findTop5ByAttentionRatingQualification();

}
