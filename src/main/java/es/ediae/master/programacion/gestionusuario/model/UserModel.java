package es.ediae.master.programacion.gestionusuario.model;

import java.time.LocalTime;
import java.util.Date;

public class UserModel {

    private Integer id;
    private String username;
    private String password;
    private Date createdAt;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private Date birthDate;
    private LocalTime breakfastTime;
    private Boolean isAdmin;
    private GenderModel gender;
    private JobTitleModel jobTitle;

    public UserModel() {
    }
    
    public UserModel(Integer id, String username, String password, Date createdAt, String name,
            String firstSurname, String secondSurname, Date birthDate, LocalTime breakfastTime,
            Boolean isAdmin, GenderModel gender, JobTitleModel jobTitle) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.birthDate = birthDate;
        this.breakfastTime = breakfastTime;
        this.isAdmin = isAdmin;
        this.gender = gender;
        this.jobTitle = jobTitle;
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public LocalTime getBreakfastTime() {
        return breakfastTime;
    }

    public void setBreakfastTime(LocalTime breakfastTime) {
        this.breakfastTime = breakfastTime;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public GenderModel getGender() {
        return gender;
    }

    public void setGender(GenderModel gender) {
        this.gender = gender;
    }

    public JobTitleModel getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitleModel jobTitle) {
        this.jobTitle = jobTitle;
    }
}
