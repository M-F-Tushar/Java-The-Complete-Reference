# Java Packages and Interfaces - Complete Guide

## Chapter Overview

This chapter examines two of Java's most innovative features: **packages** and **interfaces**. These features provide essential capabilities for organizing code and achieving polymorphism in Java applications.

### Key Concepts Covered:
- **Packages**: Containers for classes that compartmentalize the class name space
- **Interfaces**: Full abstraction of a class interface from its implementation
- **Access Control**: Fine-grained visibility control mechanisms
- **Polymorphism**: Dynamic method resolution through interface references

---

## Part 1: Packages

### What are Packages?

Packages are containers for classes that serve two primary purposes:
1. **Naming Mechanism**: Keep the class name space compartmentalized
2. **Visibility Control**: Control access to classes and their members

#### Problem Solved by Packages:
Without packages, all classes share the same global namespace, leading to:
- Name collisions between classes
- Difficulty managing large codebases
- Limited control over class visibility

### Defining a Package

#### Basic Syntax:
```java
package pkg;
```

#### Example - Creating a Simple Package:
```java
package mypackage;
```

**Important Rules:**
- Package statement must be the **first statement** in a Java source file
- If omitted, classes go into the **default package** (unnamed)
- Package names are **case-sensitive**
- Directory structure must match package hierarchy

### Hierarchical Packages

#### Syntax for Multi-level Packages:
```java
package pkg1[.pkg2[.pkg3]];
```

#### Example:
```java
package a.b.c;
```
- Must be stored in directory structure: `a\b\c` (Windows) or `a/b/c` (Unix/Linux)

### Finding Packages and CLASSPATH

Java runtime system locates packages using three methods:

1. **Current Working Directory**: Default starting point
2. **CLASSPATH Environment Variable**: Specify directory paths
3. **-classpath Option**: Command-line specification for java and javac

#### Example Directory Structure:
```
C:\MyPrograms\Java\mypack
```
**CLASSPATH should be**: `C:\MyPrograms\Java` (not including `mypack` itself)

### Complete Package Example

#### File: AccountBalance.java (in mypack directory)
```java
// A simple package
package mypack;

class Balance {
    String name;
    double bal;
    
    Balance(String n, double b) {
        name = n;
        bal = b;
    }
    
    void show() {
        if(bal < 0)
            System.out.print("--> ");
        System.out.println(name + ": $" + bal);
    }
}

class AccountBalance {
    public static void main(String[] args) {
        Balance[] current = new Balance[3];
        
        current[0] = new Balance("K. J. Fielding", 123.23);
        current[1] = new Balance("Will Tell", 157.02);
        current[2] = new Balance("Tom Jackson", -12.33);
        
        for(int i = 0; i < 3; i++) current[i].show();
    }
}
```

#### Compilation and Execution:
```bash
# Compile
javac mypack/AccountBalance.java

# Execute (from directory above mypack)
java mypack.AccountBalance

# WRONG - This will NOT work:
java AccountBalance  // Error: class must be qualified with package name
```

### Packages and Member Access

Java provides **four categories of visibility** for class members:

1. **Subclasses in the same package**
2. **Non-subclasses in the same package**
3. **Subclasses in different packages**
4. **Classes that are neither in the same package nor subclasses**

#### Access Control Table:

| Access Level | Same Class | Same Package Subclass | Same Package Non-subclass | Different Package Subclass | Different Package Non-subclass |
|--------------|------------|----------------------|---------------------------|----------------------------|-------------------------------|
| **private** | ✓ | ✗ | ✗ | ✗ | ✗ |
| **default** | ✓ | ✓ | ✓ | ✗ | ✗ |
| **protected** | ✓ | ✓ | ✓ | ✓ | ✗ |
| **public** | ✓ | ✓ | ✓ | ✓ | ✓ |

#### Simplified Rules:
- **public**: Accessible from anywhere
- **private**: Only within the same class
- **default**: Within the same package
- **protected**: Same package + subclasses in other packages

### Comprehensive Access Control Example

#### Package p1 Files:

