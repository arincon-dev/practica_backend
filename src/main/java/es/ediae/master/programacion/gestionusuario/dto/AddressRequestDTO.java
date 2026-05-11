package es.ediae.master.programacion.gestionusuario.dto;

public class AddressRequestDTO {

    private String streetName;
    private Integer streetNumber;
    private boolean mainAddress;
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

    public boolean getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(boolean mainAddress) {
        this.mainAddress = mainAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
