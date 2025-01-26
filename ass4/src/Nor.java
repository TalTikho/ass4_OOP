import java.util.HashMap;
import java.util.Map;
/**
 * Represents a logical NOR expression.
 */
class Nor extends BinaryExpression {
    public Nor(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(evaluate(assignment) || evaluate(assignment));
    }

    @Override
    public String toString() {
        return "↓(" + super.toString() + " , " + super.toString() + ")";
    }

    @Override
    protected BinaryExpression create(Expression left, Expression right) {
        return new Nor(left, right);
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(nandify(), nandify()), new Nand(nandify(), nandify()));
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = simplify();
        Expression simplifiedRight = simplify();
        if (simplifiedLeft instanceof Val && simplifiedRight instanceof Val) {
            return new Val(!(((Val) simplifiedLeft).evaluate(new HashMap<>())
                    || ((Val) simplifiedRight).evaluate(new HashMap<>())));
        }
        return new Nor(simplifiedLeft, simplifiedRight);
    }
}