**Protection.java:**
```java
package p1;

public class Protection {
    int n = 1;                    // default access
    private int n_pri = 2;        // private access
    protected int n_pro = 3;      // protected access
    public int n_pub = 4;         // public access
    
    public Protection() {
        System.out.println("base constructor");
        System.out.println("n = " + n);
        System.out.println("n_pri = " + n_pri);
        System.out.println("n_pro = " + n_pro);
        System.out.println("n_pub = " + n_pub);
    }
}
```

**Derived.java:**
```java
package p1;

class Derived extends Protection {
    Derived() {
        System.out.println("derived constructor");
        System.out.println("n = " + n);          // OK: default access, same package
        // System.out.println("n_pri = " + n_pri); // ERROR: private access
        System.out.println("n_pro = " + n_pro);  // OK: protected access
        System.out.println("n_pub = " + n_pub);  // OK: public access
    }
}
```

**SamePackage.java:**
```java
package p1;

class SamePackage {
    SamePackage() {
        Protection p = new Protection();
        System.out.println("same package constructor");
        System.out.println("n = " + p.n);          // OK: default access, same package
        // System.out.println("n_pri = " + p.n_pri); // ERROR: private access
        System.out.println("n_pro = " + p.n_pro);  // OK: protected access, same package
        System.out.println("n_pub = " + p.n_pub);  // OK: public access
    }
}
```

#### Package p2 Files:

**Protection2.java:**
```java
package p2;

class Protection2 extends p1.Protection {
    Protection2() {
        System.out.println("derived other package constructor");
        // System.out.println("n = " + n);        // ERROR: default access, different package
        // System.out.println("n_pri = " + n_pri); // ERROR: private access
        System.out.println("n_pro = " + n_pro);   // OK: protected access, subclass
        System.out.println("n_pub = " + n_pub);   // OK: public access
    }
}
```

**OtherPackage.java:**
```java
package p2;

class OtherPackage {
    OtherPackage() {
        p1.Protection p = new p1.Protection();
        System.out.println("other package constructor");
        // System.out.println("n = " + p.n);        // ERROR: default access, different package
        // System.out.println("n_pri = " + p.n_pri); // ERROR: private access
        // System.out.println("n_pro = " + p.n_pro); // ERROR: protected access, not subclass
        System.out.println("n_pub = " + p.n_pub);   // OK: public access
    }
}
```

#### Test Files:

**Demo.java (in p1):**
```java
package p1;

public class Demo {
    public static void main(String[] args) {
        Protection ob1 = new Protection();
        Derived ob2 = new Derived();
        SamePackage ob3 = new SamePackage();
    }
}
```

**Demo.java (in p2):**
```java
package p2;

public class Demo {
    public static void main(String[] args) {
        Protection2 ob1 = new Protection2();
        OtherPackage ob2 = new OtherPackage();
    }
}
```

### Importing Packages

The `import` statement brings classes or entire packages into visibility, eliminating the need for fully qualified names.

#### Import Syntax:
```java
import pkg1[.pkg2].(classname | *);
```

#### Examples:
```java
import java.util.Date;    // Import specific class
import java.io.*;         // Import entire package
```

#### Automatic Import:
```java
import java.lang.*;  // Automatically imported by compiler
```

#### Using Imports vs. Fully Qualified Names:

**With Import:**
```java
import java.util.*;
class MyDate extends Date {
}
```

**Without Import:**
```java
class MyDate extends java.util.Date {
}
```

### Making Classes Available Outside Packages

To make a class available outside its package, it must be declared **public**.

#### Enhanced Balance Class Example:
```java
package mypack;

/* Now the Balance class, its constructor, and its
   show() method are public. This means they can
   be used by non-subclass code outside their package.
*/
public class Balance {
    String name;
    double bal;
    
    public Balance(String n, double b) {
        name = n;
        bal = b;
    }
    
    public void show() {
        if(bal < 0)
            System.out.print("--> ");
        System.out.println(name + ": $" + bal);
    }
}
```

