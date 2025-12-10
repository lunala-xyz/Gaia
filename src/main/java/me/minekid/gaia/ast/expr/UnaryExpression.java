package me.minekid.gaia.ast.expr;

import me.minekid.gaia.ast.AstVisitor;

public class UnaryExpression extends Expression {
    private final UnaryOperator operator;
    private final Expression operand;

    public UnaryExpression(UnaryOperator operator, Expression operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public UnaryOperator getOperator() {
        return operator;
    }

    public Expression getOperand() {
        return operand;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitUnaryExpression(this);
    }

    public enum UnaryOperator {
        POSITIVE("+"),
        NEGATIVE("-"),
        NOT("!");

        private final String symbol;

        UnaryOperator(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        public static UnaryOperator fromSymbol(String symbol) {
            for (UnaryOperator op : values()) {
                if (op.symbol.equals(symbol)) {
                    return op;
                }
            }
            throw new IllegalArgumentException("Unknown unary operator: " + symbol);
        }
    }
}
