package tbs.vxrkafka.elastic.validator;

import tbs.vxrkafka.elastic.metadata.NegativePriceTicket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TicketPriceValidator implements ConstraintValidator<NegativePriceTicket, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return !(value <0);
    }
}
