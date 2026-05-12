package es.ediae.master.programacion.gestionusuario.model;

public class AddressModel {

    private Integer id;
    private String streetName;
    private Integer streetNumber;
    private Boolean mainAddress;
    private Integer userId;

    public AddressModel() {
    }

    public AddressModel(Integer id, String streetName, Integer streetNumber, Boolean mainAddress, Integer userId) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.mainAddress = mainAddress;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
