import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for logical expressions.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in the assignment.
     * @param assignment a map from variable names to their boolean values.
     * @return the boolean result of the expression evaluation.
     * @throws Exception if the expression contains a variable which is not in the assignment.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * Evaluate the expression using an empty assignment.
     * @return the boolean result of the expression evaluation.
     * @throws Exception if the expression contains a variable.
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     * @return a list of variable names.
     */
    List<String> getVariables();

    /**
     * Returns a string representation of the expression.
     * @return the string representation.
     */
    String toString();

    /**
     * Returns a new expression where all occurrences of the variable are replaced with the provided expression.
     * @param var the variable to replace.
     * @param expression the expression to replace the variable with.
     * @return the new expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from converting all the operations to the logical Nand operation.
     * @return the Nandified expression.
     */
    Expression nandify();

    /**
     * Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     * @return the Norified expression.
     */
    Expression norify();

    /**
     * Returns a simplified version of the current expression.
     * @return the simplified expression.
     */
    Expression simplify();

    /**
     * collect Variables.
     * @return set
     */
    Set<String> collectVariables();
}


