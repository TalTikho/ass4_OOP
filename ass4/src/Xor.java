import java.util.HashMap;
import java.util.Map;
/**
 * Represents a logical XOR expression.
 */
class Xor extends BinaryExpression {
    public Xor(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return evaluate(assignment) ^ evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + super.toString() + " ⊕ " + super.toString() + ")";
    }

    @Override
    protected BinaryExpression create(Expression left, Expression right) {
        return new Xor(left, right);
    }

    @Override
    public Expression nandify() {
        Expression nandLeft = nandify();
        Expression nandRight = nandify();
        Expression nandLeftRight = new Nand(nandLeft, nandRight);
        return new Nand(new Nand(nandLeft, nandLeftRight), new Nand(nandRight, nandLeftRight));
    }

    @Override
    public Expression norify() {
        Expression norLeft = norify();
        Expression norRight = norify();
        return new Nor(new Nor(norLeft, new Nor(norLeft, norRight)), new Nor(norRight, new Nor(norLeft, norRight)));
    }

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = simplify();
        Expression simplifiedRight = simplify();
        if (simplifiedLeft instanceof Val && simplifiedRight instanceof Val) {
            return new Val(((Val) simplifiedLeft).evaluate(new HashMap<>())
                    ^ ((Val) simplifiedRight).evaluate(new HashMap<>()));
        }
        return new Xor(simplifiedLeft, simplifiedRight);
    }
}
