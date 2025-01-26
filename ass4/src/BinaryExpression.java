import java.util.HashSet;
import java.util.Set;
import java.util.List;
/**
 * Abstract base class for binary expressions.
 */
abstract class BinaryExpression extends BaseExpression {
    private Expression left;
    private Expression right;

    BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public List<String> getVariables() {
        return collectVariables().stream().toList();
    }
    public void setLeft(Expression left) {
        this.left = left;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    @Override
    public Expression assign(String var, Expression exp) {
        return create(left.assign(var, exp), right.assign(var, exp));
    }

    protected abstract BinaryExpression create(Expression left, Expression right);

    @Override
    public Set<String> collectVariables() {
        Set<String> set = new HashSet<String>();
        set.addAll(left.collectVariables());
        set.addAll(right.collectVariables());
        return set;
    }
}
