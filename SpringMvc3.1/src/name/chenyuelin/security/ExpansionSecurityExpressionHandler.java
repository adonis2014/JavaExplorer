package name.chenyuelin.security;

import java.util.Collection;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;

public class ExpansionSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
	private static int POINT_CHAR_CODE = (int) '.';

	@Override
	public Object filter(Object filterTarget, Expression filterExpression, EvaluationContext ctx) {
		if (filterTarget instanceof Collection || filterTarget.getClass().isArray()) {
			return super.filter(filterTarget, filterExpression, ctx);
		} else {
			String originalExpression = filterExpression.getExpressionString();
			int startIndex = 0;
			int currentIndex = -1;
			while ((currentIndex = originalExpression.indexOf(POINT_CHAR_CODE, currentIndex)) > -1) {
				String propertyName = originalExpression.substring(startIndex, currentIndex ++);
				Object object = getExpressionParser().parseExpression(propertyName).getValue(filterTarget);
				if(object instanceof Collection || object.getClass().isArray()){
					super.filter(object, getExpressionParser().parseExpression(originalExpression.substring(currentIndex)), ctx);
					break;
				}
			}
		}
		return filterTarget;
	}

	public static void main(String[] args) {
		System.out.println(Character.codePointAt("=", 0));
		System.out.println((int) '=');
	}
}
