package es.ediae.master.programacion.gestionusuario.entity;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(name= "created_at", nullable=false)
    private Date createdAt;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name= "first_surname", nullable = false)
    private String firstSurname;

    @Column(name= "second_surname", nullable = true)
    private String secondSurname;

    @Column(nullable = false)
    private Date birthdate;

    @Column(name= "breakfast_time", nullable = false)
    private Time breakfastTime;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private GenderEntity gender;

    @ManyToOne
    @JoinColumn(name = "job_title_id", nullable = true)
    private JobTitleEntity jobTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Time getBreakfastTime() {
        return breakfastTime;
    }

    public void setBreakfastTime(Time breakfastTime) {
        this.breakfastTime = breakfastTime;
    }

    public GenderEntity getGender() {
        return gender;
    }

    public void setGender(GenderEntity gender) {
        this.gender = gender;
    }

    public JobTitleEntity getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitleEntity jobTitle) {
        this.jobTitle = jobTitle;
    }
}