#### Using the Public Class:
```java
import mypack.*;

class TestBalance {
    public static void main(String[] args) {
        /* Because Balance is public, you may use Balance
           class and call its constructor. */
        Balance test = new Balance("J. J. Jaspers", 99.88);
        test.show(); // you may also call show()
    }
}
```

---

## Part 2: Interfaces

### What are Interfaces?

An **interface** fully abstracts a class's interface from its implementation. It specifies **what** a class must do, but not **how** it does it.

#### Key Characteristics:
- Methods are typically **abstract** (no body)
- Variables are **implicitly final and static**
- All methods and variables are **implicitly public**
- Classes can implement **multiple interfaces**
- Supports **dynamic method resolution** at runtime

### Defining an Interface

#### Basic Syntax:
```java
access interface name {
    return-type method-name1(parameter-list);
    return-type method-name2(parameter-list);
    type final-varname1 = value;
    type final-varname2 = value;
    // ...
    return-type method-nameN(parameter-list);
    type final-varnameN = value;
}
```

#### Simple Interface Example:
```java
interface Callback {
    void callback(int param);
}
```

### Implementing Interfaces

#### Implementation Syntax:
```java
class classname [extends superclass] [implements interface [,interface...]] {
    // class-body
}
```

#### Implementation Rules:
- Must implement **all methods** from the interface
- Methods must be declared **public**
- Type signatures must **match exactly**

#### Basic Implementation Example:
```java
class Client implements Callback {
    // Implement Callback's interface
    public void callback(int p) {
        System.out.println("callback called with " + p);
    }
}
```

#### Enhanced Implementation with Additional Methods:
```java
class Client implements Callback {
    // Implement Callback's interface
    public void callback(int p) {
        System.out.println("callback called with " + p);
    }
    
    void nonIfaceMeth() {
        System.out.println("Classes that implement interfaces " +
                          "may also define other members, too.");
    }
}
```

### Interface References and Polymorphism

You can declare variables as **interface references** that can refer to any object implementing that interface.

#### Basic Interface Reference Example:
```java
class TestIface {
    public static void main(String[] args) {
        Callback c = new Client();
        c.callback(42);
    }
}
```

**Output:**
```
callback called with 42
```

#### Polymorphic Interface Usage:

**Second Implementation:**
```java
// Another implementation of Callback.
class AnotherClient implements Callback {
    // Implement Callback's interface
    public void callback(int p) {
        System.out.println("Another version of callback");
        System.out.println("p squared is " + (p*p));
    }
}
```

**Polymorphic Test:**
```java
class TestIface2 {
    public static void main(String[] args) {
        Callback c = new Client();
        AnotherClient ob = new AnotherClient();
        
        c.callback(42);
        c = ob; // c now refers to AnotherClient object
        c.callback(42);
    }
}
```

**Output:**
```
callback called with 42
Another version of callback
p squared is 1764
```

### Partial Implementations

If a class doesn't implement all interface methods, it must be declared **abstract**.

```java
abstract class Incomplete implements Callback {
    int a, b;
    
    void show() {
        System.out.println(a + " " + b);
    }
    // callback() not implemented - class must be abstract
}
```

### Nested Interfaces

Interfaces can be declared as members of classes or other interfaces.

#### Nested Interface Example:
```java
// A nested interface example.
// This class contains a member interface.
class A {
    // this is a nested interface
    public interface NestedIF {
        boolean isNotNegative(int x);
    }
}

// B implements the nested interface.
class B implements A.NestedIF {
    public boolean isNotNegative(int x) {
        return x < 0 ? false: true;
    }
}

class NestedIFDemo {
    public static void main(String[] args) {
        // use a nested interface reference
        A.NestedIF nif = new B();
        
        if(nif.isNotNegative(10))
            System.out.println("10 is not negative");
        if(nif.isNotNegative(-12))
            System.out.println("this won't be displayed");
    }
}
```

**Key Points:**
- Nested interfaces can be **public**, **private**, or **protected**
- Must be **fully qualified** when used outside enclosing scope
- Name format: `EnclosingClass.NestedInterface`

### Practical Interface Application: Stack Example

