package tbs.client.webclient.elastic.validator;

import org.springframework.beans.factory.annotation.Value;
import tbs.client.webclient.elastic.metadata.NegativePriceTicket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TicketPriceValidator implements ConstraintValidator<NegativePriceTicket, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return !(value <0);
    }
}
