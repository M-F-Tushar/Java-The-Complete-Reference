# Java Chapter 12: Enumerations, Autoboxing, and Annotations - Complete Guide

## Table of Contents
1. [Introduction](#introduction)
2. [Enumerations](#enumerations)
3. [Type Wrappers](#type-wrappers)
4. [Autoboxing and Auto-unboxing](#autoboxing-and-auto-unboxing)
5. [Annotations](#annotations)

---

## Introduction

This chapter covers three essential features that have become indispensable in Java programming:
- **Enumerations**: Type-safe constants that define new data types
- **Autoboxing**: Automatic conversion between primitive types and wrapper objects
- **Annotations**: Metadata that can be embedded in source code

These features were added in JDK 5 and provide streamlined approaches to common programming tasks.

---

## Enumerations

### What are Enumerations?

An **enumeration** is a list of named constants that define a new data type and its legal values. An enumeration object can only hold values declared in its list - no other values are allowed.

### Key Characteristics:
- Explicitly specify the only legal values for a data type
- More powerful than `final` variables used in early Java versions
- In Java, enumerations define **class types** (not just simple constants)
- Can have constructors, methods, and instance variables

### Enumeration Fundamentals

#### Basic Syntax
```java
// An enumeration of apple varieties
enum Apple {
    Jonathan, GoldenDel, RedDel, Winesap, Cortland
}
```

#### Key Points:
- Created using the `enum` keyword
- Constants are `public`, `static`, and `final` members
- Constants are **self-typed** (their type is the enumeration type)
- Don't use `new` to instantiate - declare like primitive types

#### Usage Example:
```java
Apple ap;                    // Declare enumeration variable
ap = Apple.RedDel;          // Assign enumeration constant
if(ap == Apple.GoldenDel)   // Compare with == operator
    // ...
```

#### Switch Statement Usage:
```java
switch(ap) {
    case Jonathan:    // No need to qualify with Apple.Jonathan
        // ...
        break;
    case Winesap:
        // ...
        break;
}
```

### Complete Enumeration Example

```java
// An enumeration of apple varieties
enum Apple {
    Jonathan, GoldenDel, RedDel, Winesap, Cortland
}

class EnumDemo {
    public static void main(String[] args) {
        Apple ap;
        ap = Apple.RedDel;
        
        // Output an enum value
        System.out.println("Value of ap: " + ap);
        
        ap = Apple.GoldenDel;
        
        // Compare two enum values
        if(ap == Apple.GoldenDel)
            System.out.println("ap contains GoldenDel.\n");
        
        // Use an enum to control a switch statement
        switch(ap) {
            case Jonathan:
                System.out.println("Jonathan is red.");
                break;
            case GoldenDel:
                System.out.println("Golden Delicious is yellow.");
                break;
            case RedDel:
                System.out.println("Red Delicious is red.");
                break;
            case Winesap:
                System.out.println("Winesap is red.");
                break;
            case Cortland:
                System.out.println("Cortland is red.");
                break;
        }
    }
}
```

**Output:**
```
Value of ap: RedDel
ap contains GoldenDel.
Golden Delicious is yellow.
```

### Built-in Enumeration Methods

All enumerations automatically contain two predefined methods:

#### 1. `values()` Method
```java
public static enum-type[] values()
```
- Returns an array containing all enumeration constants
- Constants are returned in the order they were declared

#### 2. `valueOf()` Method  
```java
public static enum-type valueOf(String str)
```
- Returns the enumeration constant whose value matches the string
- Throws `IllegalArgumentException` if no match is found

#### Example Using Built-in Methods:
```java
enum Apple {
    Jonathan, GoldenDel, RedDel, Winesap, Cortland
}

class EnumDemo2 {
    public static void main(String[] args) {
        Apple ap;
        
        System.out.println("Here are all Apple constants:");
        
        // Use values()
        Apple[] allapples = Apple.values();
        for(Apple a : allapples)
            System.out.println(a);
        
        System.out.println();
        
        // Use valueOf()
        ap = Apple.valueOf("Winesap");
        System.out.println("ap contains " + ap);
    }
}
```

**Output:**
```
Here are all Apple constants:
Jonathan
GoldenDel
RedDel
Winesap
Cortland
ap contains Winesap
```

### Java Enumerations Are Class Types

Since enumerations define class types, they have powerful capabilities:

#### Enumerations with Constructors and Methods:
```java
enum Apple {
    Jonathan(10), GoldenDel(9), RedDel(12), Winesap(15), Cortland(8);
    
    private int price; // price of each apple
    
    // Constructor
    Apple(int p) { price = p; }
    
    int getPrice() { return price; }
}

class EnumDemo3 {
    public static void main(String[] args) {
        // Display price of Winesap
        System.out.println("Winesap costs " +
                          Apple.Winesap.getPrice() +
                          " cents.\n");
        
        // Display all apples and prices
        System.out.println("All apple prices:");
        for(Apple a : Apple.values())
            System.out.println(a + " costs " + a.getPrice() +
                              " cents.");
    }
}
```

**Output:**
```
Winesap costs 15 cents.

All apple prices:
Jonathan costs 10 cents.
GoldenDel costs 9 cents.
RedDel costs 12 cents.
Winesap costs 15 cents.
Cortland costs 8 cents.
```

#### Key Points About Enum Constructors:
- Constructor is called once for each constant when the enum is first used
- Arguments are specified in parentheses after each constant
- Each enumeration constant has its own copy of instance variables
- Can have multiple overloaded constructors

#### Example with Multiple Constructors:
```java
enum Apple {
    Jonathan(10), GoldenDel(9), RedDel, Winesap(15), Cortland(8);
    
    private int price;
    
    // Constructor with parameter
    Apple(int p) { price = p; }
    
    // Default constructor
    Apple() { price = -1; }
    
    int getPrice() { return price; }
}
```

### Enumeration Restrictions:
1. An enumeration **cannot inherit** another class
2. An enum **cannot be a superclass** (cannot be extended)
3. Otherwise, enum acts like any other class type

### Enumerations Inherit Enum

All enumerations automatically inherit `java.lang.Enum` class, which provides several useful methods:

#### 1. `ordinal()` Method
```java
final int ordinal()
```
- Returns the ordinal value (position) of the constant
- Ordinal values begin at zero

#### 2. `compareTo()` Method
```java
final int compareTo(enum-type e)
```
- Compares ordinal values of two constants from the same enumeration
- Returns negative, zero, or positive value

#### 3. `equals()` Method
- Compares enumeration constant with any other object
- Objects are equal only if they refer to the same constant in the same enumeration

#### Example Demonstrating Enum Methods:
```java
enum Apple {
    Jonathan, GoldenDel, RedDel, Winesap, Cortland
}

class EnumDemo4 {
    public static void main(String[] args) {
        Apple ap, ap2, ap3;
        
        // Display all ordinal values
        System.out.println("Here are all apple constants" +
                          " and their ordinal values: ");
        for(Apple a : Apple.values())
            System.out.println(a + " " + a.ordinal());
        
        ap = Apple.RedDel;
        ap2 = Apple.GoldenDel;
        ap3 = Apple.RedDel;
        
        System.out.println();
        
        // Demonstrate compareTo() and equals()
        if(ap.compareTo(ap2) < 0)
            System.out.println(ap + " comes before " + ap2);
        
        if(ap.compareTo(ap2) > 0)
            System.out.println(ap2 + " comes before " + ap);
        
        if(ap.compareTo(ap3) == 0)
            System.out.println(ap + " equals " + ap3);
        
        System.out.println();
        
        if(ap.equals(ap2))
            System.out.println("Error!");
        
        if(ap.equals(ap3))
            System.out.println(ap + " equals " + ap3);
        
        if(ap == ap3)
            System.out.println(ap + " == " + ap3);
    }
}
```

**Output:**
```
Here are all apple constants and their ordinal values:
Jonathan 0
GoldenDel 1
RedDel 2
Winesap 3
Cortland 4

GoldenDel comes before RedDel
RedDel equals RedDel
RedDel equals RedDel
RedDel == RedDel
```

### Practical Enumeration Example

Here's an improved version of a "Decision Maker" program using enumerations:

```java
import java.util.Random;

// An enumeration of the possible answers
enum Answers {
    NO, YES, MAYBE, LATER, SOON, NEVER
}

class Question {
    Random rand = new Random();
    
    Answers ask() {
        int prob = (int) (100 * rand.nextDouble());
        
        if (prob < 15)
            return Answers.MAYBE;   // 15%
        else if (prob < 30)
            return Answers.NO;      // 15%
        else if (prob < 60)
            return Answers.YES;     // 30%
        else if (prob < 75)
            return Answers.LATER;   // 15%
        else if (prob < 98)
            return Answers.SOON;    // 13%
        else
            return Answers.NEVER;   // 2%
    }
}

class AskMe {
    static void answer(Answers result) {
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

---

## Type Wrappers

### Why Type Wrappers?

Java uses **primitive types** (int, double, char, etc.) for performance reasons. However, sometimes you need object representations of primitive types:
- To pass by reference to methods
- To use with data structures that operate only on objects
- To integrate primitive types into Java's object hierarchy

### Type Wrapper Classes

Java provides wrapper classes for all primitive types:

| Primitive Type | Wrapper Class |
|---------------|---------------|
| boolean       | Boolean       |
| byte          | Byte          |
| char          | Character     |
| short         | Short         |
| int           | Integer       |
| long          | Long          |
| float         | Float         |
| double        | Double        |

### Character Wrapper

#### Modern Approach (Recommended):
```java
static Character valueOf(char ch)  // Returns Character object
char charValue()                   // Returns wrapped char value
```

#### Example:
```java
Character ch = Character.valueOf('A');
char c = ch.charValue();
```

### Boolean Wrapper

#### Modern Approach (Recommended):
```java
static Boolean valueOf(boolean boolValue)
static Boolean valueOf(String boolString)
boolean booleanValue()  // Returns wrapped boolean value
```

#### Example:
```java
Boolean b1 = Boolean.valueOf(true);
Boolean b2 = Boolean.valueOf("false");
boolean bool = b1.booleanValue();
```

### Numeric Type Wrappers

All numeric wrappers inherit the abstract class `Number`, which provides conversion methods:

```java
byte byteValue()
double doubleValue()
float floatValue()
int intValue()
long longValue()
short shortValue()
```

#### Modern Approach for Integer (Similar for all numeric types):
```java
static Integer valueOf(int val)
static Integer valueOf(String valStr) throws NumberFormatException
```

#### Example:
```java
Integer iOb = Integer.valueOf(100);
int i = iOb.intValue();
System.out.println(i + " " + iOb); // displays 100 100
```

### Boxing and Unboxing Concepts

- **Boxing**: Encapsulating a primitive value within an object
- **Unboxing**: Extracting a primitive value from a wrapper object

```java
// Boxing
Integer iOb = Integer.valueOf(100);

// Unboxing
int i = iOb.intValue();
```

---

## Autoboxing and Auto-unboxing

### What is Autoboxing?

**Autoboxing** is the automatic encapsulation of a primitive type into its equivalent wrapper when an object is needed.

**Auto-unboxing** is the automatic extraction of a primitive value from a wrapper when a primitive is needed.

### Benefits:
- Streamlines coding algorithms
- Removes tedious manual boxing/unboxing
- Helps prevent errors
- Essential for generics and Collections Framework

### Basic Autoboxing/Unboxing

```java
class AutoBox {
    public static void main(String[] args) {
        Integer iOb = 100;  // autobox an int
        int i = iOb;        // auto-unbox
        System.out.println(i + " " + iOb); // displays 100 100
    }
}
```

### Autoboxing with Methods

Autoboxing/unboxing occurs automatically with method parameters and return values:

```java
class AutoBox2 {
    // Take an Integer parameter and return an int value
    static int m(Integer v) {
        return v; // auto-unbox to int
    }
    
    public static void main(String[] args) {
        // Pass an int to m() - autoboxed to Integer
        // Return value is autoboxed to Integer
        Integer iOb = m(100);
        System.out.println(iOb);
    }
}
```

**Output:** `100`

### Autoboxing/Unboxing in Expressions

Autoboxing and unboxing occur within expressions:

```java
class AutoBox3 {
    public static void main(String[] args) {
        Integer iOb, iOb2;
        int i;
        
        iOb = 100;
        System.out.println("Original value of iOb: " + iOb);
        
        // Auto-unbox, increment, then re-box
        ++iOb;
        System.out.println("After ++iOb: " + iOb);
        
        // Complex expression with auto-unboxing/boxing
        iOb2 = iOb + (iOb / 3);
        System.out.println("iOb2 after expression: " + iOb2);
        
        // Result not reboxed
        i = iOb + (iOb / 3);
        System.out.println("i after expression: " + i);
    }
}
```

**Output:**
```
Original value of iOb: 100
After ++iOb: 101
iOb2 after expression: 134
i after expression: 134
```

### Mixing Numeric Types

Auto-unboxing allows mixing different numeric wrapper types:

```java
class AutoBox4 {
    public static void main(String[] args) {
        Integer iOb = 100;
        Double dOb = 98.6;
        
        dOb = dOb + iOb;  // Both are auto-unboxed, then result reboxed
        System.out.println("dOb after expression: " + dOb);
    }
}
```

**Output:** `dOb after expression: 198.6`

### Autoboxing with Switch Statements

Integer objects can control switch statements through auto-unboxing:

```java
Integer iOb = 2;
switch(iOb) {  // iOb is auto-unboxed
    case 1: 
        System.out.println("one");
        break;
    case 2: 
        System.out.println("two");
        break;
    default: 
        System.out.println("error");
}
```

### Boolean and Character Autoboxing

```java
class AutoBox5 {
    public static void main(String[] args) {
        // Autobox/unbox a boolean
        Boolean b = true;
        
        // b is auto-unboxed in conditional expression
        if(b) System.out.println("b is true");
        
        // Autobox/unbox a char
        Character ch = 'x';  // box a char
        char ch2 = ch;       // unbox a char
        System.out.println("ch2 is " + ch2);
    }
}
```

**Output:**
```
b is true
ch2 is x
```

### Boolean in Loop Controls

Boolean objects can control loop statements:

```java
Boolean b = true;
while(b) {  // b is automatically unboxed
    // loop body
    // ... code to eventually set b to false
}
```

### Error Prevention

Autoboxing helps prevent errors that can occur with manual boxing/unboxing:

```java
// Problematic manual unboxing
class UnboxingError {
    public static void main(String[] args) {
        Integer iOb = 1000;  // autobox the value 1000
        int i = iOb.byteValue();  // manually unbox as byte - ERROR!
        System.out.println(i);  // displays -24, not 1000!
    }
}
```

Auto-unboxing prevents this by always producing the correct type.

### Performance Warning

**Important:** Don't abandon primitives entirely! This is inefficient:

```java
// BAD use of autoboxing/unboxing!
Double a, b, c;
a = 10.0;
b = 4.0;
c = Math.sqrt(a*a + b*b);  // Much overhead from boxing/unboxing
```

**Better approach:**
```java
double a, b, c;
a = 10.0;
b = 4.0;
c = Math.sqrt(a*a + b*b);  // Efficient primitive operations
```

**Guideline:** Use wrapper objects only when object representation is specifically required.

---

## Annotations

### What are Annotations?

**Annotations** are a way to embed supplemental information into source files. This metadata:
- Does not change program semantics
- Can be used by development and deployment tools
- Provides information for code generators, documentation tools, etc.

### Annotation Basics

Annotations are created using a mechanism based on interfaces:

```java
// A simple annotation type
@interface MyAnno {
    String str();
    int val();
}
```

#### Key Points:
- `@interface` declares an annotation type
- Annotations consist solely of method declarations (no method bodies)
- Java implements these methods automatically
- Methods act like fields when used
- All annotations automatically extend the `Annotation` interface

### Using Annotations

Annotations can be applied to various declarations:

```java
// Annotate a method
@MyAnno(str = "Annotation Example", val = 100)
public static void myMeth() {
    // method body
}
```

#### Annotation Syntax:
- Annotation name preceded by `@`
- Parenthesized list of member initializations
- Members are assigned values using `member = value` syntax

### Retention Policies

A **retention policy** determines when an annotation is discarded. Java defines three policies in `RetentionPolicy` enum:

1. **SOURCE**: Retained only in source file, discarded during compilation
2. **CLASS**: Stored in .class file, but not available at runtime (default)
3. **RUNTIME**: Available through JVM at runtime

```java
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String str();
    int val();
}
```

### Reflection and Runtime Annotations

To access annotations at runtime, use reflection:

#### Complete Example:
```java
import java.lang.annotation.*;
import java.lang.reflect.*;

// An annotation type declaration
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String str();
    int val();
}

class Meta {
    // Annotate a method
    @MyAnno(str = "Annotation Example", val = 100)
    public static void myMeth() {
        Meta ob = new Meta();
        
        try {
            // Get Class object representing this class
            Class<?> c = ob.getClass();
            
            // Get Method object representing this method
            Method m = c.getMethod("myMeth");
            
            // Get the annotation for this method
            MyAnno anno = m.getAnnotation(MyAnno.class);
            
            // Display the values
            System.out.println(anno.str() + " " + anno.val());
        } catch (NoSuchMethodException exc) {
            System.out.println("Method Not Found.");
        }
    }
    
    public static void main(String[] args) {
        myMeth();
    }
}
```

**Output:** `Annotation Example 100`

#### Key Reflection Methods:
- `getClass()`: Returns Class object
- `getMethod(String name, Class<?>... paramTypes)`: Returns Method object
- `getAnnotation(Class<A> annoType)`: Returns specific annotation
- `MyAnno.class`: Class literal for the annotation type

### Methods with Parameters

For methods with parameters, specify parameter types in `getMethod()`:

```java
@MyAnno(str = "Two Parameters", val = 19)
public static void myMeth(String str, int i) {
    // method body
}

// In reflection code:
Method m = c.getMethod("myMeth", String.class, int.class);
```

### Getting All Annotations

Use `getAnnotations()` to retrieve all annotations:

```java
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String str();
    int val();
}

@Retention(RetentionPolicy.RUNTIME)
@interface What {
    String description();
}

@What(description = "An annotation test class")
@MyAnno(str = "Meta2", val = 99)
class Meta2 {
    @What(description = "An annotation test method")
    @MyAnno(str = "Testing", val = 100)
    public static void myMeth() {
        Meta2 ob = new Meta2();
        
        try {
            Annotation[] annos = ob.getClass().getAnnotations();
            
            // Display all annotations for Meta2
            System.out.println("All annotations for Meta2:");
            for(Annotation a : annos)
                System.out.println(a);
            
            System.out.println();
            
            // Display all annotations for myMeth
            Method m = ob.getClass().getMethod("myMeth");
            annos = m.getAnnotations();
            
            System.out.println("All annotations for myMeth:");
            for(Annotation a : annos)
                System.out.println(a);
                
        } catch (NoSuchMethodException exc) {
            System.out.println("Method Not Found.");
        }
    }
    
    public static void main(String[] args) {
        myMeth();
    }
}
```

**Output:**
```
All annotations for Meta2:
@What(description=An annotation test class)
@MyAnno(str=Meta2, val=99)

All annotations for myMeth:
@What(description=An annotation test method)
@MyAnno(str=Testing, val=100)
```

### The AnnotatedElement Interface

The reflection methods for annotations are defined in `AnnotatedElement` interface:

#### Key Methods:
- `getAnnotation(Class<T> annotationType)`
- `getAnnotations()`
- `getDeclaredAnnotations()`: Non-inherited annotations only
- `isAnnotationPresent(Class<? extends Annotation> annotationType)`: Returns boolean
- `getAnnotationsByType(Class<T> annotationType)` (JDK 8+)
- `getDeclaredAnnotationsByType(Class<T> annotationType)` (JDK 8+)

### Default Values

Annotation members can have default values:

```java
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String str() default "Testing";
    int val() default 9000;
}
```

#### Usage Options:
```java
@MyAnno()                                   // both defaults
@MyAnno(str = "some string")               // val defaults
@MyAnno(val = 100)                         // str defaults  
@MyAnno(str = "Testing", val = 100)        // no defaults
```

#### Example with Defaults:
```java
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String str() default "Testing";
    int val() default 9000;
}

