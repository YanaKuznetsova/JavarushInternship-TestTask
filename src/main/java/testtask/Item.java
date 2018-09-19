package testtask;

import javax.persistence.*;

@Entity
@Table (name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer necessity;
    private Integer amount;

    public Item(String name, Integer necessity, Integer amount) {
        this.name = name;
        this.necessity = necessity;
        this.amount = amount;
    }

    public Item() {}

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

    public Integer getNecessity() {
        return necessity;
    }

    public void setNecessity(Integer necessity) {
        this.necessity = necessity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}
