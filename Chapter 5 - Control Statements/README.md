# Java Control Statements

This document provides an in-depth explanation of Java's control statements, categorized into **Selection**, **Iteration**, and **Jump** statements. Each section includes detailed explanations, examples, and exercises to reinforce understanding. This guide is designed for programmers learning Java's control flow mechanisms and is suitable for inclusion in a GitHub repository as a README file.

---

## Introduction to Control Statements

A programming language uses **control statements** to manage the flow of execution, allowing programs to make decisions, repeat actions, or jump to different parts of the code based on conditions. In Java, control statements are divided into three categories:

1. **Selection Statements**: Allow the program to choose different execution paths based on the outcome of an expression or variable state.
2. **Iteration Statements**: Enable repeated execution of one or more statements, forming loops.
3. **Jump Statements**: Allow nonlinear execution by transferring control to another part of the program.

Below, we explore each category in detail, with examples and exercises to illustrate their use.

---

## Selection Statements

Selection statements allow a program to choose different execution paths based on conditions evaluated at runtime. Java supports two selection statements: `if` and `switch`.

### if Statement

The `if` statement is Java's conditional branch statement, allowing execution to follow one of two paths based on a boolean condition.

**General Form**:
```java
if (condition) statement1;
else statement2;
```
- **condition**: A boolean expression.
- **statement1**: Executed if the condition is `true`.
- **statement2**: Executed if the condition is `false` (optional, requires `else`).
- Both statements can be single statements or blocks enclosed in curly braces `{}`.

**How it Works**:
- If `condition` evaluates to `true`, `statement1` is executed.
- If `condition` is `false`, `statement2` (if present) is executed.
- Only one of the two statements (or blocks) is executed.

**Example**:
```java
int a = 5, b = 10;
if (a < b) a = 0;
else b = 0;
```
In this example, since `a < b` is `true`, `a` is set to `0`, and `b` remains `10`.

**Common Pitfall**:
Forgetting curly braces when multiple statements are needed can lead to errors. For example:
```java
int bytesAvailable = 5;
if (bytesAvailable > 0) {
    ProcessData();
    bytesAvailable -= n;
} else {
    waitForMoreData();
    bytesAvailable = n;
}
```
Without curly braces around the `else` block, `bytesAvailable = n;` would execute regardless of the condition, causing unintended behavior.

**Best Practice**:
Always use curly braces for clarity, even for single statements, to avoid errors when adding more statements later.

**Nested if Statements**:
An `if` statement can be nested within another `if` or `else`. The `else` always associates with the nearest unassociated `if` in the same block.

**Example**:
```java
int i = 10, j = 15, k = 120;
if (i == 10) {
    if (j < 20) a = b;
    if (k > 100) c = d;
    else a = c; // Associates with if(k > 100)
} else a = d; // Associates with if(i == 10)
```

**if-else-if Ladder**:
A sequence of nested `if` statements, where conditions are checked sequentially until one is `true`, or the `else` block (if present) is executed.

**General Form**:
```java
if (condition1) statement1;
else if (condition2) statement2;
else if (condition3) statement3;
...
else defaultStatement;
```

**Example**:
```java
class IfElse {
    public static void main(String[] args) {
        int month = 4; // April
        String season;
        if (month == 12 || month == 1 || month == 2)
            season = "Winter";
        else if (month == 3 || month == 4 || month == 5)
            season = "Spring";
        else if (month == 6 || month == 7 || month == 8)
            season = "Summer";
        else if (month == 9 || month == 10 || month == 11)
            season = "Autumn";
        else
            season = "Bogus Month";
        System.out.println("April is in the " + season + ".");
    }
}
```
**Output**:
```
April is in the Spring.
```

**Exercise**:
Write a program that takes a numeric grade (0–100) and assigns a letter grade (A, B, C, D, F) using an `if-else-if` ladder. For example:
- 90–100: A
- 80–89: B
- 70–79: C
- 60–69: D
- Below 60: F

**Solution**:
```java
class GradeCalculator {
    public static void main(String[] args) {
        int grade = 85;
        String letterGrade;
        if (grade >= 90)
            letterGrade = "A";
        else if (grade >= 80)
            letterGrade = "B";
        else if (grade >= 70)
            letterGrade = "C";
        else if (grade >= 60)
            letterGrade = "D";
        else
            letterGrade = "F";
        System.out.println("Grade: " + grade + " -> Letter Grade: " + letterGrade);
    }
}
```
**Explanation**:
This program checks the value of `grade` against each condition in sequence. For `grade = 85`, the condition `grade >= 80` is true, so `letterGrade` is set to `"B"`.

