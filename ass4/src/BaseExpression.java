import java.util.HashMap;
/**
 * Abstract base class for expressions.
 */
abstract class BaseExpression implements Expression {
    @Override
    public Boolean evaluate() throws Exception {
        return evaluate(new HashMap<>());
    }

//    @Override
//    public List<String> getVariables() {
//        Set<String> variables = new HashSet<>();
//        collectVariables();
//        return new ArrayList<>(variables);
//    }

}
