package scopisto.apprenticeship.petstore.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import scopisto.apprenticeship.petstore.model.currency.Money;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DOG")
public class Dog extends Pet {


    @Override
    public String printBuyMessage() {
        return String.format("Woof, dog %s has owner %s", this.getName(), this.getOwner().getFirstName());
    }

    @Override
    public Money getPrice() {
        BigDecimal agePrice = this.getAgePrice().getAmount();

        BigDecimal rating = BigDecimal.valueOf(this.getRating());
        BigDecimal ratingPrice = BigDecimal.ONE.multiply(rating);

        BigDecimal totalPrice = agePrice.add(ratingPrice);

        return new Money(totalPrice);
    }


}