### switch Statement

The `switch` statement is Java’s multiway branch statement, dispatching execution based on the value of an expression. It is often more efficient than multiple `if-else-if` statements for selecting among many values.

**General Form (Traditional)**:
```java
switch (expression) {
    case value1:
        // statement sequence
        break;
    case value2:
        // statement sequence
        break;
    ...
    case valueN:
        // statement sequence
        break;
    default:
        // default statement sequence
}
```
- **expression**: Must resolve to `byte`, `short`, `int`, `char`, `String`, or an enumeration (since JDK 7 for `String`).
- **case values**: Must be unique constant expressions (e.g., literals).
- **break**: Terminates the `switch` block, jumping to the code after the `switch`.
- **default**: Optional, executed if no case matches.

**How it Works**:
- The `expression` is evaluated and compared with each `case` value.
- If a match is found, the corresponding statement sequence is executed until a `break` or the end of the `switch`.
- If no match is found and `default` is present, the `default` block is executed.

**Example**:
```java
class SampleSwitch {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++)
            switch (i) {
                case 0:
                    System.out.println("i is zero.");
                    break;
                case 1:
                    System.out.println("i is one.");
                    break;
                case 2:
                    System.out.println("i is two.");
                    break;
                case 3:
                    System.out.println("i is three.");
                    break;
                default:
                    System.out.println("i is greater than 3.");
            }
    }
}
```
**Output**:
```
i is zero.
i is one.
i is two.
i is three.
i is greater than 3.
i is greater than 3.
```

**Without break**:
Omitting `break` causes execution to "fall through" to subsequent cases until a `break` or the end of the `switch` is reached.

**Example (Fall-Through)**:
```java
class MissingBreak {
    public static void main(String[] args) {
        for (int i = 0; i < 12; i++)
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    System.out.println("i is less than 5");
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    System.out.println("i is less than 10");
                    break;
                default:
                    System.out.println("i is 10 or more");
            }
    }
}
```
**Output**:
```
i is less than 5
i is less than 5
i is less than 5
i is less than 5
i is less than 5
i is less than 10
i is less than 10
i is less than 10
i is less than 10
i is less than 10
i is 10 or more
i is 10 or more
```

**Using Strings in switch**:
Since JDK 7, `switch` supports `String` expressions.

**Example**:
```java
class StringSwitch {
    public static void main(String[] args) {
        String str = "two";
        switch (str) {
            case "one":
                System.out.println("one");
                break;
            case "two":
                System.out.println("two");
                break;
            case "three":
                System.out.println("three");
                break;
            default:
                System.out.println("no match");
                break;
        }
    }
}
```
**Output**:
```
two
```

**Nested switch Statements**:
A `switch` can be nested within another `switch`. Case constants in inner and outer switches do not conflict as each `switch` defines its own block.

**Example**:
```java
int count = 1, target = 1;
switch (count) {
    case 1:
        switch (target) {
            case 0:
                System.out.println("target is zero");
                break;
            case 1:
                System.out.println("target is one");
                break;
        }
        break;
}
```
**Output**:
```
target is one
```

**Exercise**:
Rewrite the season example using a `switch` statement with fall-through to determine the season for a given month.

**Solution**:
```java
class SwitchSeason {
    public static void main(String[] args) {
        int month = 4;
        String season;
        switch (month) {
            case 12:
            case 1:
            case 2:
                season = "Winter";
                break;
            case 3:
            case 4:
            case 5:
                season = "Spring";
                break;
            case 6:
            case 7:
            case 8:
                season = "Summer";
                break;
            case 9:
            case 10:
            case 11:
                season = "Autumn";
                break;
            default:
                season = "Bogus Month";
        }
        System.out.println("April is in the " + season + ".");
    }
}
```
**Explanation**:
This program uses fall-through to group months into seasons, setting `season` to `"Spring"` for `month = 4`. The `break` statements ensure that only one season is assigned.

---

## Iteration Statements

Iteration statements create loops, allowing repeated execution of code until a condition is met. Java provides three iteration statements: `while`, `do-while`, and `for`.

### while Loop

The `while` loop is Java’s fundamental loop, repeating a statement or block while a condition is `true`.

**General Form**:
```java
while (condition) {
    // body of loop
}
```
- **condition**: A boolean expression.
- **body**: Executed as long as `condition` is `true`.
- Curly braces are optional for a single statement.

**How it Works**:
- The `condition` is evaluated before each iteration.
- If `true`, the body executes; if `false`, execution continues after the loop.

