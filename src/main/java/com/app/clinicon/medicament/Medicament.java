package com.app.clinicon.medicament;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;
import java.util.HashSet;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.app.clinicon.pharmaceutical.Pharmaceutical;

import javax.persistence.JoinColumn;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicaments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int requiresPrescription;

    //format
    
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int status;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "medicaments_pharmaceuticals", joinColumns = @JoinColumn(name = "medicament_id"), inverseJoinColumns = @JoinColumn(name = "pharmaceutical_id"))
    private Set<Pharmaceutical> pharmaceuticals = new HashSet<>();

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
