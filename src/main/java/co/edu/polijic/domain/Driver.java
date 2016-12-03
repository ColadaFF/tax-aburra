package co.edu.polijic.domain;

public class Driver {
    private Long id;
    private String name;
    private String lastName;
    private String cabPlate;

    public Driver(Long id, String name, String lastName, String cabPlate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cabPlate = cabPlate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCabPlate() {
        return cabPlate;
    }

    public void setCabPlate(String cabPlate) {
        this.cabPlate = cabPlate;
    }
}
