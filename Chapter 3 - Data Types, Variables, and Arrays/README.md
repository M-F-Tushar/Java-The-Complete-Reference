Chapter 3: Data Types, Variables, and Arrays in Java
This chapter explores three foundational elements of Java programming: data types, variables, and arrays. These components are critical for building robust and efficient Java programs. Java’s approach ensures type safety, efficiency, and portability across platforms. This document organizes the content in a structured format, including explanations, examples, and exercises to reinforce understanding.
Table of Contents

1. Java Is a Strongly Typed Language
2. The Primitive Types
2.1 Integers
2.2 Floating-Point Types
2.3 Characters
2.4 Booleans


3. A Closer Look at Literals
3.1 Integer Literals
3.2 Floating-Point Literals
3.3 Boolean Literals
3.4 Character Literals
3.5 String Literals


4. Variables
4.1 Declaring a Variable
4.2 Dynamic Initialization
4.3 Scope and Lifetime of Variables


5. Type Conversion and Casting
5.1 Java’s Automatic Conversions
5.2 Casting Incompatible Types
5.3 Automatic Type Promotion in Expressions


6. Arrays
6.1 One-Dimensional Arrays
6.2 Multidimensional Arrays
6.3 Alternative Array Declaration Syntax


7. Introducing Type Inference with Local Variables
8. A Few Words About Strings
9. Exercises


1. Java Is a Strongly Typed Language
Overview
Java is a strongly typed language, ensuring that every variable and expression has a strictly defined type, and type compatibility is rigorously enforced. This contributes to Java’s safety and robustness.

Key Characteristics:
Every variable and expression has a specific type.
All assignments (explicit or via method parameters) are checked for type compatibility.
No automatic coercions or conversions occur for conflicting types.
Type mismatches result in compile-time errors, ensuring errors are caught before execution.



Why It Matters
The strict type system prevents errors common in loosely typed languages, such as unexpected behavior from type conversions, ensuring reliable and predictable programs.

2. The Primitive Types
Java defines eight primitive types, also known as simple types, which are the building blocks for all other data types. These are not objects, prioritizing efficiency. They are grouped into four categories:

Integers: byte, short, int, long (whole-valued signed numbers).
Floating-point numbers: float, double (numbers with fractional precision).
Characters: char (symbols in a character set, e.g., letters, numbers).
Boolean: boolean (represents true or false values).

Why Primitive Types Are Not Objects
Primitive types are not objects to maintain performance efficiency. They have fixed sizes and behaviors, ensuring portability across platforms, unlike C/C++ where integer sizes may vary.

2.1 Integers
Java provides four integer types, all signed (support positive and negative values). Java does not support unsigned integers, using an unsigned right shift operator (covered in Chapter 4) instead.
Integer Types and Ranges



Type
Width (Bits)
Range



byte
8
-128 to 127


short
16
-32,768 to 32,767


int
32
-2,147,483,648 to 2,147,483,647


long
64
-9,223,372,036,854,775,808 to 9,223,372,036,854,775,807


Detailed Explanation

byte:
Smallest integer type, ideal for raw binary data or streams (e.g., files, networks).
Example: byte b, c;


short:
Least used, suitable for specific 16-bit integer needs.
Example: short s, t;


int:
Most common, used for loops and array indexing.
Automatically promoted from byte or short in expressions.
Example: int i;


long:
Used for large values, such as in big calculations.
Example: long l;



Example: Computing Distance Light Travels
This program uses long to calculate the distance light travels in a given number of days.
class Light {
    public static void main(String[] args) {
        int lightspeed;
        long days, seconds, distance;

        lightspeed = 186000; // Speed of light in miles per second
        days = 1000; // Number of days
        seconds = days * 24 * 60 * 60; // Convert to seconds
        distance = lightspeed * seconds; // Compute distance

        System.out.print("In " + days);
        System.out.print(" days light will travel about ");
        System.out.println(distance + " miles.");
    }
}

Output:
In 1000 days light will travel about 16070400000000 miles.

Explanation:

long is used for days, seconds, and distance to handle large values.
The program calculates distance by multiplying the speed of light by seconds in 1000 days.

Exercise:Modify the Light program to calculate the distance light travels in one year (365 days). Use appropriate variable types and print the result.

