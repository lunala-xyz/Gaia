package me.minekid.gaia.ast.expr;

import me.minekid.gaia.ast.AstVisitor;

public class AssignmentExpression extends Expression {
    private final Expression target;
    private final AssignmentOperator operator;
    private final Expression value;

    public AssignmentExpression(Expression target, AssignmentOperator operator, Expression value) {
        this.target = target;
        this.operator = operator;
        this.value = value;
    }

    public Expression getTarget() {
        return target;
    }

    public AssignmentOperator getOperator() {
        return operator;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitAssignmentExpression(this);
    }

    public enum AssignmentOperator {
        ASSIGN("="),
        PLUS_ASSIGN("+="),
        MINUS_ASSIGN("-=");

        private final String symbol;

        AssignmentOperator(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        public static AssignmentOperator fromSymbol(String symbol) {
            for (AssignmentOperator op : values()) {
                if (op.symbol.equals(symbol)) {
                    return op;
                }
            }
            throw new IllegalArgumentException("Unknown assignment operator: " + symbol);
        }
    }
}