#### Defining the Interface:
```java
// Define an integer stack interface.
interface IntStack {
    void push(int item); // store an item
    int pop();           // retrieve an item
}
```

#### Fixed-Size Implementation:
```java
// An implementation of IntStack that uses fixed storage.
class FixedStack implements IntStack {
    private int[] stck;
    private int tos;
    
    // allocate and initialize stack
    FixedStack(int size) {
        stck = new int[size];
        tos = -1;
    }
    
    // Push an item onto the stack
    public void push(int item) {
        if(tos == stck.length-1) // use length member
            System.out.println("Stack is full.");
        else
            stck[++tos] = item;
    }
    
    // Pop an item from the stack
    public int pop() {
        if(tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        }
        else
            return stck[tos--];
    }
}

class IFTest {
    public static void main(String[] args) {
        FixedStack mystack1 = new FixedStack(5);
        FixedStack mystack2 = new FixedStack(8);
        
        // push some numbers onto the stack
        for(int i = 0; i < 5; i++) mystack1.push(i);
        for(int i = 0; i < 8; i++) mystack2.push(i);
        
        // pop those numbers off the stack
        System.out.println("Stack in mystack1:");
        for(int i = 0; i < 5; i++)
            System.out.println(mystack1.pop());
        System.out.println("Stack in mystack2:");
        for(int i = 0; i < 8; i++)
            System.out.println(mystack2.pop());
    }
}
```

#### Dynamic Stack Implementation:
```java
// Implement a "growable" stack.
class DynStack implements IntStack {
    private int[] stck;
    private int tos;
    
    // allocate and initialize stack
    DynStack(int size) {
        stck = new int[size];
        tos = -1;
    }
    
    // Push an item onto the stack
    public void push(int item) {
        // if stack is full, allocate a larger stack
        if(tos == stck.length-1) {
            int[] temp = new int[stck.length * 2]; // double size
            for(int i = 0; i < stck.length; i++) temp[i] = stck[i];
            stck = temp;
            stck[++tos] = item;
        }
        else
            stck[++tos] = item;
    }
    
    // Pop an item from the stack
    public int pop() {
        if(tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        }
        else
            return stck[tos--];
    }
}

class IFTest2 {
    public static void main(String[] args) {
        DynStack mystack1 = new DynStack(5);
        DynStack mystack2 = new DynStack(8);
        
        // these loops cause each stack to grow
        for(int i = 0; i < 12; i++) mystack1.push(i);
        for(int i = 0; i < 20; i++) mystack2.push(i);
        
        System.out.println("Stack in mystack1:");
        for(int i = 0; i < 12; i++)
            System.out.println(mystack1.pop());
        System.out.println("Stack in mystack2:");
        for(int i = 0; i < 20; i++)
            System.out.println(mystack2.pop());
    }
}
```

#### Runtime Polymorphism with Interface References:
```java
/* Create an interface variable and
   access stacks through it.
*/
class IFTest3 {
    public static void main(String[] args) {
        IntStack mystack; // create an interface reference variable
        DynStack ds = new DynStack(5);
        FixedStack fs = new FixedStack(8);
        
        mystack = ds; // load dynamic stack
        // push some numbers onto the stack
        for(int i = 0; i < 12; i++) mystack.push(i);
        
        mystack = fs; // load fixed stack
        for(int i = 0; i < 8; i++) mystack.push(i);
        
        mystack = ds;
        System.out.println("Values in dynamic stack:");
        for(int i = 0; i < 12; i++)
            System.out.println(mystack.pop());
        
        mystack = fs;
        System.out.println("Values in fixed stack:");
        for(int i = 0; i < 8; i++)
            System.out.println(mystack.pop());
    }
}
```

**Key Benefits:**
- **Same interface**, different implementations
- **Runtime polymorphism**: Method calls resolved dynamically
- **Code flexibility**: Can switch between implementations easily

### Variables in Interfaces

Interfaces can contain **constants** that are shared among implementing classes.

