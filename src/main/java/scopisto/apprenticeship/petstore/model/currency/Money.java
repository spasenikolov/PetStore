package scopisto.apprenticeship.petstore.model.currency;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Embeddable
@Data
public class Money {
    @Column(name = "budget")
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
    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
    public Money() {
    }
    public Money subtract(Money money){
        BigDecimal subtract = this.getAmount().subtract(money.getAmount());
        this.setAmount(subtract);
        return this;
    }
}