**Example**:
```java
class While {
    public static void main(String[] args) {
        int n = 10;
        while (n > 0) {
            System.out.println("tick " + n);
            n--;
        }
    }
}
```
**Output**:
```
tick 10
tick 9
tick 8
tick 7
tick 6
tick 5
tick 4
tick 3
tick 2
tick 1
```

**Empty Loop Body**:
A `while` loop can have an empty body (null statement), useful when all logic is in the condition.

**Example**:
```java
class NoBody {
    public static void main(String[] args) {
        int i = 100, j = 200;
        while (++i < --j); // No body
        System.out.println("Midpoint is " + i);
    }
}
```
**Output**:
```
Midpoint is 150
```

**Exercise**:
Write a program using a `while` loop to calculate the sum of numbers from 1 to 10.

**Solution**:
```java
class SumWhile {
    public static void main(String[] args) {
        int n = 1, sum = 0;
        while (n <= 10) {
            sum += n;
            n++;
        }
        System.out.println("Sum of 1 to 10: " + sum);
    }
}
```
**Explanation**:
The loop adds `n` to `sum` and increments `n` until `n > 10`. The result is `sum = 55`.

### do-while Loop

The `do-while` loop executes its body at least once, evaluating the condition at the end.

**General Form**:
```java
do {
    // body of loop
} while (condition);
```
- **condition**: A boolean expression.
- **body**: Executed at least once, then repeatedly while `condition` is `true`.

**How it Works**:
- The body executes first, then `condition` is checked.
- If `true`, the loop repeats; if `false`, it terminates.

**Example**:
```java
class DoWhile {
    public static void main(String[] args) {
        int n = 10;
        do {
            System.out.println("tick " + n);
        } while (--n > 0);
    }
}
```
**Output**:
```
tick 10
tick 9
tick 8
tick 7
tick 6
tick 5
tick 4
tick 3
tick 2
tick 1
```

**Use Case**:
`do-while` is ideal for scenarios like menu selection, where the menu must display at least once.

**Example**:
```java
class Menu {
    public static void main(String[] args) throws java.io.IOException {
        char choice;
        do {
            System.out.println("Help on: ");
            System.out.println(" 1. if");
            System.out.println(" 2. switch");
            System.out.println(" 3. while");
            System.out.println(" 4. do-while");
            System.out.println(" 5. for\n");
            System.out.println("Choose one:");
            choice = (char) System.in.read();
        } while (choice < '1' || choice > '5');
        System.out.println("\n");
        switch (choice) {
            case '1':
                System.out.println("The if:\nif(condition) statement;\nelse statement;");
                break;
            case '2':
                System.out.println("The switch:\nswitch(expression) {\n case constant:\n  statement sequence\n break;\n //...\n}");
                break;
            case '3':
                System.out.println("The while:\nwhile(condition) statement;");
                break;
            case '4':
                System.out.println("The do-while:\ndo {\n statement;\n} while (condition);");
                break;
            case '5':
                System.out.println("The for:\nfor(init; condition; iteration) statement;");
                break;
        }
    }
}
```
**Output (for input '4')**:
```
Help on:
 1. if
 2. switch
 3. while
 4. do-while
 5. for
Choose one:
4
The do-while:
do {
 statement;
} while (condition);
```

**Exercise**:
Create a program that prompts the user to enter a positive number using a `do-while` loop, ensuring the input is valid before proceeding.

**Solution**:
```java
class PositiveNumber {
    public static void main(String[] args) throws java.io.IOException {
        int number;
        do {
            System.out.println("Enter a positive number:");
            number = Integer.parseInt(new String(new char[]{(char) System.in.read()}));
            while (System.in.read() != '\n'); // Clear input buffer
        } while (number <= 0);
        System.out.println("You entered: " + number);
    }
}
```
**Explanation**:
The loop continues prompting until a positive number is entered. Note: This is a simplified version using console input; real-world applications would use more robust input methods.

### for Loop

The `for` loop is versatile, supporting both traditional and for-each styles.

#### Traditional for Loop

**General Form**:
```java
for (initialization; condition; iteration) {
    // body
}
```
- **initialization**: Executed once before the loop starts (e.g., setting a loop control variable).
- **condition**: A boolean expression checked before each iteration.
- **iteration**: Executed after each iteration (e.g., incrementing the loop control variable).
- Curly braces are optional for a single statement.

**How it Works**:
- `initialization` sets up the loop.
- `condition` is checked; if `true`, the body executes.
- After the body, `iteration` updates the control variable, and the loop repeats until `condition` is `false`.

