package es.ediae.master.programacion.gestionusuario.model;

public class JobTitleModel {

    private Integer id;
    private String name;

    public JobTitleModel() {
    }

    public JobTitleModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
