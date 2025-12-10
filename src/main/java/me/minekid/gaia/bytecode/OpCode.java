package me.minekid.gaia.bytecode;

public enum OpCode {
    // Stack operations
    NOP(0), // No operation
    POP(0), // Pop top of the stack
    DUP(0), // Duplicate top of stack

    // Constants and literals
    CONST_NULL(0), // Push null onto stack
    CONST_TRUE(0), // Push true onto stack
    CONST_FALSE(0), // Push false onto stack
    CONST_INT(8), // Push int64 (8 bytes follow)
    CONST_FLOAT(8), // Push float64 (8 bytes follow)
    LOAD_CONST(2), // Load constant from the pool (index as 2 bytes)

    // Variables
    LOAD_LOCAL(2), // Load local variable (slot index as 2 bytes)
    STORE_LOCAL(2), // Store to local variable (slot index as 2 bytes)
    LOAD_GLOBAL(2), // Load global variable (name index as 2 bytes)
    STORE_GLOBAL(2), // Store to global variable (name index as 2 bytes)

    // Arithmetic
    ADD(0), // Pop 2, push sum
    SUB(0), // Pop 2, push difference
    MUL(0), // Pop 2, push product
    DIV(0), // Pop 2, push quotient
    MOD(0), // Pop 2, push the remainder
    NEG(0), // Negate top of stack

    // Comparison
    EQ(0), // Pop 2, push boolean equal
    NE(0), // Pop 2, push boolean not equal
    LT(0), // Pop 2, push boolean less than
    LE(0), // Pop 2, push boolean less than or equal
    GT(0), // Pop 2, push boolean greater than
    GE(0), // Pop 2, push boolean greater than or equal

    // Logical
    NOT(0), // Logical not
    AND(0), // Logical and (short-circuit via jumps)
    OR(0), // Logical or (short-circuit via jumps)

    // Control flow
    JUMP(2), // Unconditional jump (offset as 2 bytes)
    JUMP_IF_FALSE(2), // Jump if top of stack is false (offset as 2 bytes)
    JUMP_IF_TRUE(2), // Jump if top of stack is true (offset as 2 bytes)

    // Functions
    CALL(1), // Call function (arg count as 1 byte)
    RETURN(0), // Return from function
    LOAD_FUNCTION(2), // Load function from function table (index as 2 bytes)

    // Arrays
    NEW_ARRAY(2), // Create a new array (element count as 2 bytes)
    ARRAY_GET(0), // Get array element: pop index, pop array, push element
    ARRAY_SET(0), // Set array element: pop value, pop index, pop array
    ARRAY_LENGTH(0), // Get array length

    // Objects
    NEW_OBJECT(2), // Create a new object (class index as 2 bytes)
    GET_FIELD(2), // Get field: pop object, push field (field name index as 2 bytes)
    SET_FIELD(2), // Set field: pop value, pop object (field name index as 2 bytes)
    GET_METHOD(2), // Get method: pop an object, push bound method (method name index as 2 bytes)

    // String operations
    CONCAT(0), // Concatenate two strings

    // Errors
    FAIL(0), // Create and return error from top of stack
    IS_ERROR(0), // Check if top of stack is error

    // Debug
    PRINT(0), // Print top of stack (for debugging)

    // End marker
    HALT(0); // Stop execution

    private final int operandBytes;

    OpCode(int operandBytes) {
        this.operandBytes = operandBytes;
    }

    public int getOperandBytes() {
        return operandBytes;
    }

    public int getInstructionSize() {
        return 1 + operandBytes;
    }

    public static OpCode fromByte(byte b) {
        return values()[b];
    }
}
