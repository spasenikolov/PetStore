package scopisto.apprenticeship.petstore.model.currency;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/** I assume there needs to be a separate class for money
 * that uses BigDecimal for precision and has a enum currency.
 *
 * Because the currency in the example is USD
 * i assume it's the default currency
 *
*/
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Money {
    @Column()
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    Currency currency;

    public Money(BigDecimal amount) {
        this.amount = amount;
        currency = Currency.USD;
    }
    public Money(Integer amount) {
        this.amount = new BigDecimal(amount);
        currency = Currency.USD;
    }
}
