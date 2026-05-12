package es.ediae.master.programacion.gestionusuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressRequestDTO {
    @NotBlank
    @Schema(example = "Calle Gran Via")
    private String streetName;
    @NotNull
    @Schema(example = "123")
    private Integer streetNumber;
    @NotNull
    @Schema(example = "true")
    private Boolean mainAddress;
    @NotNull
    @Schema(example = "1")
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