2.2 Floating-Point Types
Floating-point types handle numbers with fractional components, adhering to the IEEE-754 standard. They are used for precise calculations, such as mathematical functions.
Floating-Point Types and Ranges



Type
Width (Bits)
Approximate Range



float
32
1.4e–045 to 3.4e+038


double
64
4.9e–324 to 1.8e+308


Detailed Explanation

float:
Single-precision, faster but less precise for very large/small values.
Useful for applications like currency.
Example: float hightemp, lowtemp;


double:
Double-precision, preferred for high-precision calculations (e.g., sin(), cos(), sqrt()).
Example: double pi, radius, area;



Example: Compute the Area of a Circle
This program uses double to compute the area of a circle given its radius.
class Area {
    public static void main(String[] args) {
        double pi, r, a;

        r = 10.8; // Radius of circle
        pi = 3.1416; // Approximate value of pi
        a = pi * r * r; // Compute area

        System.out.println("Area of circle is " + a);
    }
}

Output:
Area of circle is 366.995904

Explanation:

double variables handle fractional values.
The area is calculated using π * r².

Exercise:Modify the Area program to calculate the area of a circle with radius 5.5 using float. Compare the precision with double.

2.3 Characters
The char type stores characters using Unicode (16-bit, range: 0 to 65,535), supporting all human languages. Java uses Unicode for global portability.
Key Points

No negative values.
Represents ASCII characters (e.g., 'X' is 88).
Supports arithmetic operations.

Example: Demonstrating char Variables
This program assigns ASCII values and character literals to char variables.
class CharDemo {
    public static void main(String[] args) {
        char ch1, ch2;

        ch1 = 88; // ASCII code for 'X'
        ch2 = 'Y';

        System.out.print("ch1 and ch2: ");
        System.out.println(ch1 + " " + ch2);
    }
}

Output:
ch1 and ch2: X Y

Explanation:

ch1 is assigned ASCII 88 ('X').
ch2 is assigned 'Y'.

Example: char as an Integer Type
This program shows char in arithmetic operations.
class CharDemo2 {
    public static void main(String[] args) {
        char ch1;

        ch1 = 'X';
        System.out.println("ch1 contains " + ch1);

        ch1++; // Increment ch1
        System.out.println("ch1 is now " + ch1);
    }
}

Output:
ch1 contains X
ch1 is now Y

Explanation:

ch1 starts as 'X' (ASCII 88) and increments to 'Y' (ASCII 89).

Exercise:Write a program that assigns 'A' to a char variable and increments it five times. Print the final character.

2.4 Booleans
The boolean type represents true or false, used in conditional expressions and relational operators.
Key Points

Only accepts true or false (not 1 or 0).
Directly controls conditionals without comparison to true.

Example: Demonstrating boolean
This program shows boolean in conditionals and relational operations.
class BoolTest {
    public static void main(String[] args) {
        boolean b;

        b = false;
        System.out.println("b is " + b);

        b = true;
        System.out.println("b is " + b);

        if (b) System.out.println("This is executed.");
        b = false;
        if (b) System.out.println("This is not executed.");

        System.out.println("10 > 9 is " + (10 > 9));
    }
}

Output:
b is false
b is true
This is executed.
10 > 9 is true

Explanation:

b toggles between false and true.
The if statement executes only when b is true.
10 > 9 evaluates to true.

Exercise:Write a program that checks if 42 is even using a boolean variable.

3. A Closer Look at Literals
Literals are fixed values in source code (e.g., 42, 3.14, 'a').
3.1 Integer Literals
Represent whole numbers in:

Decimal: 42
Octal: 052 (starts with 0)
Hexadecimal: 0x2A (starts with 0x or 0X)
Binary: 0b1010 (starts with 0b or 0B)

Key Points

Default to int; append L for long (e.g., 9223372036854775807L).
Underscores improve readability (e.g., 123_456_789).
Binary literals are useful for bitmasks.

Example
int dec = 42;         // Decimal
int oct = 052;        // Octal (42 in decimal)
int hex = 0x2A;       // Hexadecimal (42 in decimal)
int bin = 0b1010;     // Binary (10 in decimal)
long big = 123_456_789L;


3.2 Floating-Point Literals
Represent numbers with fractional components in:

Standard notation: 3.14159
Scientific notation: 6.022E23
Hexadecimal notation: 0x12.2P2 (rare, represents 72.5)

