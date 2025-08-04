# Java Operators Guide

This guide provides a comprehensive overview of Java's operators, organized into arithmetic, bitwise, relational, and logical categories. It includes detailed explanations, examples, and exercises to help you master their usage. The `instanceof` and arrow (`->`) operators are excluded, as they are covered in Chapters 13 and 15, respectively.

## Table of Contents
1. [Arithmetic Operators](#arithmetic-operators)
   - [Basic Arithmetic Operators](#basic-arithmetic-operators)
   - [Modulus Operator](#modulus-operator)
   - [Arithmetic Compound Assignment Operators](#arithmetic-compound-assignment-operators)
   - [Increment and Decrement Operators](#increment-and-decrement-operators)
   - [Example: Arithmetic Operations](#example-arithmetic-operations)
   - [Exercise: Arithmetic Operators](#exercise-arithmetic-operators)
2. [Bitwise Operators](#bitwise-operators)
   - [Bitwise Logical Operators](#bitwise-logical-operators)
   - [Shift Operators](#shift-operators)
   - [Bitwise Compound Assignment Operators](#bitwise-compound-assignment-operators)
   - [Example: Bitwise Operations](#example-bitwise-operations)
   - [Exercise: Bitwise Operators](#exercise-bitwise-operators)
3. [Relational Operators](#relational-operators)
   - [Example: Relational Operations](#example-relational-operations)
   - [Exercise: Relational Operators](#exercise-relational-operators)
4. [Logical Operators](#logical-operators)
   - [Boolean Logical Operators](#boolean-logical-operators)
   - [Short-Circuit Logical Operators](#short-circuit-logical-operators)
   - [Example: Logical Operations](#example-logical-operations)
   - [Exercise: Logical Operators](#exercise-logical-operators)
5. [Assignment Operator](#assignment-operator)
   - [Example: Assignment Operations](#example-assignment-operations)
   - [Exercise: Assignment Operator](#exercise-assignment-operator)
6. [Ternary Operator](#ternary-operator)
   - [Example: Ternary Operator](#example-ternary-operator)
   - [Exercise: Ternary Operator](#exercise-ternary-operator)
7. [Operator Precedence](#operator-precedence)
   - [Using Parentheses](#using-parentheses)
8. [Conclusion](#conclusion)

---

## Arithmetic Operators

Arithmetic operators perform mathematical operations similar to those in algebra. They work with numeric types (`int`, `double`, `float`, `long`, `short`, `byte`, `char`) but not with `boolean`.

### Basic Arithmetic Operators
The basic arithmetic operators are:

| Operator | Result |
|----------|--------|
| `+`      | Addition (also unary plus) |
| `–`      | Subtraction (also unary minus) |
| `*`      | Multiplication |
| `/`      | Division |
| `%`      | Modulus |
| `++`     | Increment |
| `--`     | Decrement |

- **Unary Plus (`+`)**: Returns the value of its operand (e.g., `+5` yields `5`).
- **Unary Minus (`-`)**: Negates its operand (e.g., `-5` yields `-5`).
- **Division (`/`)**: For integers, results in no fractional part (e.g., `7 / 2` yields `3`). For floating-point types, includes the fractional part (e.g., `7.0 / 2` yields `3.5`).

### Modulus Operator
The modulus operator (`%`) returns the remainder of a division operation and works with both integer and floating-point types.

### Arithmetic Compound Assignment Operators
These operators combine an arithmetic operation with assignment, providing shorthand and potential efficiency:

| Operator | Example | Equivalent |
|----------|---------|------------|
| `+=`     | `a += 4` | `a = a + 4` |
| `-=`     | `a -= 4` | `a = a - 4` |
| `*=`     | `a *= 4` | `a = a * 4` |
| `/=`     | `a /= 4` | `a = a / 4` |
| `%=`     | `a %= 4` | `a = a % 4` |

### Increment and Decrement Operators
- **Increment (`++`)**: Increases the operand by 1 (e.g., `x++` or `++x`).
- **Decrement (`--`)**: Decreases the operand by 1 (e.g., `x--` or `--x`).
- **Prefix vs. Postfix**:
  - **Prefix (`++x`, `--x`)**: Increments/decrements before the value is used in the expression.
  - **Postfix (`x++`, `x--`)**: Uses the current value in the expression, then increments/decrements.

### Example: Arithmetic Operations
This program demonstrates basic arithmetic operators with integers and floating-point numbers:

```java
class BasicMath {
    public static void main(String[] args) {
        // Integer arithmetic
        System.out.println("Integer Arithmetic");
        int a = 1 + 1;
        int b = a * 3;
        int c = b / 4;
        int d = c - a;
        int e = -d;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);
        System.out.println("e = " + e);

        // Floating-point arithmetic
        System.out.println("\nFloating Point Arithmetic");
        double da = 1 + 1;
        double db = da * 3;
        double dc = db / 4;
        double dd = dc - a;
        double de = -dd;
        System.out.println("da = " + da);
        System.out.println("db = " + db);
        System.out.println("dc = " + dc);
        System.out.println("dd = " + dd);
        System.out.println("de = " + de);
    }
}
```

**Output:**
```
Integer Arithmetic
a = 2
b = 6
c = 1
d = -1
e = 1

Floating Point Arithmetic
da = 2.0
db = 6.0
dc = 1.5
dd = -0.5
de = 0.5
```

**Explanation**: The program performs basic arithmetic operations on integers (`int`) and floating-point numbers (`double`). Integer division (`b / 4`) truncates the result to `1`, while floating-point division (`db / 4`) yields `1.5`, showing the difference in handling fractional parts.

### Exercise: Arithmetic Operators
Write a Java program that:
1. Declares two integers, `x = 25` and `y = 7`.
2. Performs addition, subtraction, multiplication, division, and modulus operations.
3. Uses compound assignment operators to modify `x` (e.g., `x += y`, `x *= 2`).
4. Demonstrates increment and decrement operators (both prefix and postfix).
5. Prints the results of each operation.

**Solution**:
```java
class ArithmeticExercise {
    public static void main(String[] args) {
        int x = 25;
        int y = 7;

        // Basic arithmetic
        System.out.println("x + y = " + (x + y));
        System.out.println("x - y = " + (x - y));
        System.out.println("x * y = " + (x * y));
        System.out.println("x / y = " + (x / y));
        System.out.println("x % y = " + (x % y));

        // Compound assignments
        x += y; // x = x + y
        System.out.println("x += y: " + x);
        x *= 2; // x = x * 2
        System.out.println("x *= 2: " + x);

        // Increment and decrement
        System.out.println("Postfix x++: " + (x++));
        System.out.println("After x++: " + x);
        System.out.println("Prefix ++x: " + (++x));
        System.out.println("Postfix x--: " + (x--));
        System.out.println("After x--: " + x);
        System.out.println("Prefix --x: " + (--x));
    }
}
```

**Expected Output**:
```
x + y = 32
x - y = 18
x * y = 175
x / y = 3
x % y = 4
x += y: 32
x *= 2: 64
Postfix x++: 64
After x++: 65
Prefix ++x: 66
Postfix x--: 66
After x--: 65
Prefix --x: 64
```

**Explanation**: The program demonstrates basic arithmetic operations, compound assignments, and the difference between prefix and postfix increment/decrement operators. Postfix operations use the current value before modifying, while prefix operations modify the value first.

---

## Bitwise Operators

Bitwise operators manipulate individual bits of integer types (`long`, `int`, `short`, `char`, `byte`). Java uses two’s complement for signed integers, where negative numbers are represented by inverting bits and adding 1.

### Bitwise Logical Operators
These operators perform logical operations on each bit of their operands:

| Operator | Result |
|----------|--------|
| `~`      | Bitwise NOT (inverts all bits) |
| `&`      | Bitwise AND (1 if both bits are 1) |
| `|`      | Bitwise OR (1 if either bit is 1) |
| `^`      | Bitwise XOR (1 if exactly one bit is 1) |

**Example**:
- `42 & 15` (binary: `00101010 & 00001111` = `00001010` = `10`)
- `42 | 15` (binary: `00101010 | 00001111` = `00101111` = `47`)
- `42 ^ 15` (binary: `00101010 ^ 00001111` = `00100101` = `37`)
- `~42` (binary: `~00101010` = `11010101` = `-43` in two’s complement)

### Shift Operators
Shift operators move bits left or right:

| Operator | Result |
|----------|--------|
| `<<`     | Left shift (shifts bits left, fills with zeros on right) |
| `>>`     | Right shift (shifts bits right, sign-extends) |
| `>>>`    | Unsigned right shift (shifts bits right, fills with zeros) |

- **Left Shift (`<<`)**: Doubles the value per shift (e.g., `32 << 1` = `64`). Bits shifted past position 31 (for `int`) or 63 (for `long`) are lost.
- **Right Shift (`>>`)**: Divides by 2 per shift, preserving the sign via sign extension (e.g., `-8 >> 1` = `-4`).
- **Unsigned Right Shift (`>>>`)**: Shifts zeros into the high-order bit, ignoring the sign (e.g., `-1 >>> 24` = `255`).

### Bitwise Compound Assignment Operators
These combine bitwise operations with assignment:

| Operator | Example | Equivalent |
|----------|---------|------------|
| `&=`     | `a &= b` | `a = a & b` |
| `|=`     | `a |= b` | `a = a | b` |
| `^=`     | `a ^= b` | `a = a ^ b` |
| `<<=`    | `a <<= n` | `a = a << n` |
| `>>=`    | `a >>= n` | `a = a >> n` |
| `>>>=`   | `a >>>= n` | `a = a >>> n` |

### Example: Bitwise Operations
This program demonstrates bitwise logical and shift operators:

```java
class BitLogic {
    public static void main(String[] args) {
        String[] binary = {
            "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
            "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"
        };
        int a = 3; // 0011
        int b = 6; // 0110
        int c = a | b; // 0111
        int d = a & b; // 0010
        int e = a ^ b; // 0101
        int f = (~a & b) | (a & ~b); // 0101
        int g = ~a & 0x0f; // 1100

        System.out.println("a = " + binary[a]);
        System.out.println("b = " + binary[b]);
        System.out.println("a|b = " + binary[c]);
        System.out.println("a&b = " + binary[d]);
        System.out.println("a^b = " + binary[e]);
        System.out.println("~a&b|a&~b = " + binary[f]);
        System.out.println("~a = " + binary[g]);
    }
}
```

**Output**:
```
a = 0011
b = 0110
a|b = 0111
a&b = 0010
a^b = 0101
~a&b|a&~b = 0101
~a = 1100
```

**Explanation**: The program uses a string array to display binary representations of results. It performs bitwise OR, AND, XOR, and a complex expression involving NOT, demonstrating how bits are manipulated. The `~a & 0x0f` operation masks the result to fit within the `binary` array.

### Exercise: Bitwise Operators
Write a Java program that:
1. Declares two integers, `x = 42` and `y = 15`.
2. Performs bitwise AND, OR, XOR, and NOT operations.
3. Demonstrates left shift, right shift, and unsigned right shift on `x`.
4. Uses compound bitwise assignment operators (e.g., `x &= y`).
5. Prints the results in both decimal and binary formats.

**Solution**:
```java
class BitwiseExercise {
    public static void main(String[] args) {
        int x = 42; // 00101010
        int y = 15; // 00001111

        // Bitwise operations
        System.out.println("x & y = " + (x & y) + " (" + Integer.toBinaryString(x & y) + ")");
        System.out.println("x | y = " + (x | y) + " (" + Integer.toBinaryString(x | y) + ")");
        System.out.println("x ^ y = " + (x ^ y) + " (" + Integer.toBinaryString(x ^ y) + ")");
        System.out.println("~x = " + (~x) + " (" + Integer.toBinaryString(~x) + ")");

        // Shift operations
        System.out.println("x << 2 = " + (x << 2) + " (" + Integer.toBinaryString(x << 2) + ")");
        System.out.println("x >> 2 = " + (x >> 2) + " (" + Integer.toBinaryString(x >> 2) + ")");
        System.out.println("x >>> 2 = " + (x >>> 2) + " (" + Integer.toBinaryString(x >>> 2) + ")");

        // Compound assignments
        x &= y; // x = x & y
        System.out.println("x &= y: " + x + " (" + Integer.toBinaryString(x) + ")");
        x = 42; // Reset x
        x |= y; // x = x | y
        System.out.println("x |= y: " + x + " (" + Integer.toBinaryString(x) + ")");
    }
}
```

**Expected Output**:
```
x & y = 10 (1010)
x | y = 47 (101111)
x ^ y = 37 (100101)
~x = -43 (11111111111111111111111111010101)
x << 2 = 168 (10101000)
x >> 2 = 10 (1010)
x >>> 2 = 10 (1010)
x &= y: 10 (1010)
x |= y: 47 (101111)
```

**Explanation**: The program performs bitwise operations on `x` and `y`, displaying results in decimal and binary using `Integer.toBinaryString`. Shift operations demonstrate doubling (`<<`) and division (`>>`, `>>>`). Compound assignments modify `x` efficiently.

---

## Relational Operators

Relational operators compare two operands and return a `boolean` result, used primarily in control structures like `if` and loops.

| Operator | Result |
|----------|--------|
| `==`     | Equal to |
| `!=`     | Not equal to |
| `>`      | Greater than |
| `<`      | Less than |
| `>=`     | Greater than or equal to |
| `<=`     | Less than or equal to |

- **Equality (`==`, `!=`)**: Works with all types (integers, floating-point, characters, Booleans).
- **Ordering (`>`, `<`, `>=`, `<=`)**: Works with numeric types and `char`, not `boolean`.
- **Note**: In Java, `true` and `false` are not numeric (unlike C/C++), so explicit comparisons (e.g., `done == 0`) are required.

### Example: Relational Operations
```java
class RelationalDemo {
    public static void main(String[] args) {
        int a = 4;
        int b = 1;
        boolean c = a < b;
        System.out.println("a < b: " + c);
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));
    }
}
```

**Output**:
```
a < b: false
a == b: false
a != b: true
a > b: true
a >= b: true
a <= b: false
```

**Explanation**: The program compares `a` and `b` using all relational operators, storing the result of `a < b` in a boolean variable and printing others directly. All results are `boolean` (`true` or `false`).

### Exercise: Relational Operators
Write a Java program that:
1. Declares two variables, `x = 10` and `y = 20`.
2. Performs all relational operations (`==`, `!=`, `>`, `<`, `>=`, `<=`).
3. Stores results in boolean variables and prints them.
4. Tests equality with a floating-point number (e.g., `10.0`).

**Solution**:
```java
class RelationalExercise {
    public static void main(String[] args) {
        int x = 10;
        int y = 20;
        double z = 10.0;

        boolean eq = x == y;
        boolean ne = x != y;
        boolean gt = x > y;
        boolean lt = x < y;
        boolean ge = x >= y;
        boolean le = x <= y;
        boolean eqDouble = x == z;

        System.out.println("x == y: " + eq);
        System.out.println("x != y: " + ne);
        System.out.println("x > y: " + gt);
        System.out.println("x < y: " + lt);
        System.out.println("x >= y: " + ge);
        System.out.println("x <= y: " + le);
        System.out.println("x == z: " + eqDouble);
    }
}
```

**Expected Output**:
```
x == y: false
x != y: true
x > y: false
x < y: true
x >= y: false
x <= y: true
x == z: true
```

**Explanation**: The program compares `x` and `y` using all relational operators, storing results in boolean variables. It also compares `x` with a `double` (`z`), showing that equality tests work across compatible types.

---

## Logical Operators

Logical operators operate on `boolean` operands, producing `boolean` results. They are used in control structures and conditional expressions.

### Boolean Logical Operators
| Operator | Result |
|----------|--------|
| `&`      | Logical AND |
| `|`      | Logical OR |
| `^`      | Logical XOR |
| `!`      | Logical NOT |
| `&&`     | Short-circuit AND |
| `||`     | Short-circuit OR |
| `&=`     | AND assignment |
| `|=`     | OR assignment |
| `^=`     | XOR assignment |

### Short-Circuit Logical Operators
- **Short-circuit AND (`&&`)**: Evaluates the right operand only if the left is `true`.
- **Short-circuit OR (`||`)**: Evaluates the right operand only if the left is `false`.
- **Use Case**: Prevents errors in expressions like `if (denom != 0 && num / denom > 10)`, where division is only attempted if `denom != 0`.

### Example: Logical Operations
```java
class BoolLogic {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        boolean c = a | b;
        boolean d = a & b;
        boolean e = a ^ b;
        boolean f = (!a & b) | (a & !b);
        boolean g = !a;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a | b = " + c);
        System.out.println("a & b = " + d);
        System.out.println("a ^ b = " + e);
        System.out.println("!a & b | a & !b = " + f);
        System.out.println("!a = " + g);
    }
}
```

**Output**:
```
a = true
b = false
a | b = true
a & b = false
a ^ b = true
!a & b | a & !b = true
!a = false
```

**Explanation**: The program applies logical operators to `boolean` values, mirroring bitwise operations but producing `true` or `false`. The complex expression `(!a & b) | (a & !b)` demonstrates combining operators.

### Exercise: Logical Operators
Write a Java program that:
1. Declares two boolean variables, `a = true` and `b = false`.
2. Performs logical AND, OR, XOR, and NOT operations.
3. Uses short-circuit operators to prevent division by zero in an expression.
4. Prints all results.

**Solution**:
```java
class LogicalExercise {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        int num = 10;
        int denom = 0;

        System.out.println("a & b: " + (a & b));
        System.out.println("a | b: " + (a | b));
        System.out.println("a ^ b: " + (a ^ b));
        System.out.println("!a: " + (!a));

        // Short-circuit example
        boolean safeDivision = denom != 0 && num / denom > 5;
        System.out.println("Safe division check: " + safeDivision);
    }
}
```

**Expected Output**:
```
a & b: false
a | b: true
a ^ b: true
!a: false
Safe division check: false
```

**Explanation**: The program performs logical operations on `a` and `b`. The short-circuit `&&` prevents a division-by-zero error by checking `denom != 0` first, ensuring the division is not evaluated if `denom` is zero.

---

## Assignment Operator

The assignment operator (`=`) assigns the value of an expression to a variable. It supports chaining (e.g., `x = y = z = 100`), where the rightmost expression’s value is assigned to all variables.

### Example: Assignment Operations
```java
class AssignmentDemo {
    public static void main(String[] args) {
        int x, y, z;
        x = y = z = 100; // Chained assignment
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("z = " + z);
    }
}
```

**Output**:
```
x = 100
y = 100
z = 100
```

**Explanation**: The chained assignment sets `x`, `y`, and `z` to `100` in a single statement, demonstrating the operator’s ability to propagate a value.

### Exercise: Assignment Operator
Write a Java program that:
1. Declares three variables (`x`, `y`, `z`) of type `double`.
2. Uses chained assignment to set all to `50.5`.
3. Modifies `y` using a compound assignment with another operator (e.g., `+=`).
4. Prints the results.

**Solution**:
```java
class AssignmentExercise {
    public static void main(String[] args) {
        double x, y, z;
        x = y = z = 50.5;
        y += 10.5; // y = y + 10.5
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("z = " + z);
    }
}
```

**Expected Output**:
```
x = 50.5
y = 61.0
z = 50.5
```

**Explanation**: The program uses chained assignment to set all variables to `50.5`. The compound assignment `y += 10.5` modifies only `y`, leaving `x` and `z` unchanged.

---

## Ternary Operator

The ternary operator (`?:`) is a concise alternative to `if-then-else` statements, with the form:
```
expression1 ? expression2 : expression3
```
If `expression1` is `true`, `expression2` is evaluated; otherwise, `expression3` is evaluated. Both expressions must return compatible types.

### Example: Ternary Operator
```java
class Ternary {
    public static void main(String[] args) {
        int i, k;
        i = 10;
        k = i < 0 ? -i : i; // Absolute value
        System.out.println("Absolute value of " + i + " is " + k);
        i = -10;
        k = i < 0 ? -i : i; // Absolute value
        System.out.println("Absolute value of " + i + " is " + k);
    }
}
```

**Output**:
```
Absolute value of 10 is 10
Absolute value of -10 is 10
```

**Explanation**: The ternary operator computes the absolute value of `i` by returning `-i` if `i < 0`, else `i`. It’s a concise way to handle conditional assignments.

### Exercise: Ternary Operator
Write a Java program that:
1. Declares a variable `score = 85`.
2. Uses the ternary operator to assign a grade (`A` if `score >= 90`, else `B`).
3. Prints the score and grade.

**Solution**:
```java
class TernaryExercise {
    public static void main(String[] args) {
        int score = 85;
        char grade = score >= 90 ? 'A' : 'B';
        System.out.println("Score: " + score + ", Grade: " + grade);
    }
}
```

**Expected Output**:
```
Score: 85, Grade: B
```

**Explanation**: The ternary operator assigns `grade` based on whether `score >= 90`. Since `score` is `85`, `grade` is set to `B`.

---

## Operator Precedence

Operator precedence determines the order of evaluation in expressions. Operators with higher precedence are evaluated first. Parentheses can override precedence.

| Precedence | Operators |
|------------|-----------|
| Highest    | `++ (postfix)`, `-- (postfix)` |
|            | `++ (prefix)`, `-- (prefix)`, `~`, `!`, `+ (unary)`, `- (unary)`, `(type-cast)` |
|            | `*`, `/`, `%` |
|            | `+`, `-` |
|            | `>>`, `>>>`, `<<` |
|            | `>`, `>=`, `<`, `<=`, `instanceof` |
|            | `==`, `!=` |
|            | `&` |
|            | `^` |
|            | `|` |
|            | `&&` |
|            | `||` |
|            | `?:` |
|            | `->` |
| Lowest     | `=`, `op=` |

### Using Parentheses
Parentheses raise the precedence of enclosed operations and improve code clarity. For example:
- `a >> b + 3`: Adds `b + 3`, then shifts `a` right.
- `(a >> b) + 3`: Shifts `a` right by `b`, then adds `3`.
- Complex expressions like `a | 4 + c >> b & 7` are clearer with parentheses: `(a | ((4 + c) >> b) & 7)`.

---

## Conclusion

This guide covers Java’s arithmetic, bitwise, relational, logical, assignment, and ternary operators, with examples and exercises to reinforce understanding. Mastering these operators is essential for effective Java programming, enabling precise control over computations and logic.
