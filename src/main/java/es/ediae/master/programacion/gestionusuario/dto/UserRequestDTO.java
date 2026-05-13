package es.ediae.master.programacion.gestionusuario.dto;

import java.time.LocalTime;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequestDTO {

    //what the client sends when creating or updating a user
    @NotBlank
    @Schema(example = "johnsmith")
    private String username;
    @NotBlank
    @Schema(example = "password123")
    private String password;
    @NotBlank
    @Schema(example = "John")
    private String name;
    @NotBlank
    @Schema(example = "Smith")
    private String firstSurname;
    @Schema(example = "Doe")
    private String secondSurname;
    @Schema(type = "string", format = "date", example = "2006-05-12")
    private Date birthDate;
    @Schema(type = "string", format = "time", example = "10:00:00")
    private LocalTime breakfastTime;
    @NotNull
    @Schema(example = "true")
    private Boolean isAdmin;
    @NotNull
    @Schema(example = "1")
    private Integer genderId;
    @Schema(example = "2")
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
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