class Meta3 {
    // Use default values
    @MyAnno()
    public static void myMeth() {
        Meta3 ob = new Meta3();
        
        try {
            Class<?> c = ob.getClass();
            Method m = c.getMethod("myMeth");
            MyAnno anno = m.getAnnotation(MyAnno.class);
            System.out.println(anno.str() + " " + anno.val());
        } catch (NoSuchMethodException exc) {
            System.out.println("Method Not Found.");
        }
    }
    
    public static void main(String[] args) {
        myMeth();
    }
}
```

**Output:** `Testing 9000`

### Marker Annotations

**Marker annotations** contain no members - their presence alone provides information:

```java
import java.lang.annotation.*;
import java.lang.reflect.*;

// A marker annotation
@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker { }

class Marker {
    // Apply marker annotation (no parentheses needed)
    @MyMarker
    public static void myMeth() {
        Marker ob = new Marker();
        
        try {
            Method m = ob.getClass().getMethod("myMeth");
            
            // Check if annotation is present
            if(m.isAnnotationPresent(MyMarker.class))
                System.out.println("MyMarker is present.");
                
        } catch (NoSuchMethodException exc) {
            System.out.println("Method Not Found.");
        }
    }
    
    public static void main(String[] args) {
        myMeth();
    }
}
```

**Output:** `MyMarker is present.`

### Single-Member Annotations

When an annotation has only one member named `value`, you can use shorthand syntax:

```java
import java.lang.annotation.*;
import java.lang.reflect.*;

