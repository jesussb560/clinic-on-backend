package com.app.clinicon.user;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.app.clinicon.city.City;
import com.app.clinicon.diseasesTreated.DiseaseTreated;
import com.app.clinicon.gender.Gender;
import com.app.clinicon.role.Role;
import com.app.clinicon.specialty.Specialty;
import com.app.clinicon.userspecialty.UserSpecialty;

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

    @Size(min = 2, max = 50)
    private String firstname;

    @Size(min = 2, max = 50)
    private String lastname;

    @Size(min = 2, max = 100)
    private String address;
    private String profileImage;

    @Size(max = 5000)
    private String aboutMe;

    @Size(min = 12, max = 12)
    private String collegiateNumber;

    @Column(unique = true)
    private String emailAddress;

    private String phoneNumber;

    @Size(min = 6)
    private String password;

    private int status;
    private Timestamp createdAt;

    @Column(nullable = true)
    private Timestamp updatedAt;
    
    // @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Gender gender;
    
    // @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
    
    @Builder.Default
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

    //nuevos campos
    @Builder.Default
    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private Set<UserSpecialty> userSpecialties = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_diseases_treated", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "disease_treated_id"))
    private Set<DiseaseTreated> diseasesTreated = new HashSet<>();

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
