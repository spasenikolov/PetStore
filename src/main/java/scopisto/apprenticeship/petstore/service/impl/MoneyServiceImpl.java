package scopisto.apprenticeship.petstore.service.impl;

import org.springframework.stereotype.Service;
import scopisto.apprenticeship.petstore.model.currency.Money;
import scopisto.apprenticeship.petstore.service.MoneyService;

@Service
public class MoneyServiceImpl implements MoneyService {

    @Override
    public Money subtract(Money subtractFrom, Money amount) {
        return new Money(subtractFrom.getAmount().subtract(amount.getAmount()));
    }
}
