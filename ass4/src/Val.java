import java.util.Map;
import java.util.Set;
/**
 * Represents a constant boolean value in an expression.
 */
class Val extends BaseExpression {
    private Boolean value;
    Val(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) {
        return value;
    }

    @Override
    public String toString() {
        return value ? "T" : "F";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    protected void collectVariables(Set<String> variables) {
        // No variables in Val
    }
}
