package es.ediae.master.programacion.gestionusuario.dto;

import java.time.LocalTime;
import java.util.Date;

public class UserRequestDTO {

    //what the client sends when creating or updating a user
    
    private String username;
    private String password;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private Date birthDate;
    private LocalTime breakfastTime;
    private boolean isAdmin;
    private Integer genderId;
    private Integer jobTitleId;

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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Integer getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(Integer jobTitleId) {
        this.jobTitleId = jobTitleId;
    }
}