Key Points

Default to double; append F for float.
Underscores improve readability.

Example
double d = 3.14159;
float f = 3.14f;
double sci = 6.022E23;
double hexFloat = 0x12.2P2; // 72.5


3.3 Boolean Literals

Only true and false.
Cannot be converted to numeric values.

Example
boolean flag = true;


3.4 Character Literals
Characters are enclosed in single quotes, supporting:

ASCII: 'a', '@'
Escape sequences: '\n', '\''
Octal: '\141' ('a')
Unicode: '\u0061' ('a')

Table 3-1: Character Escape Sequences



Escape Sequence
Description



\ddd
Octal character (ddd)


\uxxxx
Hexadecimal Unicode (xxxx)


\'
Single quote


\"
Double quote


\\
Backslash


\r
Carriage return


\n
New line


\f
Form feed


\t
Tab


\b
Backspace


\s
Space (JDK 15+)


\endofline
Line continuation (JDK 15+, for text blocks)


Example
char a = 'a';
char newline = '\n';
char unicode = '\u0061'; // 'a'


3.5 String Literals
Strings are String objects enclosed in double quotes (e.g., "Hello").
Key Points

Support escape sequences.
Must be on one line (text blocks in JDK 15+ allow multi-line).

Example
String s1 = "Hello World";
String s2 = "two\nlines";
String s3 = "\"This is in quotes\"";

Exercise:Declare variables for each literal type with underscores and escape sequences. Print their values.

4. Variables
A variable is the basic storage unit, defined by an identifier, type, and optional initializer. Variables have scope and lifetime.
4.1 Declaring a Variable
Syntax:
type identifier [= value][, identifier [= value] …];

Examples
int a, b, c;
int d = 3, e, f = 5;
byte z = 22;
double pi = 3.14159;
char x = 'x';


4.2 Dynamic Initialization
Variables can be initialized with expressions, including method calls.
Example: Hypotenuse of a Right Triangle
class DynInit {
    public static void main(String[] args) {
        double a = 3.0, b = 4.0;
        double c = Math.sqrt(a * a + b * b);

        System.out.println("Hypotenuse is " + c);
    }
}

Output:
Hypotenuse is 5.0

Explanation:

c is initialized using the Pythagorean theorem.

Exercise:Compute the volume of a sphere with radius 5 using dynamic initialization.

4.3 Scope and Lifetime of Variables

Scope: Defines visibility, determined by blocks ({}).
Lifetime: Variables are created on scope entry and destroyed on exit.
Nested Scopes: Inner scopes access outer scope variables, not vice versa.

Example: Block Scope
class Scope {
    public static void main(String[] args) {
        int x = 10;

        if (x == 10) {
            int y = 20;
            System.out.println("x and y: " + x + " " + y);
            x = y * 2;
        }
        System.out.println("x is " + x);
    }
}

Output:
x and y: 10 20
x is 40

Example: Variable Lifetime
class LifeTime {
    public static void main(String[] args) {
        for (int x = 0; x < 3; x++) {
            int y = -1;
            System.out.println("y is: " + y);
            y = 100;
            System.out.println("y is now: " + y);
        }
    }
}

Output:
y is: -1
y is now: 100
y is: -1
y is now: 100
y is: -1
y is now: 100

Exercise:Write a program with a for loop that reinitializes a variable each iteration and attempts to access it outside the loop.

5. Type Conversion and Casting
Java supports automatic conversions for compatible types and explicit casting for incompatible types.
5.1 Java’s Automatic Conversions
Occur when:

Types are compatible.
Destination type is larger (widening conversion).

Examples

int to long: No cast needed.
Numeric to char/boolean: Not allowed.


5.2 Casting Incompatible Types
Syntax:
(target-type) value

Example: Demonstrating Casts
class Conversion {
    public static void main(String[] args) {
        byte b;
        int i = 257;
        double d = 323.142;

        b = (byte) i;
        System.out.println("i and b " + i + " " + b);

        i = (int) d;
        System.out.println("d and i " + d + " " + i);

        b = (byte) d;
        System.out.println("d and b " + d + " " + b);
    }
}

Output:
i and b 257 1
d and i 323.142 323
d and b 323.142 67

Explanation:

