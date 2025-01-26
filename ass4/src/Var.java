import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Represents a variable in an expression.
 */
class Var extends BaseExpression {
    private String name;
    Var(String name) {
        this.name = name;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(name)) {
            return assignment.get(name);
        } else {
            throw new Exception("Variable " + name + " not found in assignment.");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.name.equals(var)) {
            return expression;
        } else {
            return this;
        }
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
    public Set<String> collectVariables() {
        Set<String> set = new HashSet<String>();
        set.add(name);
        return set;
    }
    @Override
    public List<String> getVariables() {
        List<String> set = new ArrayList<>();
        set.add(name);
        return set;
    }
}
