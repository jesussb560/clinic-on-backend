package com.app.clinicon.responsesdto;

import java.util.List;

import com.app.clinicon.attentionrating.AttentionRatingProjection.DashboardFilterData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardResponseDTO {

    private long usersCount;
    private long doctorsCount;
    private long appointmentsCount;
    private long complainsCount;
    private List<Long> totalAppointmentsByWeekDay;
    private List<DashboardFilterData> attentionRatingFilterList;
    
}