#### Shared Constants Example:
```java
import java.util.Random;

interface SharedConstants {
    int NO = 0;
    int YES = 1;
    int MAYBE = 2;
    int LATER = 3;
    int SOON = 4;
    int NEVER = 5;
}

class Question implements SharedConstants {
    Random rand = new Random();
    
    int ask() {
        int prob = (int) (100 * rand.nextDouble());
        if (prob < 30)
            return NO;      // 30%
        else if (prob < 60)
            return YES;     // 30%
        else if (prob < 75)
            return LATER;   // 15%
        else if (prob < 98)
            return SOON;    // 13%
        else
            return NEVER;   // 2%
    }
}

class AskMe implements SharedConstants {
    static void answer(int result) {
        switch(result) {
            case NO:
                System.out.println("No");
                break;
            case YES:
                System.out.println("Yes");
                break;
            case MAYBE:
                System.out.println("Maybe");
                break;
            case LATER:
                System.out.println("Later");
                break;
            case SOON:
                System.out.println("Soon");
                break;
            case NEVER:
                System.out.println("Never");
                break;
        }
    }
    
    public static void main(String[] args) {
        Question q = new Question();
        answer(q.ask());
        answer(q.ask());
        answer(q.ask());
        answer(q.ask());
    }
}
```

**Sample Output:**
```
Later
Soon
No
Yes
```

### Interface Inheritance

Interfaces can **extend** other interfaces using the `extends` keyword.

#### Interface Inheritance Example:
```java
// One interface can extend another.
interface A {
    void meth1();
    void meth2();
}

// B now includes meth1() and meth2() -- it adds meth3().
interface B extends A {
    void meth3();
}

// This class must implement all of A and B
class MyClass implements B {
    public void meth1() {
        System.out.println("Implement meth1().");
    }
    
    public void meth2() {
        System.out.println("Implement meth2().");
    }
    
    public void meth3() {
        System.out.println("Implement meth3().");
    }
}

class IFExtend {
    public static void main(String[] args) {
        MyClass ob = new MyClass();
        ob.meth1();
        ob.meth2();
        ob.meth3();
    }
}
```

**Important:** A class implementing an interface must implement **all methods** from the entire inheritance chain.

---

## Part 3: Advanced Interface Features (JDK 8+)

### Default Interface Methods (JDK 8)

Starting with JDK 8, interfaces can provide **default implementations** for methods.

#### Default Method Fundamentals:

```java
public interface MyIF {
    // This is a "normal" interface method declaration.
    // It does NOT define a default implementation.
    int getNumber();
    
    // This is a default method. Notice that it provides
    // a default implementation.
    default String getString() {
        return "Default String";
    }
}
```

#### Basic Implementation:
```java
// Implement MyIF.
class MyIFImp implements MyIF {
    // Only getNumber() defined by MyIF needs to be implemented.
    // getString() can be allowed to default.
    public int getNumber() {
        return 100;
    }
}
```

#### Using Default Methods:
```java
// Use the default method.
class DefaultMethodDemo {
    public static void main(String[] args) {
        MyIFImp obj = new MyIFImp();
        
        // Can call getNumber(), because it is explicitly
        // implemented by MyIFImp:
        System.out.println(obj.getNumber());
        
        // Can also call getString(), because of default
        // implementation:
        System.out.println(obj.getString());
    }
}
```

**Output:**
```
100
Default String
```

#### Overriding Default Methods:
```java
class MyIFImp2 implements MyIF {
    // Here, implementations for both getNumber() and getString() are provided.
    public int getNumber() {
        return 100;
    }
    
    public String getString() {
        return "This is a different string.";
    }
}
```

### Practical Default Method Example

#### Enhanced IntStack with Default Method:
```java
interface IntStack {
    void push(int item); // store an item
    int pop();           // retrieve an item
    
    // Because clear() has a default, it need not be
    // implemented by a preexisting class that uses IntStack.
    default void clear() {
        System.out.println("clear() not implemented.");
    }
}
```

**Benefits:**
1. **Interface Evolution**: Add new methods without breaking existing code
2. **Optional Functionality**: Provide default behavior for optional methods

### Multiple Inheritance Conflict Resolution