// A single-member annotation
@Retention(RetentionPolicy.RUNTIME)
@interface MySingle {
    int value(); // must be named "value"
}

class Single {
    // Use shorthand syntax
    @MySingle(100)  // equivalent to @MySingle(value = 100)
    public static void myMeth() {
        Single ob = new Single();
        
        try {
            Method m = ob.getClass().getMethod("myMeth");
            MySingle anno = m.getAnnotation(MySingle.class);
            System.out.println(anno.value()); // displays 100
        } catch (NoSuchMethodException exc) {
            System.out.println("Method Not Found.");
        }
    }
    
    public static void main(String[] args) {
        myMeth();
    }
}
```

#### Multiple Members with Single-Value Syntax:
```java
@interface SomeAnno {
    int value();
    int xyz() default 0;
}

// Usage:
@SomeAnno(88)                           // xyz defaults to 0, value = 88
@SomeAnno(value = 88, xyz = 99)        // explicit specification required
```

### Built-In Annotations

Java provides several built-in annotations:

#### Meta-Annotations (for annotating annotations):

##### `@Retention`
Specifies retention policy:
```java
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno { }
```

##### `@Target`
Specifies where annotation can be applied:
```java
@Target(ElementType.METHOD)
@interface MyMethodAnno { }

@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@interface MyFieldVarAnno { }
```

**ElementType Constants:**
- `ANNOTATION_TYPE`: Other annotations
- `CONSTRUCTOR`: Constructors
- `FIELD`: Fields
- `LOCAL_VARIABLE`: Local variables  
- `METHOD`: Methods
- `MODULE`: Modules
- `PACKAGE`: Packages
- `PARAMETER`: Parameters
- `RECORD_COMPONENT`: Record components
- `TYPE`: Classes, interfaces, enumerations
- `TYPE_PARAMETER`: Type parameters
- `TYPE_USE`: Type uses

##### `@Documented`
Marker indicating annotation should be documented:
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface DocumentedAnno { }
```

