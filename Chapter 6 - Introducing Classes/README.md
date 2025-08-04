# Introduction to Java Classes

This document provides an in-depth exploration of Java classes, the cornerstone of object-oriented programming in Java. It covers the fundamental concepts, including class structure, object creation, methods, constructors, the `this` keyword, and garbage collection, with examples and exercises to reinforce understanding.

## Table of Contents
1. [Class Fundamentals](#class-fundamentals)
   - [What is a Class?](#what-is-a-class)
   - [The General Form of a Class](#the-general-form-of-a-class)
2. [Creating and Using Objects](#creating-and-using-objects)
   - [Declaring Objects](#declaring-objects)
   - [A Closer Look at `new`](#a-closer-look-at-new)
   - [Assigning Object Reference Variables](#assigning-object-reference-variables)
3. [Introducing Methods](#introducing-methods)
   - [Adding a Method to a Class](#adding-a-method-to-a-class)
   - [Returning a Value](#returning-a-value)
   - [Adding a Method with Parameters](#adding-a-method-with-parameters)
4. [Constructors](#constructors)
   - [Simple Constructors](#simple-constructors)
   - [Parameterized Constructors](#parameterized-constructors)
5. [The `this` Keyword](#the-this-keyword)
   - [Instance Variable Hiding](#instance-variable-hiding)
6. [Garbage Collection](#garbage-collection)
7. [A Practical Example: The Stack Class](#a-practical-example-the-stack-class)
8. [Examples](#examples)
   - [Example 1: Basic Box Class](#example-1-basic-box-class)
   - [Example 2: Box Class with Method](#example-2-box-class-with-method)
   - [Example 3: Box Class with Parameterized Constructor](#example-3-box-class-with-parameterized-constructor)
   - [Example 4: Stack Class Implementation](#example-4-stack-class-implementation)
9. [Exercises](#exercises)
   - [Exercise 1: Rectangle Class](#exercise-1-rectangle-class)
   - [Exercise 2: Enhanced Stack Class](#exercise-2-enhanced-stack-class)

## Class Fundamentals

### What is a Class?
A class in Java is a blueprint for creating objects, defining their properties (instance variables) and behaviors (methods). It encapsulates data and the code that operates on that data, forming the basis of object-oriented programming. A class creates a new data type, and objects are instances of that type.

### The General Form of a Class
A class is declared using the `class` keyword. The general form includes instance variables and methods, collectively called members of the class:

```java
class ClassName {
    type instanceVariable1;
    type instanceVariable2;
    // ...
    type methodName1(parameter-list) {
        // body of method
    }
    // ...
}
```

- **Instance Variables**: Variables defined within a class, where each object has its own copy.
- **Methods**: Define how the class's data can be used or manipulated.

## Creating and Using Objects

### Declaring Objects
Creating an object involves two steps:
1. Declare a variable of the class type.
2. Use the `new` operator to allocate memory and assign the object to the variable.

For example:
```java
Box myBox = new Box(); // Declares and instantiates a Box object
```

### A Closer Look at `new`
The `new` operator dynamically allocates memory for an object at runtime and returns a reference to it. The class name followed by parentheses calls the class's constructor. If no constructor is defined, Java provides a default constructor that initializes instance variables to their default values (e.g., `0` for numbers, `null` for references).

### Assigning Object Reference Variables
When assigning one object reference to another, you copy the reference, not the object itself. Both variables then point to the same object in memory.

```java
Box b1 = new Box();
Box b2 = b1; // b1 and b2 refer to the same object
```

**Key Point**: Changes to the object through one reference affect the object accessed through the other reference.

## Introducing Methods

### Adding a Method to a Class
Methods define a class's interface, allowing controlled access to instance variables. For example, a `volume()` method can be added to the `Box` class to compute and display the volume:

```java
void volume() {
    System.out.println("Volume is " + (width * height * depth));
}
```

### Returning a Value
Methods can return values to the caller. For instance, modifying the `volume()` method to return the computed volume:

```java
double volume() {
    return width * height * depth;
}
```

### Adding a Method with Parameters
Parameterized methods generalize functionality. For example, a `setDim()` method sets the dimensions of a `Box`:

```java
void setDim(double w, double h, double d) {
    width = w;
    height = h;
    depth = d;
}
```

**Parameter vs. Argument**:
- **Parameter**: A variable defined in the method signature (e.g., `w`, `h`, `d`).
- **Argument**: The value passed to the method when called (e.g., `10`, `20`, `15`).

## Constructors

### Simple Constructors
A constructor is a special method with the same name as the class, called automatically when an object is created. It has no return type, not even `void`. A simple constructor for `Box` might set default dimensions:

```java
Box() {
    width = 10;
    height = 10;
    depth = 10;
}
```

### Parameterized Constructors
Parameterized constructors allow initialization with specific values:

```java
Box(double w, double h, double d) {
    width = w;
    height = h;
    depth = d;
}
```

This allows creating `Box` objects with different dimensions, e.g., `new Box(10, 20, 15)`.

## The `this` Keyword

### Instance Variable Hiding
The `this` keyword refers to the current object, useful for resolving naming conflicts between instance variables and local variables or parameters. For example:

```java
Box(double width, double height, double depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
}
```

Here, `this.width` refers to the instance variable, while `width` refers to the parameter.

## Garbage Collection
Java automatically manages memory through garbage collection. When no references to an object exist, it becomes eligible for garbage collection, and its memory is reclaimed. This process occurs sporadically and does not require manual deallocation, unlike some other languages.

## A Practical Example: The Stack Class
A `Stack` class encapsulates a first-in, last-out data structure using `push()` and `pop()` methods. The implementation hides the underlying array, providing a clean interface:

```java
class Stack {
    int[] stck = new int[10];
    int tos;

    Stack() {
        tos = -1;
    }

    void push(int item) {
        if (tos == 9)
            System.out.println("Stack is full.");
        else
            stck[++tos] = item;
    }

    int pop() {
        if (tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        } else
            return stck[tos--];
    }
}
```

This demonstrates encapsulation, as the stack's internal array is manipulated only through `push()` and `pop()`.

## Examples

### Example 1: Basic Box Class
This example demonstrates a simple `Box` class with instance variables and a program to compute its volume.

```java
class Box {
    double width;
    double height;
    double depth;
}

class BoxDemo {
    public static void main(String[] args) {
        Box mybox = new Box();
        double vol;

        mybox.width = 10;
        mybox.height = 20;
        mybox.depth = 15;

        vol = mybox.width * mybox.height * mybox.depth;
        System.out.println("Volume is " + vol);
    }
}
```

**Explanation**: The `Box` class defines three instance variables. The `BoxDemo` class creates a `Box` object, assigns values to its variables, and computes the volume (output: `3000.0`).

### Example 2: Box Class with Method
This example adds a `volume()` method to the `Box` class to compute and return the volume.

```java
class Box {
    double width;
    double height;
    double depth;

    double volume() {
        return width * height * depth;
    }
}

class BoxDemo4 {
    public static void main(String[] args) {
        Box mybox1 = new Box();
        Box mybox2 = new Box();

        mybox1.width = 10;
        mybox1.height = 20;
        mybox1.depth = 15;

        mybox2.width = 3;
        mybox2.height = 6;
        mybox2.depth = 9;

        System.out.println("Volume is " + mybox1.volume());
        System.out.println("Volume is " + mybox2.volume());
    }
}
```

**Explanation**: The `volume()` method encapsulates the volume calculation. The program creates two `Box` objects, sets their dimensions, and uses `volume()` to compute their volumes (output: `3000.0` and `162.0`).

### Example 3: Box Class with Parameterized Constructor
This example uses a parameterized constructor to initialize `Box` objects.

```java
class Box {
    double width;
    double height;
    double depth;

    Box(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    double volume() {
        return width * height * depth;
    }
}

class BoxDemo7 {
    public static void main(String[] args) {
        Box mybox1 = new Box(10, 20, 15);
        Box mybox2 = new Box(3, 6, 9);

        System.out.println("Volume is " + mybox1.volume());
        System.out.println("Volume is " + mybox2.volume());
    }
}
```

**Explanation**: The constructor initializes the `Box` dimensions at creation, simplifying object setup. The output is the same as Example 2: `3000.0` and `162.0`.

### Example 4: Stack Class Implementation
This example demonstrates the `Stack` class and its usage.

```java
class Stack {
    int[] stck = new int[10];
    int tos;

    Stack() {
        tos = -1;
    }

    void push(int item) {
        if (tos == 9)
            System.out.println("Stack is full.");
        else
            stck[++tos] = item;
    }

    int pop() {
        if (tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        } else
            return stck[tos--];
    }
}

class TestStack {
    public static void main(String[] args) {
        Stack mystack1 = new Stack();
        Stack mystack2 = new Stack();

        for (int i = 0; i < 10; i++) mystack1.push(i);
        for (int i = 10; i < 20; i++) mystack2.push(i);

        System.out.println("Stack in mystack1:");
        for (int i = 0; i < 10; i++)
            System.out.println(mystack1.pop());

        System.out.println("Stack in mystack2:");
        for (int i = 0; i < 10; i++)
            System.out.println(mystack2.pop());
    }
}
```

**Explanation**: The `Stack` class implements a stack with `push()` and `pop()` methods. The `TestStack` class pushes numbers onto two stacks and pops them off, demonstrating first-in, last-out behavior. Output shows numbers 9 to 0 for `mystack1` and 19 to 10 for `mystack2`.

## Exercises

### Exercise 1: Rectangle Class
Create a `Rectangle` class with instance variables `length` and `width`. Include a constructor to initialize these variables, a method to compute the area, and a method to compute the perimeter. Write a program to test the class.

**Solution**:
```java
class Rectangle {
    double length;
    double width;

    Rectangle(double l, double w) {
        length = l;
        width = w;
    }

    double area() {
        return length * width;
    }

    double perimeter() {
        return 2 * (length + width);
    }
}

class RectangleDemo {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(5, 3);
        System.out.println("Area: " + rect.area());
        System.out.println("Perimeter: " + rect.perimeter());
    }
}
```

**Explanation**: The `Rectangle` class has a constructor to set `length` and `width`. The `area()` method returns `length * width`, and the `perimeter()` method returns `2 * (length + width)`. The test program creates a `Rectangle` with length 5 and width 3, outputting `Area: 15.0` and `Perimeter: 16.0`.

### Exercise 2: Enhanced Stack Class
Modify the `Stack` class to include a method `isEmpty()` that returns `true` if the stack is empty. Test the modified class.

**Solution**:
```java
class Stack {
    int[] stck = new int[10];
    int tos;

    Stack() {
        tos = -1;
    }

    void push(int item) {
        if (tos == 9)
            System.out.println("Stack is full.");
        else
            stck[++tos] = item;
    }

    int pop() {
        if (tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        } else
            return stck[tos--];
    }

    boolean isEmpty() {
        return tos < 0;
    }
}

class TestStack2 {
    public static void main(String[] args) {
        Stack mystack = new Stack();
        System.out.println("Is stack empty? " + mystack.isEmpty());
        mystack.push(5);
        System.out.println("Is stack empty? " + mystack.isEmpty());
        mystack.pop();
        System.out.println("Is stack empty? " + mystack.isEmpty());
    }
}
```

**Explanation**: The `isEmpty()` method checks if `tos < 0`, indicating an empty stack. The test program creates a `Stack`, checks if it's empty (true), pushes an item (false), and pops it (true again). Output: `true`, `false`, `true`.