int to byte: Reduced modulo 256 (257 % 256 = 1).
double to int: Truncated to 323.
double to byte: Truncated and reduced modulo 256 (323 % 256 = 67).

Exercise:Cast 1234.5678 to int and byte. Explain data loss.

5.3 Automatic Type Promotion in Expressions
Rules:

byte, short, char → int
If long operand, expression → long
If float operand, expression → float
If double operand, expression → double

Example: Type Promotion
class Promote {
    public static void main(String[] args) {
        byte b = 42;
        char c = 'a';
        short s = 1024;
        int i = 50000;
        float f = 5.67f;
        double d = .1234;

        double result = (f * b) + (i / c) - (d * s);
        System.out.println((f * b) + " + " + (i / c) + " - " + (d * s));
        System.out.println("result = " + result);
    }
}

Output:
238.14 + 515 - 126.3616
result = 626.7784

Explanation:

f * b: b → float
i / c: c → int
d * s: s → double
Final: (float + int) - double → double

Exercise:Evaluate (byte)10 * (short)20 / (char)'a'. Explain type promotions.

6. Arrays
An array is a collection of same-type variables accessed by index (starting at 0).
6.1 One-Dimensional Arrays
Syntax:
type[] var-name = new type[size];

Example: Days in Each Month
class Array {
    public static void main(String[] args) {
        int[] month_days = new int[12];
        month_days[0] = 31; // January
        month_days[1] = 28; // February
        month_days[2] = 31; // March
        month_days[3] = 30; // April
        month_days[4] = 31; // May
        month_days[5] = 30; // June
        month_days[6] = 31; // July
        month_days[7] = 31; // August
        month_days[8] = 30; // September
        month_days[9] = 31; // October
        month_days[10] = 30; // November
        month_days[11] = 31; // December

        System.out.println("April has " + month_days[3] + " days.");
    }
}

Output:
April has 30 days.

Example: Array Initialization
class AutoArray {
    public static void main(String[] args) {
        int[] month_days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        System.out.println("April has " + month_days[3] + " days.");
    }
}

Output:
April has 30 days.

Example: Average of Numbers
class Average {
    public static void main(String[] args) {
        double[] nums = {10.1, 11.2, 12.3, 13.4, 14.5};
        double result = 0;

        for (int i = 0; i < 5; i++)
            result += nums[i];

        System.out.println("Average is " + result / 5);
    }
}

Output:
Average is 12.3

Exercise:Create an array of 10 integers (1 to 10), compute their sum and average.

6.2 Multidimensional Arrays
Multidimensional arrays are arrays of arrays.
Example: Two-Dimensional Array
class TwoDArray {
    public static void main(String[] args) {
        int[][] twoD = new int[4][5];
        int k = 0;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 5; j++) {
                twoD[i][j] = k;
                k++;
            }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(twoD[i][j] + " ");
            System.out.println();
        }
    }
}

Output:
0 1 2 3 4
5 6 7 8 9
10 11 12 13 14
15 16 17 18 19

Example: Irregular Multidimensional Array
class TwoDAgain {
    public static void main(String[] args) {
        int[][] twoD = new int[4][];
        twoD[0] = new int[1];
        twoD[1] = new int[2];
        twoD[2] = new int[3];
        twoD[3] = new int[4];
        int k = 0;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < i + 1; j++) {
                twoD[i][j] = k;
                k++;
            }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i + 1; j++)
                System.out.print(twoD[i][j] + " ");
            System.out.println();
        }
    }
}

Output:
0
1 2
3 4 5
6 7 8 9

Example: Initialized Two-Dimensional Array
class Matrix {
    public static void main(String[] args) {
        double[][] m = {
            { 0*0, 1*0, 2*0, 3*0 },
            { 0*1, 1*1, 2*1, 3*1 },
            { 0*2, 1*2, 2*2, 3*2 },
            { 0*3, 1*3, 2*3, 3*3 }
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
    }
}

Output:
0.0 0.0 0.0 0.0
0.0 1.0 2.0 3.0
0.0 2.0 4.0 6.0
0.0 3.0 6.0 9.0

Example: Three-Dimensional Array
class ThreeDMatrix {
    public static void main(String[] args) {
        int[][][] threeD = new int[3][4][5];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 5; k++)
                    threeD[i][j][k] = i * j * k;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 5; k++)
                    System.out.print(threeD[i][j][k] + " ");
                System.out.println();
            }
            System.out.println();
        }
    }
}

