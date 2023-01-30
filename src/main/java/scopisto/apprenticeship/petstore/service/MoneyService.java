package scopisto.apprenticeship.petstore.service;

import scopisto.apprenticeship.petstore.model.currency.Money;

public interface MoneyService {

    public Money subtract(Money subtractFrom, Money amount);
}
