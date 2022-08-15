package com.app.clinicon.pharmaceutical;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.app.clinicon.city.City;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pharmaceuticals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pharmaceutical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @PrePersist
    private void prePersist(){

        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());

        this.createdAt = now;
        this.updatedAt = null;
        this.status = 1;

    }

    @PreUpdate
    private void preUpdate(){

        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        
        this.updatedAt = now;

    }
    
}