##### `@Inherited`
Causes superclass annotations to be inherited by subclasses:
```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface InheritedAnno { }
```

#### General-Purpose Annotations:

##### `@Override`
Ensures method overrides superclass method:
```java
class Parent {
    void method() { }
}

class Child extends Parent {
    @Override
    void method() { }  // Compile error if not actually overriding
}
```

##### `@Deprecated`
Indicates obsolete API:
```java
@Deprecated
public void oldMethod() { }

// JDK 9+ version with additional info:
@Deprecated(since = "1.5", forRemoval = true)
public void oldMethod() { }
```

##### `@SuppressWarnings`
Suppresses compiler warnings:
```java
@SuppressWarnings("unchecked")
List list = new ArrayList();

@SuppressWarnings({"unchecked", "deprecation"})
void method() { }
```

##### `@FunctionalInterface`
Indicates interface is functional (for lambda expressions):
```java
@FunctionalInterface
interface MyFunc {
    void call();  // exactly one abstract method
}
```

##### `@SafeVarargs`
Suppresses varargs warnings:
```java
@SafeVarargs
public static <T> void method(T... args) { }
```

### Type Annotations (JDK 8+)

Type annotations can be applied to type uses, not just declarations:

#### Target Specification for Type Annotations:
```java
@Target(ElementType.TYPE_USE)
@interface TypeAnno { }
```

