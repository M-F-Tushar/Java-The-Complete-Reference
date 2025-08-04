# Java Inheritance Guide

This guide provides an in-depth exploration of **inheritance** in Java, a cornerstone of object-oriented programming (OOP). Inheritance enables the creation of hierarchical classifications, allowing a subclass to inherit attributes and behaviors from a superclass while adding its own unique elements. This document includes detailed explanations, practical examples, and exercises to solidify your understanding, making it ideal for learners and developers looking to master Java inheritance.

## Table of Contents

1. [Introduction to Inheritance](#introduction-to-inheritance)
2. [Inheritance Basics](#inheritance-basics)
   - [Example: Simple Inheritance](#example-simple-inheritance)
3. [Member Access and Inheritance](#member-access-and-inheritance)
   - [Example: Private Member Access Issue](#example-private-member-access-issue)
4. [A Practical Example: Extending the Box Class](#a-practical-example-extending-the-box-class)
   - [Example: Box and BoxWeight](#example-box-and-boxweight)
   - [Example: Adding Color to Box](#example-adding-color-to-box)
5. [Superclass Reference to Subclass Object](#superclass-reference-to-subclass-object)
   - [Example: Reference Demo](#example-reference-demo)
6. [Using the `super` Keyword](#using-the-super-keyword)
   - [Calling Superclass Constructors](#calling-superclass-constructors)
   - [Accessing Superclass Members](#accessing-superclass-members)
   - [Example: Improved BoxWeight with `super`](#example-improved-boxweight-with-super)
   - [Example: Overcoming Name Hiding](#example-overcoming-name-hiding)
7. [Multilevel Hierarchy](#multilevel-hierarchy)
   - [Example: Shipment Class](#example-shipment-class)
8. [Constructor Execution Order](#constructor-execution-order)
   - [Example: Constructor Order Demonstration](#example-constructor-order-demonstration)
9. [Method Overriding](#method-overriding)
   - [Example: Basic Method Overriding](#example-basic-method-overriding)
   - [Example: Using `super` in Overriding](#example-using-super-in-overriding)
   - [Example: Overloading vs. Overriding](#example-overloading-vs-overriding)
10. [Dynamic Method Dispatch](#dynamic-method-dispatch)
    - [Example: Dynamic Method Dispatch](#example-dynamic-method-dispatch)
11. [Applying Method Overriding](#applying-method-overriding)
    - [Example: Figure, Rectangle, and Triangle](#example-figure-rectangle-and-triangle)
12. [Abstract Classes](#abstract-classes)
    - [Example: Simple Abstract Class](#example-simple-abstract-class)
    - [Example: Abstract Figure Class](#example-abstract-figure-class)
13. [Using `final` with Inheritance](#using-final-with-inheritance)
    - [Preventing Method Overriding](#preventing-method-overriding)
    - [Preventing Class Inheritance](#preventing-class-inheritance)
14. [Local Variable Type Inference and Inheritance](#local-variable-type-inference-and-inheritance)
    - [Example: Type Inference with Inheritance](#example-type-inference-with-inheritance)
15. [The `Object` Class](#the-object-class)
16. [Exercise: Vehicle Hierarchy](#exercise-vehicle-hierarchy)
    - [Solution](#solution)

## Introduction to Inheritance

Inheritance is a fundamental OOP concept in Java that allows a class (subclass) to inherit attributes and methods from another class (superclass). This promotes code reuse and establishes a hierarchical relationship where subclasses can extend or specialize the functionality of their superclasses. A superclass defines common traits, while subclasses add unique features.

- **Superclass**: The parent class that is inherited from.
- **Subclass**: The child class that inherits from the superclass, using the `extends` keyword.
- **Key Benefit**: Enables code reuse and modularity by allowing subclasses to inherit and build upon existing code.

## Inheritance Basics

To create a subclass, use the `extends` keyword in the class declaration. The general syntax is:

```java
class SubclassName extends SuperclassName {
    // Subclass-specific fields and methods
}
```

A subclass inherits all public and protected members of its superclass and can add its own fields and methods. Java supports single inheritance, meaning a subclass can only extend one superclass.

### Example: Simple Inheritance

The following example demonstrates a superclass `A` and a subclass `B` that extends it, showcasing how the subclass inherits and uses the superclass's members.

```java
// Create a superclass
class A {
    int i, j;
    void showij() {
        System.out.println("i and j: " + i + " " + j);
    }
}

// Create a subclass by extending class A
class B extends A {
    int k;
    void showk() {
        System.out.println("k: " + k);
    }
    void sum() {
        System.out.println("i + j + k: " + (i + j + k));
    }
}

class SimpleInheritance {
    public static void main(String[] args) {
        A superOb = new A();
        B subOb = new B();

        // Use superclass independently
        superOb.i = 10;
        superOb.j = 20;
        System.out.println("Contents of superOb: ");
        superOb.showij();
        System.out.println();

        // Subclass accesses superclass members and adds its own
        subOb.i = 7;
        subOb.j = 8;
        subOb.k = 9;
        System.out.println("Contents of subOb: ");
        subOb.showij();
        subOb.showk();
        System.out.println();
        System.out.println("Sum of i, j and k in subOb:");
        subOb.sum();
    }
}
```

**Output**:
```
Contents of superOb:
i and j: 10 20

Contents of subOb:
i and j: 7 8
k: 9

Sum of i, j and k in subOb:
i+j+k: 24
```

**Explanation**:
- Class `A` defines fields `i`, `j`, and method `showij()`.
- Class `B` extends `A`, inheriting `i`, `j`, and `showij()`, and adds field `k` and methods `showk()` and `sum()`.
- The `sum()` method in `B` directly accesses `i` and `j` from `A`, demonstrating inheritance.
- The `main` method shows that both the superclass and subclass can be used independently.

## Member Access and Inheritance

A subclass cannot access private members of its superclass, enforcing encapsulation. Only public or protected members are inherited and accessible.

### Example: Private Member Access Issue

This example illustrates the error that occurs when a subclass attempts to access a private member of its superclass.

```java
// Create a superclass
class A {
    int i; // default access
    private int j; // private to A
    void setij(int x, int y) {
        i = x;
        j = y;
    }
}

// A's j is not accessible here
class B extends A {
    int total;
    void sum() {
        total = i + j; // ERROR: j is not accessible
    }
}

class Access {
    public static void main(String[] args) {
        B subOb = new B();
        subOb.setij(10, 12);
        subOb.sum();
        System.out.println("Total is " + subOb.total);
    }
}
```

**Explanation**:
- The field `j` in class `A` is private, so it cannot be accessed in `B`.
- Attempting to access `j` in `B`'s `sum()` method results in a compile-time error.
- Use public or protected access modifiers to allow subclass access, or provide public/protected methods like `setij()`.

## A Practical Example: Extending the Box Class

Inheritance is powerful for extending existing classes. The `Box` class can be extended to include additional attributes like weight or color.

### Example: Box and BoxWeight

This example extends the `Box` class to create `BoxWeight`, adding a `weight` field.

```java
class Box {
    double width;
    double height;
    double depth;

    // Construct clone of an object
    Box(Box ob) {
        width = ob.width;
        height = ob.height;
        depth = ob.depth;
    }

    // Constructor for specified dimensions
    Box(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    // Default constructor
    Box() {
        width = -1;
        height = -1;
        depth = -1;
    }

    // Constructor for cube
    Box(double len) {
        width = height = depth = len;
    }

    // Compute and return volume
    double volume() {
        return width * height * depth;
    }
}

class BoxWeight extends Box {
    double weight;

    BoxWeight(double w, double h, double d, double m) {
        width = w;
        height = h;
        depth = d;
        weight = m;
    }
}

class DemoBoxWeight {
    public static void main(String[] args) {
        BoxWeight mybox1 = new BoxWeight(10, 20, 15, 34.3);
        BoxWeight mybox2 = new BoxWeight(2, 3, 4, 0.076);
        double vol;

        vol = mybox1.volume();
        System.out.println("Volume of mybox1 is " + vol);
        System.out.println("Weight of mybox1 is " + mybox1.weight);
        System.out.println();

        vol = mybox2.volume();
        System.out.println("Volume of mybox2 is " + vol);
        System.out.println("Weight of mybox2 is " + mybox2.weight);
    }
}
```

**Output**:
```
Volume of mybox1 is 3000.0
Weight of mybox1 is 34.3

Volume of mybox2 is 24.0
Weight of mybox2 is 0.076
```

**Explanation**:
- `Box` defines `width`, `height`, `depth`, and methods for volume calculation.
- `BoxWeight` extends `Box`, inheriting its fields and methods, and adds `weight`.
- The subclass constructor initializes inherited fields directly, which is less robust if `Box` fields become private.

### Example: Adding Color to Box

This example extends `Box` to include a `color` attribute.

```java
class ColorBox extends Box {
    int color;

    ColorBox(double w, double h, double d, int c) {
        width = w;
        height = h;
        depth = d;
        color = c;
    }
}
```

**Explanation**:
- `ColorBox` inherits all features of `Box` and adds a `color` field.
- This demonstrates how multiple subclasses can extend a single superclass, each adding unique attributes.

## Superclass Reference to Subclass Object

A superclass reference variable can refer to a subclass object, but it can only access members defined in the superclass.

### Example: Reference Demo

```java
class RefDemo {
    public static void main(String[] args) {
        BoxWeight weightbox = new BoxWeight(3, 5, 7, 8.37);
        Box plainbox = new Box();
        double vol;

        vol = weightbox.volume();
        System.out.println("Volume of weightbox is " + vol);
        System.out.println("Weight of weightbox is " + weightbox.weight);
        System.out.println();

        // Assign BoxWeight reference to Box reference
        plainbox = weightbox;
        vol = plainbox.volume(); // OK, volume() defined in Box
        System.out.println("Volume of plainbox is " + vol);
        // System.out.println("Weight of plainbox is " + plainbox.weight); // Error: weight not defined in Box
    }
}
```

**Output**:
```
Volume of weightbox is 105.0
Weight of weightbox is 8.37

Volume of plainbox is 105.0
```

**Explanation**:
- A `Box` reference (`plainbox`) can point to a `BoxWeight` object because `BoxWeight` is a subclass of `Box`.
- Only `Box` members (e.g., `volume()`) are accessible through `plainbox`, not `weight`.

## Using the `super` Keyword

The `super` keyword allows a subclass to call its superclass's constructors or access its members, enhancing encapsulation and code efficiency.

### Calling Superclass Constructors

Use `super(arg-list)` to call a superclass constructor. It must be the first statement in the subclass constructor.

### Accessing Superclass Members

Use `super.member` to access a superclass member hidden by a subclass member.

### Example: Improved BoxWeight with `super`

This example improves `BoxWeight` by using `super` to call `Box` constructors, allowing `Box` fields to be private.

```java
class Box {
    private double width;
    private double height;
    private double depth;

    Box(Box ob) {
        width = ob.width;
        height = ob.height;
        depth = ob.depth;
    }

    Box(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    Box() {
        width = -1;
        height = -1;
        depth = -1;
    }

    Box(double len) {
        width = height = depth = len;
    }

    double volume() {
        return width * height * depth;
    }
}

class BoxWeight extends Box {
    double weight;

    BoxWeight(BoxWeight ob) {
        super(ob);
        weight = ob.weight;
    }

    BoxWeight(double w, double h, double d, double m) {
        super(w, h, d);
        weight = m;
    }

    BoxWeight() {
        super();
        weight = -1;
    }

    BoxWeight(double len, double m) {
        super(len);
        weight = m;
    }
}

class DemoSuper {
    public static void main(String[] args) {
        BoxWeight mybox1 = new BoxWeight(10, 20, 15, 34.3);
        BoxWeight mybox2 = new BoxWeight(2, 3, 4, 0.076);
        BoxWeight mybox3 = new BoxWeight();
        BoxWeight mycube = new BoxWeight(3, 2);
        BoxWeight myclone = new BoxWeight(mybox1);
        double vol;

        vol = mybox1.volume();
        System.out.println("Volume of mybox1 is " + vol);
        System.out.println("Weight of mybox1 is " + mybox1.weight);
        System.out.println();

        vol = mybox2.volume();
        System.out.println("Volume of mybox2 is " + vol);
        System.out.println("Weight of mybox2 is " + mybox2.weight);
        System.out.println();

        vol = mybox3.volume();
        System.out.println("Volume of mybox3 is " + vol);
        System.out.println("Weight of mybox3 is " + mybox3.weight);
        System.out.println();

        vol = myclone.volume();
        System.out.println("Volume of myclone is " + vol);
        System.out.println("Weight of myclone is " + myclone.weight);
        System.out.println();

        vol = mycube.volume();
        System.out.println("Volume of mycube is " + vol);
        System.out.println("Weight of mycube is " + mycube.weight);
        System.out.println();
    }
}
```

**Output**:
```
Volume of mybox1 is 3000.0
Weight of mybox1 is 34.3

Volume of mybox2 is 24.0
Weight of mybox2 is 0.076

Volume of mybox3 is -1.0
Weight of mybox3 is -1.0

Volume of myclone is 3000.0
Weight of myclone is 34.3

Volume of mycube is 27.0
Weight of mycube is 2.0
```

**Explanation**:
- `Box` fields are private, enforcing encapsulation.
- `BoxWeight` uses `super` to call `Box` constructors, initializing inherited fields indirectly.
- Multiple constructors in `BoxWeight` match those in `Box`, ensuring flexibility.

### Example: Overcoming Name Hiding

This example shows how `super` accesses a hidden superclass field.

```java
class A {
    int i;
}

class B extends A {
    int i; // Hides i in A
    B(int a, int b) {
        super.i = a; // Access i in A
        i = b; // Access i in B
    }
    void show() {
        System.out.println("i in superclass: " + super.i);
        System.out.println("i in subclass: " + i);
    }
}

class UseSuper {
    public static void main(String[] args) {
        B subOb = new B(1, 2);
        subOb.show();
    }
}
```

**Output**:
```
i in superclass: 1
i in subclass: 2
```

**Explanation**:
- Class `B` defines its own `i`, hiding `A`'s `i`.
- `super.i` accesses the superclass's `i`, distinguishing it from the subclass's `i`.

## Multilevel Hierarchy

A subclass can act as a superclass for another subclass, creating a multilevel hierarchy. Each subclass inherits all traits from its superclasses.

### Example: Shipment Class

This example extends `BoxWeight` to create `Shipment`, adding a `cost` field.

```java
class Box {
    private double width;
    private double height;
    private double depth;

    Box(Box ob) {
        width = ob.width;
        height = ob.height;
        depth = ob.depth;
    }

    Box(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    Box() {
        width = -1;
        height = -1;
        depth = -1;
    }

    Box(double len) {
        width = height = depth = len;
    }

    double volume() {
        return width * height * depth;
    }
}

class BoxWeight extends Box {
    double weight;

    BoxWeight(BoxWeight ob) {
        super(ob);
        weight = ob.weight;
    }

    BoxWeight(double w, double h, double d, double m) {
        super(w, h, d);
        weight = m;
    }

    BoxWeight() {
        super();
        weight = -1;
    }

    BoxWeight(double len, double m) {
        super(len);
        weight = m;
    }
}

class Shipment extends BoxWeight {
    double cost;

    Shipment(Shipment ob) {
        super(ob);
        cost = ob.cost;
    }

    Shipment(double w, double h, double d, double m, double c) {
        super(w, h, d, m);
        cost = c;
    }

    Shipment() {
        super();
        cost = -1;
    }

    Shipment(double len, double m, double c) {
        super(len, m);
        cost = c;
    }
}

class DemoShipment {
    public static void main(String[] args) {
        Shipment shipment1 = new Shipment(10, 20, 15, 10, 3.41);
        Shipment shipment2 = new Shipment(2, 3, 4, 0.76, 1.28);
        double vol;

        vol = shipment1.volume();
        System.out.println("Volume of shipment1 is " + vol);
        System.out.println("Weight of shipment1 is " + shipment1.weight);
        System.out.println("Shipping cost: $" + shipment1.cost);
        System.out.println();

        vol = shipment2.volume();
        System.out.println("Volume of shipment2 is " + vol);
        System.out.println("Weight of shipment2 is " + shipment2.weight);
        System.out.println("Shipping cost: $" + shipment2.cost);
    }
}
```

**Output**:
```
Volume of shipment1 is 3000.0
Weight of shipment1 is 10.0
Shipping cost: $3.41

Volume of shipment2 is 24.0
Weight of shipment2 is 0.76
Shipping cost: $1.28
```

**Explanation**:
- `Shipment` inherits from `BoxWeight`, which inherits from `Box`, forming a three-level hierarchy.
- `super` in `Shipment` calls `BoxWeight` constructors, which in turn call `Box` constructors.
- This structure allows code reuse across multiple levels.

## Constructor Execution Order

In a class hierarchy, constructors are executed from the superclass to the subclass, ensuring proper initialization.

### Example: Constructor Order Demonstration

```java
class A {
    A() {
        System.out.println("Inside A's constructor.");
    }
}

class B extends A {
    B() {
        System.out.println("Inside B's constructor.");
    }
}

class C extends B {
    C() {
        System.out.println("Inside C's constructor.");
    }
}

class CallingCons {
    public static void main(String[] args) {
        C c = new C();
    }
}
```

**Output**:
```
Inside A's constructor.
Inside B's constructor.
Inside C's constructor.
```

**Explanation**:
- Creating a `C` object triggers the constructors in order: `A`, `B`, then `C`.
- This ensures that each superclass is fully initialized before the subclass.

## Method Overriding

Method overriding occurs when a subclass defines a method with the same name and type signature as a method in its superclass. The subclass method is called when invoked on a subclass object.

### Example: Basic Method Overriding

```java
class A {
    int i, j;
    A(int a, int b) {
        i = a;
        j = b;
    }
    void show() {
        System.out.println("i and j: " + i + " " + j);
    }
}

class B extends A {
    int k;
    B(int a, int b, int c) {
        super(a, b);
        k = c;
    }
    void show() {
        System.out.println("k: " + k);
    }
}

class Override {
    public static void main(String[] args) {
        B subOb = new B(1, 2, 3);
        subOb.show(); // Calls show() in B
    }
}
```

**Output**:
```
k: 3
```

**Explanation**:
- `B` overrides `A`'s `show()` method, so `subOb.show()` calls `B`'s version.
- The superclass method is hidden unless accessed via `super`.

### Example: Using `super` in Overriding

```java
class B extends A {
    int k;
    B(int a, int b, int c) {
        super(a, b);
        k = c;
    }
    void show() {
        super.show(); // Call A's show()
        System.out.println("k: " + k);
    }
}
```

**Output**:
```
i and j: 1 2
k: 3
```

**Explanation**:
- `super.show()` calls the superclass's `show()` method, allowing both versions to execute.

### Example: Overloading vs. Overriding

```java
class A {
    int i, j;
    A(int a, int b) {
        i = a;
        j = b;
    }
    void show() {
        System.out.println("i and j: " + i + " " + j);
    }
}

class B extends A {
    int k;
    B(int a, int b, int c) {
        super(a, b);
        k = c;
    }
    void show(String msg) { // Overloaded, not overridden
        System.out.println(msg + k);
    }
}

class Override {
    public static void main(String[] args) {
        B subOb = new B(1, 2, 3);
        subOb.show("This is k: "); // Calls show(String) in B
        subOb.show(); // Calls show() in A
    }
}
```

**Output**:
```
This is k: 3
i and j: 1 2
```

**Explanation**:
- `B`'s `show(String)` has a different signature than `A`'s `show()`, so it overloads rather than overrides.
- Both methods are accessible depending on the argument provided.

## Dynamic Method Dispatch

Dynamic method dispatch resolves calls to overridden methods at runtime based on the actual object type, enabling runtime polymorphism.

### Example: Dynamic Method Dispatch

```java
class A {
    void callme() {
        System.out.println("Inside A's callme method");
    }
}

class B extends A {
    void callme() {
        System.out.println("Inside B's callme method");
    }
}

class C extends A {
    void callme() {
        System.out.println("Inside C's callme method");
    }
}

class Dispatch {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        A r;

        r = a;
        r.callme(); // Calls A's callme
        r = b;
        r.callme(); // Calls B's callme
        r = c;
        r.callme(); // Calls C's callme
    }
}
```

**Output**:
```
Inside A's callme method
Inside B's callme method
Inside C's callme method
```

**Explanation**:
- The `r` variable, of type `A`, can refer to `A`, `B`, or `C` objects.
- The actual method called depends on the object type at runtime, not the reference type.

## Applying Method Overriding

Method overriding supports runtime polymorphism by allowing subclasses to provide specific implementations of general methods defined in a superclass.

### Example: Figure, Rectangle, and Triangle

```java
class Figure {
    double dim1;
    double dim2;
    Figure(double a, double b) {
        dim1 = a;
        dim2 = b;
    }
    double area() {
        System.out.println("Area for Figure is undefined.");
        return 0;
    }
}

class Rectangle extends Figure {
    Rectangle(double a, double b) {
        super(a, b);
    }
    double area() {
        System.out.println("Inside Area for Rectangle.");
        return dim1 * dim2;
    }
}

class Triangle extends Figure {
    Triangle(double a, double b) {
        super(a, b);
    }
    double area() {
        System.out.println("Inside Area for Triangle.");
        return dim1 * dim2 / 2;
    }
}

class FindAreas {
    public static void main(String[] args) {
        Figure f = new Figure(10, 10);
        Rectangle r = new Rectangle(9, 5);
        Triangle t = new Triangle(10, 8);
        Figure figref;

        figref = r;
        System.out.println("Area is " + figref.area());
        figref = t;
        System.out.println("Area is " + figref.area());
        figref = f;
        System.out.println("Area is " + figref.area());
    }
}
```

**Output**:
```
Inside Area for Rectangle.
Area is 45
Inside Area for Triangle.
Area is 40
Area for Figure is undefined.
Area is 0
```

**Explanation**:
- `Figure` defines a generic `area()` method.
- `Rectangle` and `Triangle` override `area()` with specific implementations.
- A `Figure` reference (`figref`) calls the appropriate `area()` method based on the actual object type.

## Abstract Classes

Abstract classes define a structure without providing complete method implementations, requiring subclasses to override abstract methods.

### Example: Simple Abstract Class

```java
abstract class A {
    abstract void callme();
    void callmetoo() {
        System.out.println("This is a concrete method.");
    }
}

class B extends A {
    void callme() {
        System.out.println("B's implementation of callme.");
    }
}

class AbstractDemo {
    public static void main(String[] args) {
        B b = new B();
        b.callme();
        b.callmetoo();
    }
}
```

**Output**:
```
B's implementation of callme.
This is a concrete method.
```

**Explanation**:
- `A` is abstract and cannot be instantiated.
- `callme()` is abstract, so `B` must provide an implementation.
- `callmetoo()` is concrete and inherited by `B`.

### Example: Abstract Figure Class

```java
abstract class Figure {
    double dim1;
    double dim2;
    Figure(double a, double b) {
        dim1 = a;
        dim2 = b;
    }
    abstract double area();
}

class Rectangle extends Figure {
    Rectangle(double a, double b) {
        super(a, b);
    }
    double area() {
        System.out.println("Inside Area for Rectangle.");
        return dim1 * dim2;
    }
}

class Triangle extends Figure {
    Triangle(double a, double b) {
        super(a, b);
    }
    double area() {
        System.out.println("Inside Area for Triangle.");
        return dim1 * dim2 / 2;
    }
}

class AbstractAreas {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(9, 5);
        Triangle t = new Triangle(10, 8);
        Figure figref;

        figref = r;
        System.out.println("Area is " + figref.area());
        figref = t;
        System.out.println("Area is " + figref.area());
    }
}
```

**Output**:
```
Inside Area for Rectangle.
Area is 45
Inside Area for Triangle.
Area is 40
```

**Explanation**:
- `Figure` is abstract with an abstract `area()` method.
- Subclasses must implement `area()`, ensuring a consistent interface.
- A `Figure` reference supports polymorphism without instantiating `Figure`.

## Using `final` with Inheritance

The `final` keyword prevents method overriding or class inheritance.

### Preventing Method Overriding

```java
class A {
    final void meth() {
        System.out.println("This is a final method.");
    }
}

class B extends A {
    // void meth() { // ERROR: Cannot override final method
    //     System.out.println("Illegal!");
    // }
}
```

**Explanation**:
- `meth()` in `A` is `final`, so `B` cannot override it.
- This ensures the method's behavior remains unchanged.

### Preventing Class Inheritance

```java
final class A {
    // ...
}

// class B extends A { // ERROR: Cannot extend final class
// }
```

**Explanation**:
- A `final` class cannot be extended, ensuring its implementation is fixed.

## Local Variable Type Inference and Inheritance

With `var`, the inferred type is based on the initializer's declared type, not the actual object type.

### Example: Type Inference with Inheritance

```java
class MyClass {
    // ...
}

class FirstDerivedClass extends MyClass {
    int x;
    // ...
}

class SecondDerivedClass extends FirstDerivedClass {
    int y;
    // ...
}

class TypeInferenceAndInheritance {
    static MyClass getObj(int which) {
        switch(which) {
            case 0: return new MyClass();
            case 1: return new FirstDerivedClass();
            default: return new SecondDerivedClass();
        }
    }
    public static void main(String[] args) {
        var mc = getObj(0); // Inferred as MyClass
        var mc2 = getObj(1); // Inferred as MyClass
        var mc3 = getObj(2); // Inferred as MyClass

        // mc2.x = 10; // Error: MyClass has no x field
        // mc3.y = 10; // Error: MyClass has no y field
    }
}
```

**Explanation**:
- `getObj()` returns `MyClass`, so `var` infers `MyClass` for all variables.
- Subclass fields (`x`, `y`) are inaccessible through a `MyClass` reference.

## The `Object` Class

All Java classes inherit from `Object`, which provides methods like `equals()`, `toString()`, and `hashCode()`. These can be overridden to customize behavior.

## Exercise: Vehicle Hierarchy

Create a class hierarchy for vehicles:
- **Superclass**: `Vehicle` with fields `maxSpeed` and `wheels`, and a method `description()`.
- **Subclass**: `Car` adding `seats` and overriding `description()`.
- **Subclass**: `Truck` extending `Car`, adding `payloadCapacity` and overriding `description()`.
- Demonstrate the hierarchy with a main class.

### Solution

```java
class Vehicle {
    int maxSpeed;
    int wheels;

    Vehicle(int maxSpeed, int wheels) {
        this.maxSpeed = maxSpeed;
        this.wheels = wheels;
    }

    String description() {
        return "Vehicle with max speed " + maxSpeed + " km/h and " + wheels + " wheels";
    }
}

class Car extends Vehicle {
    int seats;

    Car(int maxSpeed, int wheels, int seats) {
        super(maxSpeed, wheels);
        this.seats = seats;
    }

    @Override
    String description() {
        return super.description() + ", " + seats + " seats";
    }
}

class Truck extends Car {
    double payloadCapacity;

    Truck(int maxSpeed, int wheels, int seats, double payloadCapacity) {
        super(maxSpeed, wheels, seats);
        this.payloadCapacity = payloadCapacity;
    }

    @Override
    String description() {
        return super.description() + ", payload capacity " + payloadCapacity + " tons";
    }
}

class VehicleDemo {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle(120, 4);
        Car car = new Car(180, 4, 5);
        Truck truck = new Truck(100, 6, 2, 10.5);

        System.out.println(vehicle.description());
        System.out.println(car.description());
        System.out.println(truck.description());

        // Demonstrate polymorphism
        Vehicle ref;
        ref = car;
        System.out.println("Car via Vehicle reference: " + ref.description());
        ref = truck;
        System.out.println("Truck via Vehicle reference: " + ref.description());
    }
}
```

**Output**:
```
Vehicle with max speed 120 km/h and 4 wheels
Vehicle with max speed 180 km/h and 4 wheels, 5 seats
Vehicle with max speed 100 km/h and 6 wheels, 2 seats, payload capacity 10.5 tons
Car via Vehicle reference: Vehicle with max speed 180 km/h and 4 wheels, 5 seats
Truck via Vehicle reference: Vehicle with max speed 100 km/h and 6 wheels, 2 seats, payload capacity 10.5 tons
```

**Explanation**:
- `Vehicle` defines common attributes and a `description()` method.
- `Car` extends `Vehicle`, adding `seats` and enhancing `description()`.
- `Truck` extends `Car`, adding `payloadCapacity` and further customizing `description()`.
- Polymorphism is demonstrated by using a `Vehicle` reference to call overridden methods.

This guide provides a thorough understanding of Java inheritance, with practical examples and an exercise to reinforce learning. Upload this README to your GitHub repository for a comprehensive reference.