When a class implements multiple interfaces with conflicting default methods, Java uses these rules:

1. **Class Implementation Priority**: Class methods override interface defaults
2. **Conflict Error**: If two interfaces have same default method and no class override exists, compilation error occurs
3. **Inheritance Priority**: Inheriting interface's default takes precedence over parent interface's default

#### Explicit Interface Reference:
```java
InterfaceName.super.methodName()
```

**Example:**
```java
Alpha.super.reset();  // Explicitly call Alpha's default reset() method
```

### Static Interface Methods (JDK 8)

Interfaces can define **static methods** that can be called independently of any object.

#### Static Method Example:
```java
public interface MyIF {
    // This is a "normal" interface method declaration.
    int getNumber();
    
    // This is a default method.
    default String getString() {
        return "Default String";
    }
    
    // This is a static interface method.
    static int getDefaultNumber() {
        return 0;
    }
}
```

#### Calling Static Interface Methods:
```java
int defNum = MyIF.getDefaultNumber();
```

**Important:** Static interface methods are **not inherited** by implementing classes or subinterfaces.

### Private Interface Methods (JDK 9)

Starting with JDK 9, interfaces can include **private methods** for code reuse among default methods.

#### Private Method Example:
```java
// Another version of IntStack that has a private interface
// method that is used by two default methods.
interface IntStack {
    void push(int item); // store an item
    int pop();           // retrieve an item
    
    // A default method that returns an array that contains
    // the top n elements on the stack.
    default int[] popNElements(int n) {
        // Return the requested elements.
        return getElements(n);
    }
    
    // A default method that returns an array that contains
    // the next n elements on the stack after skipping elements.
    default int[] skipAndPopNElements(int skip, int n) {
        // Skip the specified number of elements.
        getElements(skip);
        // Return the requested elements.
        return getElements(n);
    }
    
    // A private method that returns an array containing
    // the top n elements on the stack
    private int[] getElements(int n) {
        int[] elements = new int[n];
        for(int i = 0; i < n; i++) elements[i] = pop();
        return elements;
    }
}
```

**Benefits:**
- **Code Reuse**: Share common code between default methods
- **Encapsulation**: Private methods are not accessible outside the interface
- **Clean Interface**: Avoid exposing implementation details

---

## Summary and Best Practices

### Key Takeaways

#### Packages:
1. **Organization**: Use packages to organize related classes
2. **Naming**: Choose meaningful, hierarchical package names
3. **Access Control**: Leverage package-level access for related classes
4. **CLASSPATH**: Understand how Java locates packages

#### Interfaces:
1. **Abstraction**: Define what classes should do, not how
2. **Polymorphism**: Use interface references for flexible, extensible code
3. **Multiple Implementation**: Classes can implement multiple interfaces
4. **Evolution**: Use default methods to evolve interfaces safely

### Best Practices

#### Package Best Practices:
- Use reverse domain names for uniqueness (e.g., `com.company.project`)
- Keep related classes in the same package
- Use appropriate access modifiers
- Avoid the default package for real applications

#### Interface Best Practices:
- Keep interfaces focused and cohesive
- Use meaningful names that describe behavior
- Prefer composition over inheritance
- Use default methods sparingly and thoughtfully
- Document interface contracts clearly

### Modern Java Interface Features Summary

| Feature | JDK Version | Purpose |
|---------|-------------|---------|
| **Abstract Methods** | All versions | Define method contracts |
| **Constants** | All versions | Share constant values |
| **Default Methods** | JDK 8+ | Provide default implementations |
| **Static Methods** | JDK 8+ | Utility methods in interfaces |
| **Private Methods** | JDK 9+ | Code reuse within interfaces |

### Conclusion

Packages and interfaces are fundamental to Java's design philosophy:

- **Packages** provide essential organization and access control mechanisms
- **Interfaces** enable powerful abstraction and polymorphism
- **Modern features** enhance interface capabilities while maintaining backward compatibility

These features are crucial for building maintainable, scalable Java applications and form the foundation for many advanced Java concepts and frameworks.