**Example**:
```java
class ForTick {
    public static void main(String[] args) {
        for (int n = 10; n > 0; n--)
            System.out.println("tick " + n);
    }
}
```
**Output**:
```
tick 10
tick 9
tick 8
tick 7
tick 6
tick 5
tick 4
tick 3
tick 2
tick 1
```

**Declaring Loop Variables**:
The loop control variable can be declared inside the `for` loop, limiting its scope to the loop.

**Example**:
```java
for (int n = 10; n > 0; n--)
    System.out.println("tick " + n);
```

**Using Commas**:
Multiple variables can be initialized or updated using commas.

**Example**:
```java
class Comma {
    public static void main(String[] args) {
        int a, b;
        for (a = 1, b = 4; a < b; a++, b--) {
            System.out.println("a = " + a);
            System.out.println("b = " + b);
        }
    }
}
```
**Output**:
```
a = 1
b = 4
a = 2
b = 3
```

**Variations**:
- The `condition` can be any boolean expression, not just a comparison.
- `initialization` or `iteration` can be omitted, though this is rare.

**Example (Empty Parts)**:
```java
class ForVar {
    public static void main(String[] args) {
        int i = 0;
        boolean done = false;
        for (; !done; ) {
            System.out.println("i is " + i);
            if (i == 10) done = true;
            i++;
        }
    }
}
```
**Output**:
```
i is 0
i is 1
...
i is 10
```

**Infinite Loop**:
```java
for ( ; ; ) {
    // Infinite loop
}
```

**Exercise**:
Write a program to find prime numbers between 2 and 50 using a `for` loop.

**Solution**:
```java
class FindPrime {
    public static void main(String[] args) {
        for (int num = 2; num <= 50; num++) {
            boolean isPrime = true;
            for (int i = 2; i <= num / i; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) System.out.println(num + " is prime");
        }
    }
}
```
**Explanation**:
The outer loop iterates through numbers 2 to 50. The inner loop checks divisibility up to `num/i`. If no divisors are found, `num` is prime.

#### for-each Loop

The for-each loop (enhanced `for`) iterates over collections like arrays sequentially.

**General Form**:
```java
for (type itr-var : collection) {
    // statement-block
}
```
- **type**: Type of elements in the collection.
- **itr-var**: Iteration variable receiving each element.
- **collection**: The collection (e.g., an array).

**How it Works**:
- Iterates through `collection` from start to end, assigning each element to `itr-var`.
- The iteration variable is read-only for the underlying collection.

**Example**:
```java
class ForEach {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;
        for (int x : nums) {
            System.out.println("Value is: " + x);
            sum += x;
        }
        System.out.println("Summation: " + sum);
    }
}
```
**Output**:
```
Value is: 1
Value is: 2
...
Value is: 10
Summation: 55
```

**Using break**:
```java
class ForEach2 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;
        for (int x : nums) {
            System.out.println("Value is: " + x);
            sum += x;
            if (x == 5) break;
        }
        System.out.println("Summation of first 5 elements: " + sum);
    }
}
```
**Output**:
```
Value is: 1
Value is: 2
Value is: 3
Value is: 4
Value is: 5
Summation of first 5 elements: 15
```

**Multidimensional Arrays**:
For a multidimensional array, the for-each loop iterates over arrays of one dimension lower.

**Example**:
```java
class ForEach3 {
    public static void main(String[] args) {
        int[][] nums = new int[3][5];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 5; j++)
                nums[i][j] = (i + 1) * (j + 1);
        int sum = 0;
        for (int[] x : nums) {
            for (int y : x) {
                System.out.println("Value is: " + y);
                sum += y;
            }
        }
        System.out.println("Summation: " + sum);
    }
}
```
**Output**:
```
Value is: 1
Value is: 2
...
Value is: 15
Summation: 90
```

**Exercise**:
Use a for-each loop to find the maximum value in an array of integers.

**Solution**:
```java
class FindMax {
    public static void main(String[] args) {
        int[] nums = {6, 8, 3, 7, 5, 6, 1, 4};
        int max = nums[0];
        for (int x : nums) {
            if (x > max) max = x;
        }
        System.out.println("Maximum value: " + max);
    }
}
```
**Explanation**:
The for-each loop compares each element with `max`, updating `max` if a larger value is found. For the given array, `max = 8`.

---

## Jump Statements

Jump statements transfer control to another part of the program. Java supports three jump statements: `break`, `continue`, and `return`.

### break Statement