#### Where Type Annotations Can Be Used:
- Method return types
- Parameter types
- Field types
- Local variable types
- Cast expressions
- Generic type arguments
- Array component types
- Exception types in throws clauses
- Type parameters
- Inheritance clauses

#### Comprehensive Type Annotation Example:
```java
import java.lang.annotation.*;
import java.lang.reflect.*;

// Type annotations
@Target(ElementType.TYPE_USE)
@interface TypeAnno { }

@Target(ElementType.TYPE_USE)
@interface NotZeroLen { }

@Target(ElementType.TYPE_USE)
@interface Unique { }

@Target(ElementType.TYPE_USE)
@interface MaxLen {
    int value();
}

// Type parameter annotation
@Target(ElementType.TYPE_PARAMETER)
@interface What {
    String description();
}

// Field declaration annotation
@Target(ElementType.FIELD)
@interface EmptyOK { }

// Method declaration annotation
@Target(ElementType.METHOD)
@interface Recommended { }

// Use type parameter annotation
class TypeAnnoDemo<@What(description = "Generic data type") T> {
    
    // Annotate constructor return type
    public @Unique TypeAnnoDemo() {}
    
    // Annotate the type String, not the field
    @TypeAnno String str;
    
    // This annotates the field test (not the type)
    @EmptyOK String test;
    
    // Annotate 'this' parameter
    public int f(@TypeAnno TypeAnnoDemo<T> this, int x) {
        return 10;
    }
    
    // Annotate return type
    public @TypeAnno Integer f2(int j, int k) {
        return j + k;
    }
    
    // Annotate method declaration (not return type)
    public @Recommended Integer f3(String str) {
        return str.length() / 2;
    }
    
    // Annotate exception type in throws clause
    public void f4() throws @TypeAnno NullPointerException {
        // method body
    }
    
    // Annotate array levels
    String @MaxLen(10) [] @NotZeroLen [] w;
    
    // Annotate array element type
    @TypeAnno Integer[] vec;
    
    public static void myMeth(int i) {
        // Annotate type argument
        TypeAnnoDemo<@TypeAnno Integer> ob = 
            new TypeAnnoDemo<@TypeAnno Integer>();
        
        // Annotate constructor call
        @Unique TypeAnnoDemo<Integer> ob2 = 
            new @Unique TypeAnnoDemo<Integer>();
        
        Object x = Integer.valueOf(10);
        Integer y;
        
        // Annotate cast
        y = (@TypeAnno Integer) x;
    }
    
    public static void main(String[] args) {
        myMeth(10);
    }
}

// Annotate inheritance clause
class SomeClass extends @TypeAnno TypeAnnoDemo<Boolean> {}
```

