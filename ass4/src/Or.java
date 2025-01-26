import java.util.HashMap;
import java.util.Map;
/**
 * Represents a logical OR expression.
 */
class Or extends BinaryExpression {
    public Or(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return evaluate(assignment) || evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + super.toString() + " ∨ " + super.toString() + ")";
    }

    @Override
    protected BinaryExpression create(Expression left, Expression right) {
        return new Or(left, right);
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(nandify(), nandify()), new Nand(nandify(), nandify()));
    }

    @Override
    public Expression norify() {
        Expression norLeft = new Nor(norify(), norify());
        return new Nor(norLeft, norLeft);
    }

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = simplify();
        Expression simplifiedRight = simplify();
        if (simplifiedLeft instanceof Val) {
            if (!((Val) simplifiedLeft).evaluate(new HashMap<>())) {
                return simplifiedRight;
            } else {
                return new Val(true);
            }
        }
        if (simplifiedRight instanceof Val) {
            if (!((Val) simplifiedRight).evaluate(new HashMap<>())) {
                return simplifiedLeft;
            } else {
                return new Val(true);
            }
        }
        return new Or(simplifiedLeft, simplifiedRight);
    }
}