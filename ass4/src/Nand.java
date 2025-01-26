import java.util.HashMap;
import java.util.Map;
/**
 * Represents a logical NAND expression.
 */
class Nand extends BinaryExpression {
    public Nand(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(evaluate(assignment) && evaluate(assignment));
    }

    @Override
    public String toString() {
        return "↑(" + super.toString() + " , " + super.toString() + ")";
    }

    @Override
    protected BinaryExpression create(Expression left, Expression right) {
        return new Nand(left, right);
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        Expression norLeft = new Nor(norify(), norify());
        Expression norRight = new Nor(norify(), norify());
        return new Nor(norLeft, norRight);
    }

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = simplify();
        Expression simplifiedRight = simplify();
        if (simplifiedLeft instanceof Val && simplifiedRight instanceof Val) {
            return new Val(!(((Val) simplifiedLeft).evaluate(new HashMap<>())
                    && ((Val) simplifiedRight).evaluate(new HashMap<>())));
        }
        return new Nand(simplifiedLeft, simplifiedRight);
    }
}
