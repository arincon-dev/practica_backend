package es.ediae.master.programacion.gestionusuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressRequestDTO {
    @NotBlank
    private String streetName;
    @NotNull
    private Integer streetNumber;
    @NotNull
    private Boolean mainAddress;
    @NotNull
    private Integer userId;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Boolean getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Boolean mainAddress) {
        this.mainAddress = mainAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
