package scopisto.apprenticeship.petstore.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import scopisto.apprenticeship.petstore.model.currency.Money;

@Entity
@DiscriminatorValue("CAT")
public class Cat extends Pet{
    @Override
    public String getType() {
        return "CAT";
    }
    @Override
    public String printBuyMessage() {
        return String.format("Meow, cat %s has owner %s",this.getName(), this.getOwner().getFirstName());
    }
    @Override
    public Money getPrice() {
        return this.getAgePrice();
    }
}
