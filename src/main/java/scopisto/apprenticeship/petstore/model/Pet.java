package scopisto.apprenticeship.petstore.model;
import jakarta.persistence.*;
import lombok.*;
import scopisto.apprenticeship.petstore.model.currency.Money;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="pet_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User owner;

    private String name;

    private String description;
    private LocalDate dateOfBirth;

    private Money price;

    private Integer rating;

    public abstract String getType();
    public abstract String printBuyMessage();
    public abstract Money getPrice();

    protected int getAge(){
        Period period = dateOfBirth.until(LocalDate.now());
        return period.getYears();
    }
    protected Money getAgePrice(){
        BigDecimal age = BigDecimal.valueOf(this.getAge());
        return new Money(BigDecimal.ONE.multiply(age));
    }

}