#### Key Distinctions in Type Annotations:

**1. Method Return Type vs. Method Declaration:**
```java
// Annotates the return type (TYPE_USE target)
public @TypeAnno Integer f2(int j, int k) {
    return j + k;
}

// Annotates the method declaration (METHOD target)
public @Recommended Integer f3(String str) {
    return str.length() / 2;
}
```

**2. Field Type vs. Field Declaration:**
```java
// Annotates the type String (TYPE_USE target)
@TypeAnno String str;

// Annotates the field test (FIELD target)
@EmptyOK String test;
```

**3. Array Level Annotations:**
```java
// @MaxLen annotates first level, @NotZeroLen annotates second level
String @MaxLen(10) [] @NotZeroLen [] w;

// @TypeAnno annotates the element type Integer
@TypeAnno Integer[] vec;
```

**4. Explicit 'this' Parameter:**
```java
// Annotate the receiver parameter 'this'
public int f(@TypeAnno TypeAnnoDemo<T> this, int x) {
    return 10;
}
```

Note: You only need to explicitly declare `this` if you want to annotate it. It must be the first parameter and must match the class type.

### Repeating Annotations (JDK 8+)

Starting with JDK 8, the same annotation can be repeated on the same element:

#### Creating Repeatable Annotations:
```java
import java.lang.annotation.*;
import java.lang.reflect.*;

// Make MyAnno repeatable
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyRepeatedAnnos.class)
@interface MyAnno {
    String str() default "Testing";
    int val() default 9000;
}

// Container annotation
@Retention(RetentionPolicy.RUNTIME)
@interface MyRepeatedAnnos {
    MyAnno[] value();
}

class RepeatAnno {
    // Repeat MyAnno on myMeth()
    @MyAnno(str = "First annotation", val = -1)
    @MyAnno(str = "Second annotation", val = 100)
    public static void myMeth(String str, int i) {
        RepeatAnno ob = new RepeatAnno();
        
        try {
            Class<?> c = ob.getClass();
            Method m = c.getMethod("myMeth", String.class, int.class);
            
            // Get repeated annotations via container
            Annotation anno = m.getAnnotation(MyRepeatedAnnos.class);
            System.out.println(anno);
            
        } catch (NoSuchMethodException exc) {
            System.out.println("Method Not Found.");
        }
    }
    
    public static void main(String[] args) {
        myMeth("test", 10);
    }
}
```

