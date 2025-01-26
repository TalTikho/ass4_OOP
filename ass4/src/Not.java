import java.util.HashMap;
import java.util.Map;
/**
 * Represents a logical NOT expression.
 */
class Not extends UnaryExpression {
    Not(Expression expression) {
        super(expression);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !evaluate(assignment);
    }

    @Override
    public String toString() {
        return "∼(" + super.toString() + ")";
    }

    @Override
    protected UnaryExpression create(Expression expression) {
        return new Not(expression);
    }

    @Override
    public Expression nandify() {
        return new Nand(nandify(), nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(norify(), norify());
    }

    @Override
    public Expression simplify() {
        Expression simplified = simplify();
        if (simplified instanceof Val) {
            return new Val(!((Val) simplified).evaluate(new HashMap<>()));
        }
        return new Not(simplified);
    }
}