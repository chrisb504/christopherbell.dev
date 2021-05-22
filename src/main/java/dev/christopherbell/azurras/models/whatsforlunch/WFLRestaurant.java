package dev.christopherbell.azurras.models.whatsforlunch;

//import javax.persistence.*;

//@Entity
//@Table(name = "wfl_table")
public class WFLRestaurant {
    private String address;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String phoneNumber;
    private String postalCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}