**Output:**
```
@MyRepeatedAnnos(value={@MyAnno(val=-1, str="First annotation"), 
@MyAnno(val=100, str="Second annotation")})
```

#### Accessing Individual Repeated Annotations:
```java
// Use getAnnotationsByType() to get individual annotations
MyAnno[] annos = m.getAnnotationsByType(MyAnno.class);
for(MyAnno a : annos)
    System.out.println(a);
```

**Output:**
```
@MyAnno(val=-1, str="First annotation")
@MyAnno(val=100, str="Second annotation")
```

#### Key Points for Repeatable Annotations:
1. Annotation must be marked with `@Repeatable`
2. Must specify a container annotation
3. Container annotation has a `value()` method returning array of repeatable annotation
4. Access via container annotation or `getAnnotationsByType()`

### Annotation Restrictions

#### Declaration Restrictions:
1. **No inheritance**: Annotations cannot inherit other classes
2. **No extension**: Annotations cannot be extended by other annotations
3. **No type parameters**: Annotations cannot be generic

#### Method Restrictions:
All annotation methods must:
1. **Have no parameters**
2. **Return one of these types:**
   - Primitive type (int, double, etc.)
   - String
   - Class
   - Enum type
   - Another annotation type
   - Array of any legal type

#### Valid Method Examples:
```java
@interface ValidAnno {
    int intValue();                    // primitive
    String stringValue();              // String
    Class<?> classValue();             // Class
    ElementType enumValue();           // enum
    Override annotationValue();        // annotation
    int[] arrayValue();               // array of legal type
}
```