The `break` statement has three uses:
1. Terminate a `switch` statement.
2. Exit a loop.
3. Act as a "civilized" `goto` with a label.

#### Exiting a Loop
`break` terminates the enclosing loop, resuming execution at the next statement.

**Example**:
```java
class BreakLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if (i == 10) break;
            System.out.println("i: " + i);
        }
        System.out.println("Loop complete.");
    }
}
```
**Output**:
```
i: 0
i: 1
...
i: 9
Loop complete.
```

**Nested Loops**:
`break` only exits the innermost loop.

**Example**:
```java
class BreakLoop3 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.print("Pass " + i + ": ");
            for (int j = 0; j < 100; j++) {
                if (j == 10) break;
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("Loops complete.");
    }
}
```
**Output**:
```
Pass 0: 0 1 2 3 4 5 6 7 8 9
Pass 1: 0 1 2 3 4 5 6 7 8 9
Pass 2: 0 1 2 3 4 5 6 7 8 9
Loops complete.
```

#### Labeled break
A labeled `break` exits a specified block, useful for breaking out of nested loops.

**Example**:
```java
class BreakLoop4 {
    public static void main(String[] args) {
        outer: for (int i = 0; i < 3; i++) {
            System.out.print("Pass " + i + ": ");
            for (int j = 0; j < 100; j++) {
                if (j == 10) break outer;
                System.out.print(j + " ");
            }
            System.out.println("This will not print");
        }
        System.out.println("Loops complete.");
    }
}
```
**Output**:
```
Pass 0: 0 1 2 3 4 5 6 7 8 9 Loops complete.
```

**Exercise**:
Write a program using a labeled `break` to exit nested loops when the product of the loop variables exceeds 50.

**Solution**:
```java
class BreakProduct {
    public static void main(String[] args) {
        outer: for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (i * j > 50) break outer;
                System.out.print(i * j + " ");
            }
            System.out.println();
        }
        System.out.println("Loops terminated.");
    }
}
```
**Explanation**:
The loop prints products until `i * j > 50`, at which point the labeled `break outer` exits both loops.

### continue Statement

The `continue` statement skips the rest of the loop body, moving to the next iteration.

- In `while`/`do-while`, control goes to the condition.
- In `for`, control goes to the iteration expression, then the condition.

**Example**:
```java
class Continue {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            if (i % 2 == 0) continue;
            System.out.println("");
        }
    }
}
```
**Output**:
```
0 1
2 3
4 5
6 7
8 9
```

**Labeled continue**:
A labeled `continue` continues the specified outer loop.

**Example**:
```java
class ContinueLabel {
    public static void main(String[] args) {
        outer: for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j > i) {
                    System.out.println();
                    continue outer;
                }
                System.out.print(" " + (i * j));
            }
        }
        System.out.println();
    }
}
```
**Output**:
```
0
0 1
0 2 4
0 3 6 9
0 4 8 12 16
0 5 10 15 20 25
0 6 12 18 24 30 36
0 7 14 21 28 35 42 49
0 8 16 24 32 40 48 56 64
0 9 18 27 36 45 54 63 72 81
```

**Exercise**:
Write a program that prints only odd numbers from 1 to 20 using `continue`.

**Solution**:
```java
class OddNumbers {
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) continue;
            System.out.println(i);
        }
    }
}
```
**Explanation**:
The `continue` statement skips even numbers, printing only odd numbers.

### return Statement

The `return` statement exits a method, transferring control back to the caller.

**Example**:
```java
class Return {
    public static void main(String[] args) {
        boolean t = true;
        System.out.println("Before the return.");
        if (t) return;
        System.out.println("This won't execute.");
    }
}
```
**Output**:
```
Before the return.
```

**Exercise**:
Write a method that returns the factorial of a number, exiting early if the input is negative.

**Solution**:
```java
class Factorial {
    public static long factorial(int n) {
        if (n < 0) return -1;
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("Factorial of -1: " + factorial(-1));
    }
}
```
**Explanation**:
The method returns `-1` for negative inputs, otherwise computes the factorial using a loop.

---

## Conclusion

Java’s control statements—**selection** (`if`, `switch`), **iteration** (`while`, `do-while`, `for`), and **jump** (`break`, `continue`, `return`)—provide powerful mechanisms to control program flow. Understanding their syntax, behavior, and best practices is essential for writing efficient and maintainable code. The provided examples and exercises demonstrate practical applications, helping you master these constructs.

For further exploration, experiment with the exercises, modify the examples, and apply these concepts to real-world problems. Refer to Chapter 17 for advanced `switch` features introduced in JDK 14 and beyond.
