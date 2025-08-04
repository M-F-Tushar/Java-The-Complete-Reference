# Chapter 7: A Closer Look at Methods and Classes

This chapter continues the exploration of methods and classes in Java, building on concepts introduced previously. It covers method overloading, parameter passing, recursion, access control, the `static` keyword, the `String` class, and additional topics like varargs, arrays, nested classes, and local variable type inference. Each section includes explanations, examples, and exercises to reinforce understanding.

## Table of Contents
1. [Overloading Methods](#overloading-methods)
   - [Explanation](#overloading-methods-explanation)
   - [Example](#overloading-methods-example)
   - [Exercise](#overloading-methods-exercise)
2. [Overloading Constructors](#overloading-constructors)
   - [Explanation](#overloading-constructors-explanation)
   - [Example](#overloading-constructors-example)
   - [Exercise](#overloading-constructors-exercise)
3. [Using Objects as Parameters](#using-objects-as-parameters)
   - [Explanation](#using-objects-as-parameters-explanation)
   - [Example](#using-objects-as-parameters-example)
   - [Exercise](#using-objects-as-parameters-exercise)
4. [A Closer Look at Argument Passing](#a-closer-look-at-argument-passing)
   - [Explanation](#argument-passing-explanation)
   - [Example](#argument-passing-example)
   - [Exercise](#argument-passing-exercise)
5. [Returning Objects](#returning-objects)
   - [Explanation](#returning-objects-explanation)
   - [Example](#returning-objects-example)
   - [Exercise](#returning-objects-exercise)
6. [Recursion](#recursion)
   - [Explanation](#recursion-explanation)
   - [Example](#recursion-example)
   - [Exercise](#recursion-exercise)
7. [Introducing Access Control](#introducing-access-control)
   - [Explanation](#access-control-explanation)
   - [Example](#access-control-example)
   - [Exercise](#access-control-exercise)
8. [Understanding static](#understanding-static)
   - [Explanation](#static-explanation)
   - [Example](#static-example)
   - [Exercise](#static-exercise)
9. [Introducing final](#introducing-final)
   - [Explanation](#final-explanation)
   - [Example](#final-example)
   - [Exercise](#final-exercise)
10. [Arrays Revisited](#arrays-revisited)
    - [Explanation](#arrays-revisited-explanation)
    - [Example](#arrays-revisited-example)
    - [Exercise](#arrays-revisited-exercise)
11. [Introducing Nested and Inner Classes](#introducing-nested-and-inner-classes)
    - [Explanation](#nested-and-inner-classes-explanation)
    - [Example](#nested-and-inner-classes-example)
    - [Exercise](#nested-and-inner-classes-exercise)
12. [Exploring the String Class](#exploring-the-string-class)
    - [Explanation](#string-class-explanation)
    - [Example](#string-class-example)
    - [Exercise](#string-class-exercise)
13. [Using Command-Line Arguments](#using-command-line-arguments)
    - [Explanation](#command-line-arguments-explanation)
    - [Example](#command-line-arguments-example)
    - [Exercise](#command-line-arguments-exercise)
14. [Varargs: Variable-Length Arguments](#varargs-variable-length-arguments)
    - [Explanation](#varargs-explanation)
    - [Example](#varargs-example)
    - [Exercise](#varargs-exercise)
15. [Local Variable Type Inference with Reference Types](#local-variable-type-inference)
    - [Explanation](#type-inference-explanation)
    - [Example](#type-inference-example)
    - [Exercise](#type-inference-exercise)

## Overloading Methods
### Overloading Methods Explanation
Method overloading allows multiple methods in the same class to share the same name but differ in their parameter lists (type and/or number of parameters). This supports polymorphism by enabling a single method name to represent different behaviors based on the arguments provided. Java resolves which method to call by matching the argument types and counts, with automatic type conversions applied if no exact match is found. Return types do not affect overload resolution.

### Overloading Methods Example
The following program demonstrates method overloading with different parameter types and counts:

```java
class OverloadDemo {
    void test() {
        System.out.println("No parameters");
    }
    void test(int a) {
        System.out.println("a: " + a);
    }
    void test(int a, int b) {
        System.out.println("a and b: " + a + " " + b);
    }
    double test(double a) {
        System.out.println("double a: " + a);
        return a * a;
    }
}

class Overload {
    public static void main(String[] args) {
        OverloadDemo ob = new OverloadDemo();
        double result;
        ob.test();
        ob.test(10);
        ob.test(10, 20);
        result = ob.test(123.25);
        System.out.println("Result of ob.test(123.25): " + result);
    }
}
```

**Output:**
```
No parameters
a: 10
a and b: 10 20
double a: 123.25
Result of ob.test(123.25): 15190.5625
```

### Overloading Methods Exercise
Create a class `Calculator` with overloaded methods `add` that perform addition for:
- Two integers
- Two doubles
- Three integers
Test the methods in a main program and display the results.

## Overloading Constructors
### Overloading Constructors Explanation
Constructors can be overloaded to provide multiple ways to initialize objects. This flexibility allows objects to be created with different sets of parameters, accommodating various use cases, such as default values or specific initializations.

### Overloading Constructors Example
This program shows a `Box` class with overloaded constructors:

```java
class Box {
    double width, height, depth;
    Box(double w, double h, double d) {
        width = w; height = h; depth = d;
    }
    Box() {
        width = height = depth = -1;
    }
    Box(double len) {
        width = height = depth = len;
    }
    double volume() {
        return width * height * depth;
    }
}

class OverloadCons {
    public static void main(String[] args) {
        Box mybox1 = new Box(10, 20, 15);
        Box mybox2 = new Box();
        Box mycube = new Box(7);
        System.out.println("Volume of mybox1 is " + mybox1.volume());
        System.out.println("Volume of mybox2 is " + mybox2.volume());
        System.out.println("Volume of mycube is " + mycube.volume());
    }
}
```

**Output:**
```
Volume of mybox1 is 3000.0
Volume of mybox2 is -1.0
Volume of mycube is 343.0
```

### Overloading Constructors Exercise
Modify the `Box` class to include a constructor that takes a single `Box` object to create a copy. Test this constructor by creating a clone of an existing box and verifying their volumes are equal.

## Using Objects as Parameters
### Using Objects as Parameters Explanation
Methods can accept objects as parameters, allowing operations on object data. This is common in constructors for copying objects or in methods that compare or manipulate objects.

### Using Objects as Parameters Example
This program demonstrates passing objects to methods:

```java
class Test {
    int a, b;
    Test(int i, int j) {
        a = i; b = j;
    }
    boolean equalTo(Test o) {
        return o.a == a && o.b == b;
    }
}

class PassOb {
    public static void main(String[] args) {
        Test ob1 = new Test(100, 22);
        Test ob2 = new Test(100, 22);
        Test ob3 = new Test(-1, -1);
        System.out.println("ob1 == ob2: " + ob1.equalTo(ob2));
        System.out.println("ob1 == ob3: " + ob1.equalTo(ob3));
    }
}
```

**Output:**
```
ob1 == ob2: true
ob1 == ob3: false
```

### Using Objects as Parameters Exercise
Create a `Point` class with x and y coordinates. Add a method `distance` that takes another `Point` object and calculates the Euclidean distance between them. Test the method in a main program.

## A Closer Look at Argument Passing
### Argument Passing Explanation
Java uses call-by-value for all arguments. For primitive types, a copy of the value is passed, so changes to the parameter do not affect the original. For objects, a copy of the reference is passed, meaning changes to the object’s fields affect the original object.

### Argument Passing Example
This program illustrates the difference between passing primitive types and objects:

```java
class Test {
    void meth(int i, int j) {
        i *= 2; j /= 2;
    }
    void meth(Test o) {
        o.a *= 2; o.b /= 2;
    }
}

class CallByValue {
    public static void main(String[] args) {
        Test ob = new Test();
        int a = 15, b = 20;
        System.out.println("a and b before call: " + a + " " + b);
        ob.meth(a, b);
        System.out.println("a and b after call: " + a + " " + b);
        Test obj = new Test(15, 20);
        System.out.println("obj.a and obj.b before call: " + obj.a + " " + obj.b);
        ob.meth(obj);
        System.out.println("obj.a and obj.b after call: " + obj.a + " " + obj.b);
    }
}
```

**Output:**
```
a and b before call: 15 20
a and b after call: 15 20
obj.a and obj.b before call: 15 20
obj.a and obj.b after call: 30 10
```

### Argument Passing Exercise
Create a class `Counter` with an integer field `count`. Write two methods: one that modifies a primitive `int` parameter and another that modifies a `Counter` object’s `count`. Demonstrate that the primitive parameter remains unchanged while the object’s field is modified.

## Returning Objects
### Returning Objects Explanation
Methods can return objects, allowing the creation of new objects based on computations or transformations of existing ones. Objects returned are dynamically allocated and persist as long as they are referenced.

### Returning Objects Example
This program shows a method returning an object:

```java
class Test {
    int a;
    Test(int i) { a = i; }
    Test incrByTen() {
        Test temp = new Test(a + 10);
        return temp;
    }
}

class RetOb {
    public static void main(String[] args) {
        Test ob1 = new Test(2);
        Test ob2 = ob1.incrByTen();
        System.out.println("ob1.a: " + ob1.a);
        System.out.println("ob2.a: " + ob2.a);
        ob2 = ob2.incrByTen();
        System.out.println("ob2.a after second increase: " + ob2.a);
    }
}
```

**Output:**
```
ob1.a: 2
ob2.a: 12
ob2.a after second increase: 22
```

### Returning Objects Exercise
Modify the `Box` class to include a method `scale` that returns a new `Box` object with dimensions scaled by a given factor. Test the method by creating a scaled box and comparing its volume.

## Recursion
### Recursion Explanation
Recursion allows a method to call itself, useful for problems that can be defined in terms of smaller instances of the same problem. A base case is essential to prevent infinite recursion. Recursive methods create new local variable copies on the stack for each call.

### Recursion Example
This program computes factorials using recursion:

```java
class Factorial {
    int fact(int n) {
        if (n == 1) return 1;
        return fact(n - 1) * n;
    }
}

class Recursion {
    public static void main(String[] args) {
        Factorial f = new Factorial();
        System.out.println("Factorial of 3 is " + f.fact(3));
        System.out.println("Factorial of 4 is " + f.fact(4));
        System.out.println("Factorial of 5 is " + f.fact(5));
    }
}
```

**Output:**
```
Factorial of 3 is 6
Factorial of 4 is 24
Factorial of 5 is 120
```

### Recursion Exercise
Write a recursive method to calculate the Fibonacci sequence for a given n (e.g., fib(5) = 5). Include a base case and test the method for inputs 3, 5, and 7.

## Introducing Access Control
### Access Control Explanation
Access control, part of encapsulation, restricts access to class members using modifiers like `public`, `private`, and default (package-private). This prevents misuse and ensures data integrity by allowing access only through designated methods.

### Access Control Example
This program demonstrates access modifiers:

```java
class Test {
    int a; // default access
    public int b; // public access
    private int c; // private access
    void setc(int i) { c = i; }
    int getc() { return c; }
}

class AccessTest {
    public static void main(String[] args) {
        Test ob = new Test();
        ob.a = 10;
        ob.b = 20;
        ob.setc(100);
        System.out.println("a, b, and c: " + ob.a + " " + ob.b + " " + ob.getc());
    }
}
```

**Output:**
```
a, b, and c: 10 20 100
```

### Access Control Exercise
Create a `BankAccount` class with a private `balance` field. Provide public methods `deposit` and `withdraw` to modify the balance, ensuring withdrawals don’t result in a negative balance. Test the class with various transactions.

## Understanding static
### static Explanation
The `static` keyword allows class members to exist independently of any object instance. Static variables are shared across all instances, and static methods can be called without creating an object, but they can only access other static members directly.

### static Example
This program demonstrates static members:

```java
class UseStatic {
    static int a = 3;
    static int b;
    static void meth(int x) {
        System.out.println("x = " + x);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
    static { b = a * 4; }
    public static void main(String[] args) {
        meth(42);
    }
}
```

**Output:**
```
x = 42
a = 3
b = 12
```

### static Exercise
Create a `Counter` class with a static variable to track the number of instances created. Include a static method to display the count. Test by creating multiple instances and calling the method.

## Introducing final
### final Explanation
The `final` keyword makes a field a constant, preventing modification after initialization. It can also apply to method parameters and local variables to prevent reassignment.

### final Example
This example shows `final` fields used as constants:

```java
class FileOperations {
    final int FILE_NEW = 1;
    final int FILE_OPEN = 2;
    final int FILE_SAVE = 3;
    final int FILE_SAVEAS = 4;
    final int FILE_QUIT = 5;
}
```

### final Exercise
Create a `Circle` class with a `final` field `PI` set to 3.14159. Include a method to calculate the area given a radius. Test the method with different radii.

## Arrays Revisited
### Arrays Revisited Explanation
Arrays in Java are objects with a `length` field indicating their size. This allows dynamic handling of array sizes, useful in applications like stacks.

### Arrays Revisited Example
This program demonstrates the `length` field:

```java
class Length {
    public static void main(String[] args) {
        int[] a1 = new int[10];
        int[] a2 = {3, 5, 7, 1, 8, 99, 44, -10};
        int[] a3 = {4, 3, 2, 1};
        System.out.println("length of a1 is " + a1.length);
        System.out.println("length of a2 is " + a2.length);
        System.out.println("length of a3 is " + a3.length);
    }
}
```

**Output:**
```
length of a1 is 10
length of a2 is 8
length of a3 is 4
```

### Arrays Revisited Exercise
Modify the `Stack` class to use the `length` field to create a stack of any size. Test with stacks of different sizes and verify push/pop operations.

## Introducing Nested and Inner Classes
### Nested and Inner Classes Explanation
Nested classes are defined within another class, with static nested classes requiring an object to access non-static members, while inner (non-static) classes can directly access all members of the enclosing class.

### Nested and Inner Classes Example
This program shows an inner class:

```java
class Outer {
    int outer_x = 100;
    void test() {
        Inner inner = new Inner();
        inner.display();
    }
    class Inner {
        void display() {
            System.out.println("display: outer_x = " + outer_x);
        }
    }
}

class InnerClassDemo {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.test();
    }
}
```

**Output:**
```
display: outer_x = 100
```

### Nested and Inner Classes Exercise
Create an `Outer` class with a private `counter` field and an inner class `Inner` that increments and displays the counter. Test by creating multiple `Inner` instances and verifying the counter increments.

## Exploring the String Class
### String Class Explanation
The `String` class is immutable, meaning its contents cannot be changed after creation. It supports operations like concatenation (`+`), equality checking (`equals`), and methods like `length()` and `charAt()`.

### String Class Example
This program demonstrates `String` methods:

```java
class StringDemo2 {
    public static void main(String[] args) {
        String strOb1 = "First String";
        String strOb2 = "Second String";
        String strOb3 = strOb1;
        System.out.println("Length of strOb1: " + strOb1.length());
        System.out.println("Char at index 3 in strOb1: " + strOb1.charAt(3));
        System.out.println("strOb1 == strOb2: " + strOb1.equals(strOb2));
        System.out.println("strOb1 == strOb3: " + strOb1.equals(strOb3));
    }
}
```

**Output:**
```
Length of strOb1: 12
Char at index 3 in strOb1: s
strOb1 != strOb2
strOb1 == strOb3
```

### String Class Exercise
Create a program that takes a string array, reverses each string, and prints the results. Use `StringBuilder` for reversal to avoid creating new `String` objects unnecessarily.

## Using Command-Line Arguments
### Command-Line Arguments Explanation
Command-line arguments are passed as a `String` array to the `main` method, allowing external input to a program. They are accessed via the `args` parameter.

### Command-Line Arguments Example
This program displays command-line arguments:

```java
class CommandLine {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++)
            System.out.println("args[" + i + "]: " + args[i]);
    }
}
```

**Run command:**
```bash
java CommandLine this is a test 100 -1
```

**Output:**
```
args[0]: this
args[1]: is
args[2]: a
args[3]: test
args[4]: 100
args[5]: -1
```

### Command-Line Arguments Exercise
Write a program that accepts command-line arguments and calculates their sum, assuming they are integers. Handle non-integer inputs gracefully.

## Varargs: Variable-Length Arguments
### Varargs Explanation
Varargs allow methods to accept a variable number of arguments, specified using `...`. The arguments are treated as an array within the method, simplifying calls with varying argument counts.

### Varargs Example
This program demonstrates varargs:

```java
class VarArgs {
    static void vaTest(int ... v) {
        System.out.print("Number of args: " + v.length + " Contents: ");
        for (int x : v)
            System.out.print(x + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        vaTest(10);
        vaTest(1, 2, 3);
        vaTest();
    }
}
```

**Output:**
```
Number of args: 1 Contents: 10
Number of args: 3 Contents: 1 2 3
Number of args: 0 Contents:
```

### Varargs Exercise
Create a method `concatStrings` that uses varargs to concatenate an arbitrary number of strings with a specified delimiter. Test with different numbers of strings and delimiters.

## Local Variable Type Inference with Reference Types
### Type Inference Explanation
Since JDK 10, Java supports local variable type inference using `var`, where the type is inferred from the initializer. This streamlines declarations, especially for complex reference types, but must be used carefully to maintain code readability.

### Type Inference Example
This program uses `var` with a user-defined class:

```java
class MyClass {
    private int i;
    MyClass(int k) { i = k; }
    int geti() { return i; }
    void seti(int k) { if (k >= 0) i = k; }
}

class RefVarDemo {
    public static void main(String[] args) {
        var mc = new MyClass(10);
        System.out.println("Value of i in mc is " + mc.geti());
        mc.seti(19);
        System.out.println("Value of i in mc is now " + mc.geti());
    }
}
```

**Output:**
```
Value of i in mc is 10
Value of i in mc is now 19
```

### Type Inference Exercise
Rewrite the `Box` class constructor example using `var` for local variables where appropriate. Test the program to ensure identical behavior.