#### Invalid Method Examples:
```java
@interface InvalidAnno {
    int method(String param);          // ERROR: has parameters
    List<String> listValue();          // ERROR: generic type not allowed
    Object objectValue();              // ERROR: Object not allowed
    void voidMethod();                 // ERROR: void not allowed
    int method() throws Exception;     // ERROR: throws clause not allowed
}
```

### Complete Practical Example: Validation Framework

Here's a comprehensive example showing how annotations can be used in a validation framework:

```java
import java.lang.annotation.*;
import java.lang.reflect.*;

// Validation annotations
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotNull { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min() default 0;
    int max() default 100;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Email { }

// Entity class using validation annotations
class User {
    @NotNull
    private String name;
    
    @Range(min = 18, max = 120)
    private int age;
    
    @Email
    @NotNull
    private String emailAddress;
    
    // Constructor
    public User(String name, int age, String emailAddress) {
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmailAddress() { return emailAddress; }
}

// Validation framework
class Validator {
    public static void validate(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        for (Field field : fields) {
            field.setAccessible(true);
            
            try {
                Object value = field.get(obj);
                
                // Check @NotNull
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (value == null) {
                        System.out.println("Validation failed: " + 
                            field.getName() + " cannot be null");
                    }
                }
                
                // Check @Range
                if (field.isAnnotationPresent(Range.class)) {
                    Range range = field.getAnnotation(Range.class);
                    if (value instanceof Integer) {
                        int intValue = (Integer) value;
                        if (intValue < range.min() || intValue > range.max()) {
                            System.out.println("Validation failed: " + 
                                field.getName() + " must be between " + 
                                range.min() + " and " + range.max());
                        }
                    }
                }
                
                // Check @Email (simplified)
                if (field.isAnnotationPresent(Email.class)) {
                    if (value instanceof String) {
                        String strValue = (String) value;
                        if (!strValue.contains("@")) {
                            System.out.println("Validation failed: " + 
                                field.getName() + " must be a valid email");
                        }
                    }
                }
                
            } catch (IllegalAccessException e) {
                System.out.println("Cannot access field: " + field.getName());
            }
        }
    }
}

// Test the validation framework
class ValidationTest {
    public static void main(String[] args) {
        // Valid user
        User validUser = new User("John Doe", 25, "john@example.com");
        System.out.println("Validating valid user:");
        Validator.validate(validUser);
        
        System.out.println();
        
        // Invalid user
        User invalidUser = new User(null, 150, "invalid-email");
        System.out.println("Validating invalid user:");
        Validator.validate(invalidUser);
    }
}
```

**Output:**
```
Validating valid user:

Validating invalid user:
Validation failed: name cannot be null
Validation failed: age must be between 18 and 120
Validation failed: emailAddress must be a valid email
```

### Summary

This chapter covered three powerful Java features:

#### **Enumerations:**
- Type-safe constants that define new data types
- Can have constructors, methods, and instance variables
- Inherit from `java.lang.Enum`
- Support built-in methods like `values()`, `valueOf()`, `ordinal()`
- More powerful and safer than traditional constant approaches

#### **Type Wrappers and Autoboxing:**
- Wrapper classes for all primitive types (Integer, Double, Boolean, etc.)
- Autoboxing: automatic primitive-to-wrapper conversion
- Auto-unboxing: automatic wrapper-to-primitive conversion
- Simplifies code but should be used judiciously for performance
- Essential for generics and Collections Framework

#### **Annotations:**
- Metadata that doesn't change program semantics
- Can be processed by tools during development and deployment
- Support different retention policies (SOURCE, CLASS, RUNTIME)
- Accessible at runtime through reflection
- Support default values, marker annotations, single-member annotations
- Type annotations (JDK 8+) can annotate type uses
- Repeatable annotations (JDK 8+) allow multiple instances
- Java provides many built-in annotations for various purposes

These features represent modern Java programming practices and are essential for writing robust, maintainable code. They provide type safety, reduce boilerplate code, and enable powerful frameworks and tools.