Output:
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0

0 0 0 0 0
0 1 2 3 4
0 2 4 6 8
0 3 6 9 12

0 0 0 0 0
0 2 4 6 8
0 4 8 12 16
0 6 12 18 24

Exercise:Create a 3×3 array initialized with the sum of row and column indices.

6.3 Alternative Array Declaration Syntax
Syntax:
type var-name[];

Examples
int al[] = new int[3];
char twod1[][] = new char[3][4];


7. Introducing Type Inference with Local Variables
Since JDK 10, the var keyword allows type inference for local variables.
Key Points

Syntax: var identifier = initializer;
Requires initializer.
Restricted to local variables.

Example: Local Variable Type Inference
class VarDemo {
    public static void main(String[] args) {
        var avg = 10.0; // Inferred as double
        System.out.println("Value of avg: " + avg);

        int var = 1; // var as identifier
        System.out.println("Value of var: " + var);

        var k = -var; // var as type
        System.out.println("Value of k: " + k);
    }
}

Output:
Value of avg: 10.0
Value of var: 1
Value of k: -1

Exercise:Use var to declare a double array and compute its sum.

8. A Few Words About Strings
String is an object, not a primitive type.
Examples
String str = "Hello";
System.out.println(str);


9. Exercises
Integer Exercise
class LightYear {
    public static void main(String[] args) {
        long lightspeed = 186000;
        long days = 365;
        long seconds = days * 24 * 60 * 60;
        long distance = lightspeed * seconds;
        System.out.println("In one year, light travels " + distance + " miles.");
    }
}

Floating-Point Exercise
class AreaFloat {
    public static void main(String[] args) {
        float pi = 3.1416f;
        float r = 5.5f;
        float a = pi * r * r;
        System.out.println("Area of circle is " + a);
    }
}

Character Exercise
class CharIncrement {
    public static void main(String[] args) {
        char ch = 'A';
        for (int i = 0; i < 5; i++) ch++;
        System.out.println("Final character: " + ch);
    }
}

Boolean Exercise
class EvenCheck {
    public static void main(String[] args) {
        int num = 42;
        boolean isEven = (num % 2 == 0);
        System.out.println(num + " is even: " + isEven);
    }
}

Literals Exercise
class Literals {
    public static void main(String[] args) {
        int num = 123_456;
        double d = 1_234.56;
        boolean b = true;
        char c = '\n';
        String s = "Hello\nWorld";
        System.out.println("Int: " + num + ", Double: " + d + ", Boolean: " + b + ", Char: " + c + "String: " + s);
    }
}

Dynamic Initialization Exercise
class SphereVolume {
    public static void main(String[] args) {
        double r = 5.0;
        double volume = (4.0 / 3.0) * Math.PI * r * r * r;
        System.out.println("Volume of sphere: " + volume);
    }
}

Scope Exercise
class ScopeLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            int counter = 0;
            System.out.println("Counter: " + counter);
            counter = 100;
            System.out.println("Counter now: " + counter);
        }
    }
}

Type Conversion Exercise
class CastDemo {
    public static void main(String[] args) {
        double d = 1234.5678;
        int i = (int) d;
        byte b = (byte) d;
        System.out.println("Double: " + d + ", Int: " + i + ", Byte: " + b);
    }
}

Type Promotion Exercise
class PromoteExpr {
    public static void main(String[] args) {
        byte b = 10;
        short s = 20;
        char c = 'a';
        int result = (b * s) / c;
        System.out.println("Result: " + result);
    }
}

Array Exercise
class ArraySum {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;
        for (int num : nums) sum += num;
        double avg = (double) sum / nums.length;
        System.out.println("Sum: " + sum + ", Average: " + avg);
    }
}

Multidimensional Array Exercise
class MatrixSum {
    public static void main(String[] args) {
        int[][] m = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                m[i][j] = i + j;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
    }
}

Type Inference Exercise
class VarArray {
    public static void main(String[] args) {
        var nums = new double[]{1.1, 2.2, 3.3, 4.4, 5.5};
        var sum = 0.0;
        for (var num : nums) sum += num;
        System.out.println("Sum: " + sum);
    }
}
