package tbs.vxrkafka.elastic.metadata;

import tbs.vxrkafka.elastic.validator.TicketPriceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE, PARAMETER})
@Constraint(validatedBy = TicketPriceValidator.class)
public @interface NegativePriceTicket {
    String message() default "Price can not be negative";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
