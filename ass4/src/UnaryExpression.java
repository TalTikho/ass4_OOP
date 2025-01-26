import java.util.HashSet;
import java.util.Set;
/**
 * Abstract base class for unary expressions.
 */
abstract class UnaryExpression extends BaseExpression {
    private Expression expression;
    /**
     * expression.
     * @param expression represents the expression
     */
    UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Expression assign(String var, Expression exp) {
        return create(expression.assign(var, exp));
    }

    protected abstract UnaryExpression create(Expression expression);
//
//    @Override
//    protected void collectVariables(Set<String> variables) {
//        expression.collectVariables(variables);
//    }
    @Override
    public Set<String> collectVariables() {
        Set<String> set = new HashSet<String>();
        set.add(expression.toString());
        return set;
    }
}

