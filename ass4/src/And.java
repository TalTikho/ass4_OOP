import java.util.HashMap;
import java.util.Map;
/**
 * Represents a logical AND expression.
 */
class And extends BinaryExpression {
    And(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return evaluate(assignment) && evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + super.toString() + " ∧ " + super.toString() + ")";
    }

    @Override
    protected BinaryExpression create(Expression left, Expression right) {
        return new And(left, right);
    }

    @Override
    public Expression nandify() {
        Expression nandLeft = new Nand(nandify(), nandify());
        return new Nand(nandLeft, nandLeft);
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(norify(), norify()), new Nor(norify(), norify()));
    }

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = simplify();
        Expression simplifiedRight = simplify();
        if (simplifiedLeft instanceof Val) {
            if (((Val) simplifiedLeft).evaluate(new HashMap<>())) {
                return simplifiedRight;
            } else {
                return new Val(false);
            }
        }
        if (simplifiedRight instanceof Val) {
            if (((Val) simplifiedRight).evaluate(new HashMap<>())) {
                return simplifiedLeft;
            } else {
                return new Val(false);
            }
        }
        return new And(simplifiedLeft, simplifiedRight);
    }
}
