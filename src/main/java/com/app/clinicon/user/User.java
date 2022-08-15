package com.app.clinicon.user;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.app.clinicon.city.City;
import com.app.clinicon.gender.Gender;
import com.app.clinicon.role.Role;
import com.app.clinicon.specialty.Specialty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jesussb560
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String rut;

    private String firstName;
    private String lastName;
    private String address;

    @Column(unique = true)
    private String emailAddress;
    private String password;

    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

    //nuevos campos
    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_specialties", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Gender gender;
    
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
