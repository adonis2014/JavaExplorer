package test.supplier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.experimental.theories.suppliers.TestedOnSupplier;

@ParametersSuppliedBy(TestedOnSupplier.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectTestedOn {
	Class<?> value();
}
