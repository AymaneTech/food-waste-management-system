package net.foodeals.common.valueOjects;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Currency;

@Embeddable
public record Price(BigDecimal amount, Currency currency) {
}
