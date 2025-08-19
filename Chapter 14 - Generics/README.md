# Complete Java Generics Guide

## Table of Contents
1. [Introduction to Generics](#introduction)
2. [What Are Generics?](#what-are-generics)
3. [Simple Generic Examples](#simple-examples)
4. [Generic Classes with Multiple Parameters](#multiple-parameters)
5. [Bounded Types](#bounded-types)
6. [Wildcard Arguments](#wildcard-arguments)
7. [Generic Methods](#generic-methods)
8. [Generic Constructors](#generic-constructors)
9. [Generic Interfaces](#generic-interfaces)
10. [Raw Types and Legacy Code](#raw-types)
11. [Generic Class Hierarchies](#hierarchies)
12. [Type Inference](#type-inference)
13. [Erasure and Implementation](#erasure)
14. [Generic Restrictions](#restrictions)

---

## 1. Introduction to Generics {#introduction}

### What Generics Brought to Java
- **Introduced**: JDK 5 (2004)
- **Impact**: Fundamental change to Java programming
- **Benefits**:
  - Added new syntactical elements
  - Changed core API classes and methods
  - Became integral part of Java programming

### Core Concept
Generics enable creation of classes, interfaces, and methods that work in a **type-safe manner** with various data types. The same algorithm can be applied to different data types without additional effort.

### Example: Collections Framework Impact
Before generics, collections could store any `Object` but lacked type safety. Generics added complete type safety to collection classes.

---

## 2. What Are Generics? {#what-are-generics}

### Definition
**Generics = Parameterized Types**

Parameterized types allow you to create classes, interfaces, and methods where the data type is specified as a parameter.

### Key Advantages Over Object-Based Approach

#### Before Generics (Problems):
```java
// Using Object - NOT type-safe
class NonGenericContainer {
    Object data;
    
    NonGenericContainer(Object data) {
        this.data = data;
    }
    
    Object getData() {
        return data; // Requires casting
    }
}

// Usage problems:
NonGenericContainer container = new NonGenericContainer("Hello");
String str = (String) container.getData(); // Manual cast required
```

**Problems:**
1. **Manual casting required**
2. **No compile-time type checking**
3. **Runtime errors possible**
4. **Type mismatches detected only at runtime**

#### With Generics (Solutions):
```java
// Generic class - Type-safe
class GenericContainer<T> {
    T data;
    
    GenericContainer(T data) {
        this.data = data;
    }
    
    T getData() {
        return data; // No casting needed
    }
}

// Usage benefits:
GenericContainer<String> container = new GenericContainer<>("Hello");
String str = container.getData(); // No cast needed!
```

**Benefits:**
1. **Automatic type safety**
2. **No manual casting**
3. **Compile-time error detection**
4. **Code reusability**

---

## 3. Simple Generic Examples {#simple-examples}

### Basic Generic Class Example

```java
// A simple generic class
class Gen<T> {
    T ob; // Object of type T
    
    // Constructor takes object of type T
    Gen(T o) {
        ob = o;
    }
    
    // Return object of type T
    T getOb() {
        return ob;
    }
    
    // Show the type of T
    void showType() {
        System.out.println("Type of T is " + 
            ob.getClass().getName());
    }
}

// Demonstration class
class GenDemo {
    public static void main(String[] args) {
        // Create Gen object for Integers
        Gen<Integer> iOb = new Gen<Integer>(88);
        iOb.showType();
        
        // Get value without casting
        int v = iOb.getOb(); // Auto-unboxing
        System.out.println("value: " + v);
        
        // Create Gen object for Strings
        Gen<String> strOb = new Gen<String>("Generics Test");
        strOb.showType();
        
        // Get value without casting
        String str = strOb.getOb();
        System.out.println("value: " + str);
    }
}
```

**Output:**
```
Type of T is java.lang.Integer
value: 88
Type of T is java.lang.String
value: Generics Test
```

### Key Points Explained

#### 1. Generic Class Declaration
```java
class Gen<T> {
```
- `T` is a **type parameter**
- Acts as placeholder for actual type
- Contained within angle brackets `< >`
- `T` is traditional name (could be any valid identifier)

#### 2. Type Parameter Usage
```java
T ob; // Declare object of type T
```
- `T` used wherever type parameter needed
- Will be replaced by actual type when object created

#### 3. Constructor with Type Parameter
```java
Gen(T o) {
    ob = o;
}
```
- Parameter `o` is of type `T`
- Both `o` and `ob` will be same actual type

#### 4. Method Return Type
```java
T getOb() {
    return ob;
}
```
- Return type is `T`
- Compatible with `ob` since both are type `T`

#### 5. Object Creation
```java
Gen<Integer> iOb = new Gen<Integer>(88);
```
- `Integer` is **type argument** passed to type parameter `T`
- Creates version where all `T` references become `Integer`

### Type Safety Demonstration

#### What Doesn't Work (Compile-time Error):
```java
Gen<Integer> iOb = new Gen<Integer>(88);
Gen<String> strOb = new Gen<String>("Test");

// This causes compile-time error:
iOb = strOb; // Error! Different parameterized types
```

### Reference Types Only
```java
// This is ILLEGAL:
Gen<int> intOb = new Gen<int>(53); // Error! Primitive types not allowed

// This is LEGAL:
Gen<Integer> intOb = new Gen<Integer>(53); // Use wrapper class
```

**Important**: Generics work only with reference types, not primitive types. Use wrapper classes for primitives.

---

## 4. Generic Classes with Multiple Parameters {#multiple-parameters}

### Two Type Parameters Example

```java
// Generic class with two type parameters
class TwoGen<T, V> {
    T ob1;
    V ob2;
    
    // Constructor takes objects of type T and V
    TwoGen(T o1, V o2) {
        ob1 = o1;
        ob2 = o2;
    }
    
    // Show types of T and V
    void showTypes() {
        System.out.println("Type of T is " + 
            ob1.getClass().getName());
        System.out.println("Type of V is " + 
            ob2.getClass().getName());
    }
    
    T getOb1() {
        return ob1;
    }
    
    V getOb2() {
        return ob2;
    }
}

// Demonstration
class SimpGen {
    public static void main(String[] args) {
        // Create object with Integer and String types
        TwoGen<Integer, String> tgObj = 
            new TwoGen<Integer, String>(88, "Generics");
        
        tgObj.showTypes();
        
        // Obtain values
        int v = tgObj.getOb1();
        String str = tgObj.getOb2();
        
        System.out.println("value: " + v);
        System.out.println("value: " + str);
    }
}
```

**Output:**
```
Type of T is java.lang.Integer
Type of V is java.lang.String
value: 88
value: Generics
```

### Key Points for Multiple Type Parameters

#### 1. Declaration Syntax
```java
class TwoGen<T, V> {
```
- Multiple type parameters separated by commas
- Can have as many type parameters as needed

#### 2. Object Creation
```java
TwoGen<Integer, String> tgObj = 
    new TwoGen<Integer, String>(88, "Generics");
```
- Must provide type argument for each type parameter
- Order matters: first argument for T, second for V

#### 3. Same Types Allowed
```java
TwoGen<String, String> x = new TwoGen<String, String>("A", "B");
```
- Both type parameters can be same type
- If always same, consider using single parameter

### General Form of Generic Class

#### Declaration:
```java
class class-name<type-param-list> {
    // class body
}
```

#### Instance Creation:
```java
class-name<type-arg-list> var-name = 
    new class-name<type-arg-list>(cons-arg-list);
```

---

## 5. Bounded Types {#bounded-types}

### The Problem: Unrestricted Type Parameters

```java
// This won't work - ERROR!
class Stats<T> {
    T[] nums;
    
    Stats(T[] o) {
        nums = o;
    }
    
    double average() {
        double sum = 0.0;
        for(int i=0; i < nums.length; i++)
            sum += nums[i].doubleValue(); // ERROR! doubleValue() unknown
        return sum / nums.length;
    }
}
```

**Problem**: Compiler doesn't know that `T` will be numeric type, so `doubleValue()` method is not available.

### Solution: Bounded Types

```java
// Bounded type parameter - T must extend Number
class Stats<T extends Number> {
    T[] nums;
    
    Stats(T[] o) {
        nums = o;
    }
    
    // Now this works because Number has doubleValue()
    double average() {
        double sum = 0.0;
        for(int i=0; i < nums.length; i++)
            sum += nums[i].doubleValue();
        return sum / nums.length;
    }
}

// Demonstration
class BoundsDemo {
    public static void main(String[] args) {
        // Works with Integer (extends Number)
        Integer[] inums = { 1, 2, 3, 4, 5 };
        Stats<Integer> iob = new Stats<Integer>(inums);
        System.out.println("Average: " + iob.average());
        
        // Works with Double (extends Number)
        Double[] dnums = { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Stats<Double> dob = new Stats<Double>(dnums);
        System.out.println("Average: " + dob.average());
        
        // This won't compile - String doesn't extend Number
        // String[] strs = { "1", "2", "3", "4", "5" };
        // Stats<String> strob = new Stats<String>(strs); // ERROR!
    }
}
```

### Bounded Type Syntax

#### Basic Upper Bound:
```java
<T extends superclass>
```
- T can only be `superclass` or its subclasses
- `extends` used for both classes and interfaces
- Creates **inclusive upper bound**

#### Multiple Bounds:
```java
class Gen<T extends MyClass & MyInterface> {
    // T must extend MyClass AND implement MyInterface
}
```
- Use `&` to separate multiple bounds
- Class type must come first
- Can have one class and multiple interfaces

#### Example with Interface Bound:
```java
class NumberProcessor<T extends Number & Comparable<T>> {
    // T must extend Number AND implement Comparable
}
```

### Benefits of Bounded Types

1. **Method Access**: Can call methods of bound type
2. **Type Safety**: Prevents incompatible types at compile-time
3. **Code Clarity**: Makes intent explicit

---

## 6. Wildcard Arguments {#wildcard-arguments}

### The Problem: Type Compatibility

```java
// This approach is too restrictive
class Stats<T extends Number> {
    T[] nums;
    
    // This only works with same type
    boolean isSameAvg(Stats<T> ob) {
        if(average() == ob.average())
            return true;
        return false;
    }
}
```

**Problem**: `Stats<Integer>` object can't compare with `Stats<Double>` object.

### Solution: Wildcard Arguments

```java
class Stats<T extends Number> {
    T[] nums;
    
    Stats(T[] o) {
        nums = o;
    }
    
    double average() {
        double sum = 0.0;
        for(int i=0; i < nums.length; i++)
            sum += nums[i].doubleValue();
        return sum / nums.length;
    }
    
    // Wildcard allows any Stats object
    boolean isSameAvg(Stats<?> ob) {
        if(average() == ob.average())
            return true;
        return false;
    }
}

// Demonstration
class WildcardDemo {
    public static void main(String[] args) {
        Integer[] inums = { 1, 2, 3, 4, 5 };
        Stats<Integer> iob = new Stats<Integer>(inums);
        
        Double[] dnums = { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Stats<Double> dob = new Stats<Double>(dnums);
        
        Float[] fnums = { 1.0F, 2.0F, 3.0F, 4.0F, 5.0F };
        Stats<Float> fob = new Stats<Float>(fnums);
        
        // Now these comparisons work!
        if(iob.isSameAvg(dob))
            System.out.println("Integer and Double averages are same");
        
        if(iob.isSameAvg(fob))
            System.out.println("Integer and Float averages are same");
    }
}
```

### Wildcard Syntax

#### Unbounded Wildcard:
```java
Stats<?> ob  // Matches any Stats object
```

### Bounded Wildcards

#### Upper Bounded Wildcard:
```java
<? extends UpperBound>
```

#### Lower Bounded Wildcard:
```java
<? super LowerBound>
```

### Bounded Wildcard Example

```java
// Coordinate hierarchy
class TwoD {
    int x, y;
    TwoD(int a, int b) { x = a; y = b; }
}

class ThreeD extends TwoD {
    int z;
    ThreeD(int a, int b, int c) { super(a, b); z = c; }
}

class FourD extends ThreeD {
    int t;
    FourD(int a, int b, int c, int d) { super(a, b, c); t = d; }
}

class Coords<T extends TwoD> {
    T[] coords;
    Coords(T[] o) { coords = o; }
}

class BoundedWildcard {
    // Works with any Coords object
    static void showXY(Coords<?> c) {
        System.out.println("X Y Coordinates:");
        for(int i=0; i < c.coords.length; i++)
            System.out.println(c.coords[i].x + " " + c.coords[i].y);
    }
    
    // Only works with ThreeD or FourD coordinates
    static void showXYZ(Coords<? extends ThreeD> c) {
        System.out.println("X Y Z Coordinates:");
        for(int i=0; i < c.coords.length; i++)
            System.out.println(c.coords[i].x + " " + 
                             c.coords[i].y + " " + c.coords[i].z);
    }
    
    // Only works with FourD coordinates
    static void showAll(Coords<? extends FourD> c) {
        System.out.println("X Y Z T Coordinates:");
        for(int i=0; i < c.coords.length; i++)
            System.out.println(c.coords[i].x + " " + c.coords[i].y + " " + 
                             c.coords[i].z + " " + c.coords[i].t);
    }
}
```

### Wildcard Usage Rules

1. **Upper Bounded** (`? extends Type`):
   - Can read from the structure
   - Cannot add elements (except null)
   - Use when you need to consume/read data

2. **Lower Bounded** (`? super Type`):
   - Can add elements of Type or its subtypes
   - Reading returns Object type
   - Use when you need to produce/write data

3. **Unbounded** (`?`):
   - Most flexible
   - Can only read as Object
   - Cannot add elements (except null)

---

## 7. Generic Methods {#generic-methods}

### Creating Generic Methods

Generic methods can exist in both generic and non-generic classes.

```java
class GenMethDemo {
    // Generic method with two type parameters
    static <T extends Comparable<T>, V extends T> 
    boolean isIn(T x, V[] y) {
        for(int i=0; i < y.length; i++)
            if(x.equals(y[i])) return true;
        return false;
    }
    
    public static void main(String[] args) {
        // Use with Integers
        Integer[] nums = { 1, 2, 3, 4, 5 };
        if(isIn(2, nums))
            System.out.println("2 is in nums");
        
        // Use with Strings
        String[] strs = { "one", "two", "three", "four", "five" };
        if(isIn("two", strs))
            System.out.println("two is in strs");
        
        // This won't compile - type mismatch
        // if(isIn("two", nums)) // ERROR!
    }
}
```

### Generic Method Syntax

```java
<type-param-list> return-type method-name(param-list) {
    // method body
}
```

### Key Points for Generic Methods

1. **Type Parameter Declaration**:
   - Comes BEFORE return type
   - `static <T extends Comparable<T>, V extends T> boolean isIn(...)`

2. **Type Inference**:
   - Usually automatic: `isIn(2, nums)`
   - Can be explicit: `GenMethDemo.<Integer, Integer>isIn(2, nums)`

3. **Bounds in Generic Methods**:
   - `T extends Comparable<T>`: T must be comparable
   - `V extends T`: V must be T or subtype of T

4. **Static and Non-static**:
   - Generic methods can be static or non-static
   - Static generic methods define their own type parameters

### More Generic Method Examples

```java
class Utility {
    // Generic method to swap array elements
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // Generic method to find maximum
    public static <T extends Comparable<T>> T max(T x, T y) {
        return x.compareTo(y) > 0 ? x : y;
    }
    
    // Generic method with multiple bounds
    public static <T extends Number & Comparable<T>> 
    T findMax(T[] array) {
        T max = array[0];
        for(int i = 1; i < array.length; i++) {
            if(array[i].compareTo(max) > 0)
                max = array[i];
        }
        return max;
    }
}
```

---

## 8. Generic Constructors {#generic-constructors}

Even non-generic classes can have generic constructors.

```java
class GenCons {
    private double val;
    
    // Generic constructor
    <T extends Number> GenCons(T arg) {
        val = arg.doubleValue();
    }
    
    void showVal() {
        System.out.println("val: " + val);
    }
}

class GenConsDemo {
    public static void main(String[] args) {
        // Can use any Number subtype
        GenCons test = new GenCons(100);        // Integer
        GenCons test2 = new GenCons(123.5F);    // Float
        GenCons test3 = new GenCons(99.99);     // Double
        
        test.showVal();   // val: 100.0
        test2.showVal();  // val: 123.5
        test3.showVal();  // val: 99.99
    }
}
```

### Key Points for Generic Constructors

1. **Syntax**: Type parameters declared before constructor name
2. **Flexibility**: Accept different but related types
3. **Type Safety**: Bounds ensure type compatibility
4. **Usage**: Even in non-generic classes

---

## 9. Generic Interfaces {#generic-interfaces}

### Creating Generic Interfaces

```java
// Generic interface
interface MinMax<T extends Comparable<T>> {
    T min();
    T max();
}

// Implementation
class MyClass<T extends Comparable<T>> implements MinMax<T> {
    T[] vals;
    
    MyClass(T[] o) { vals = o; }
    
    public T min() {
        T v = vals[0];
        for(int i=1; i < vals.length; i++)
            if(vals[i].compareTo(v) < 0) v = vals[i];
        return v;
    }
    
    public T max() {
        T v = vals[0];
        for(int i=1; i < vals.length; i++)
            if(vals[i].compareTo(v) > 0) v = vals[i];
        return v;
    }
}

// Usage
class GenIFDemo {
    public static void main(String[] args) {
        Integer[] inums = {3, 6, 2, 8, 6};
        Character[] chs = {'b', 'r', 'p', 'w'};
        
        MyClass<Integer> iob = new MyClass<Integer>(inums);
        MyClass<Character> cob = new MyClass<Character>(chs);
        
        System.out.println("Max value in inums: " + iob.max());
        System.out.println("Min value in inums: " + iob.min());
        System.out.println("Max value in chs: " + cob.max());
        System.out.println("Min value in chs: " + cob.min());
    }
}
```

### Generic Interface Rules

#### 1. Interface Declaration:
```java
interface interface-name<type-param-list> {
    // interface methods
}
```

#### 2. Implementation Requirements:
- Implementing class must be generic (pass type parameter)
- OR implement with specific type

```java
// Generic implementation
class MyClass<T extends Comparable<T>> implements MinMax<T> { }

// Specific implementation
class IntegerClass implements MinMax<Integer> { }

// This is WRONG - T is unknown
// class MyClass implements MinMax<T> { } // ERROR!
```

#### 3. Type Parameter Passing:
```java
// Correct - pass through type parameter
class MyClass<T extends Comparable<T>> implements MinMax<T>

// Wrong - don't redefine bounds in implements clause
// class MyClass<T extends Comparable<T>> 
//     implements MinMax<T extends Comparable<T>> // ERROR!
```

### Benefits of Generic Interfaces

1. **Type Safety**: Compile-time type checking
2. **Reusability**: Same interface for different types
3. **Constraints**: Bounded types enforce requirements
4. **Clarity**: Intent is explicit in declaration

---

## 10. Raw Types and Legacy Code {#raw-types}

### Understanding Raw Types

Raw types provide compatibility with pre-JDK 5 code.

```java
class Gen<T> {
    T ob;
    
    Gen(T o) { ob = o; }
    
    T getOb() { return ob; }
}

class RawDemo {
    public static void main(String[] args) {
        // Generic objects
        Gen<Integer> iOb = new Gen<Integer>(88);
        Gen<String> strOb = new Gen<String>("Generics Test");
        
        // Raw type object - NO type parameter
        Gen raw = new Gen(Double.valueOf(98.6));
        
        // Must cast because type is unknown
        double d = (Double) raw.getOb();
        System.out.println("value: " + d);
        
        // Dangerous operations (compile but may fail at runtime)
        strOb = raw;    // OK, but potentially wrong
        // String str = strOb.getOb(); // Runtime error!
        
        raw = iOb;      // OK, but potentially wrong
        // d = (Double) raw.getOb();   // Runtime error!
    }
}
```

### Problems with Raw Types

1. **Loss of Type Safety**:
   ```java
   Gen raw = new Gen(98.6);
   int i = (Integer) raw.getOb(); // Runtime ClassCastException!
   ```

2. **Unchecked Warnings**:
   ```java
   Gen raw = new Gen(98.6);        // Warning
   Gen<String> strOb = raw;        // Warning
   ```

3. **Runtime Errors**:
   - Type mismatches caught only at runtime
   - Can corrupt type safety of generic variables

### When to Use Raw Types

- **Legacy Code Integration**: Only when mixing old and new code
- **Transitional Purposes**: Temporary during code migration
- **Avoid for New Code**: Use proper generics instead

### Better Alternatives

Instead of raw types, use:

```java
// Instead of raw Gen
Gen<?> wildcard = new Gen<String>("Safe");

// Instead of raw List  
List<?> list = new ArrayList<String>();

// Specific parameterization when type is known
Gen<Object> objGen = new Gen<Object>(someObject);
```

---

## 11. Generic Class Hierarchies {#hierarchies}

### Generic Superclass

```java
// Generic superclass
class Gen<T> {
    T ob;
    
    Gen(T o) { ob = o; }
    
    T getOb() { return ob; }
}

// Generic subclass - must pass type parameter up
class Gen2<T> extends Gen<T> {
    Gen2(T o) {
        super(o);
    }
}

// Usage
class HierDemo {
    public static void main(String[] args) {
        Gen2<Integer> num = new Gen2<Integer>(100);
        System.out.println(num.getOb());
    }
}
```

### Subclass with Additional Type Parameters

```java
class Gen<T> {
    T ob;
    Gen(T o) { ob = o; }
    T getOb() { return ob; }
}

// Subclass adds its own type parameter
class Gen2<T, V> extends Gen<T> {
    V ob2;
    
    Gen2(T o, V o2) {
        super(o);
        ob2 = o2;
    }
    
    V getOb2() { return ob2; }
}

// Usage
class HierDemo {
    public static void main(String[] args) {
        Gen2<String, Integer> x = 
            new Gen2<String, Integer>("Value is: ", 99);
        
        System.out.print(x.getOb());     // String from superclass
        System.out.println(x.getOb2());  // Integer from subclass
    }
}
```

### Non-Generic Superclass with Generic Subclass

```java
// Non-generic superclass
class NonGen {
    int num;
    
    NonGen(int i) { num = i; }
    
    int getnum() { return num; }
}

// Generic subclass
class Gen<T> extends NonGen {
    T ob;
    
    Gen(T o, int i) {
        super(i);
        ob = o;
    }
    
    T getOb() { return ob; }
}

// Usage
class HierDemo2 {
    public static void main(String[] args) {
        Gen<String> w = new Gen<String>("Hello", 47);
        System.out.println(w.getOb() + " " + w.getnum());
    }
}
```

### Runtime Type Comparisons

```java
class HierDemo3 {
    public static void main(String[] args) {
        Gen<Integer> iOb = new Gen<Integer>(88);
        Gen2<Integer> iOb2 = new Gen2<Integer>(99);
        Gen2<String> strOb2 = new Gen2<String>("Generics Test");
        
        // Check type hierarchy with wildcards
        if(iOb2 instanceof Gen2<?>)
            System.out.println("iOb2 is instance of Gen2");
        
        if(iOb2 instanceof Gen<?>)
            System.out.println("iOb2 is instance of Gen");
        
        // This fails - iOb is not a Gen2
        if(iOb instanceof Gen2<?>) // false
            System.out.println("iOb is instance of Gen2");
        
        if(iOb instanceof Gen<?>) // true
            System.out.println("iOb is instance of Gen");
    }
}
```

### Method Overriding in Generic Classes

```java
class Gen<T> {
    T ob;
    
    Gen(T o) { ob = o; }
    
    T getOb() {
        System.out.print("Gen's getOb(): ");
        return ob;
    }
}

class Gen2<T> extends Gen<T> {
    Gen2(T o) { super(o); }
    
    // Override generic method
    T getOb() {
        System.out.print("Gen2's getOb(): ");
        return ob;
    }
}

// Usage shows overriding works normally
class OverrideDemo {
    public static void main(String[] args) {
        Gen<Integer> iOb = new Gen<Integer>(88);
        Gen2<Integer> iOb2 = new Gen2<Integer>(99);
        
        System.out.println(iOb.getOb());   // Calls Gen's version
        System.out.println(iOb2.getOb());  // Calls Gen2's version
    }
}
```

---

## 12. Type Inference {#type-inference}

### Diamond Operator (JDK 7+)

Before JDK 7:
```java
MyClass<Integer, String> mcOb = 
    new MyClass<Integer, String>(98, "A String");
```

JDK 7+ with Diamond Operator:
```java
MyClass<Integer, String> mcOb = new MyClass<>(98, "A String");
```

### Type Inference Examples

```java
class MyClass<T, V> {
    T ob1;
    V ob2;
    
    MyClass(T o1, V o2) {
        ob1 = o1;
        ob2 = o2;
    }
    
    boolean isSame(MyClass<T, V> o) {
        return (ob1 == o.ob1 && ob2 == o.ob2);
    }
}

class TypeInferenceDemo {
    public static void main(String[] args) {
        // Diamond operator
        MyClass<Integer, String> mc1 = new MyClass<>(98, "String");
        
        // Type inference in method calls
        if(mc1.isSame(new MyClass<>(1, "test"))) {
            System.out.println("Same");
        }
    }
}
```

### Local Variable Type Inference (JDK 10+)

```java
// Traditional way
MyClass<Integer, String> mcOb = 
    new MyClass<Integer, String>(98, "A String");

// With var keyword (JDK 10+)
var mcOb = new MyClass<Integer, String>(98, "A String");
```

The `var` keyword infers the type from the initializer, making declarations shorter while maintaining type safety.

### Benefits of Type Inference

1. **Shorter Code**: Less verbose declarations
2. **Maintains Type Safety**: Still compile-time checked
3. **Easier Maintenance**: Less duplication of type information
4. **Better Readability**: Focus on logic, not type declarations

---

## 13. Erasure and Implementation {#erasure}

### What is Erasure?

**Erasure** is how Java implements generics while maintaining backward compatibility.

#### Process:
1. **Compile Time**: Generic type information exists and enforced
2. **Runtime**: All generic type information removed (erased)
3. **Result**: Only one version of class exists at runtime

### Erasure Examples

#### Before Erasure (Source Code):
```java
class Gen<T> {
    T ob;
    
    Gen(T o) { ob = o; }
    
    T getOb() { return ob; }
}

// Usage
Gen<String> strGen = new Gen<String>("Hello");
String s = strGen.getOb(); // No cast in source
```

#### After Erasure (Runtime):
```java
class Gen {
    Object ob; // T becomes Object
    
    Gen(Object o) { ob = o; }
    
    Object getOb() { return ob; }
}

// Usage becomes
Gen strGen = new Gen("Hello");
String s = (String) strGen.getOb(); // Cast inserted by compiler
```

### Erasure with Bounded Types

#### Source Code:
```java
class Stats<T extends Number> {
    T[] nums;
    
    double average() {
        double sum = 0.0;
        for(int i = 0; i < nums.length; i++)
            sum += nums[i].doubleValue(); // Method available because T extends Number
        return sum / nums.length;
    }
}
```

#### After Erasure:
```java
class Stats {
    Number[] nums; // T becomes Number (the bound)
    
    double average() {
        double sum = 0.0;
        for(int i = 0; i < nums.length; i++)
            sum += nums[i].doubleValue(); // Still valid - Number has doubleValue()
        return sum / nums.length;
    }
}
```

### Bridge Methods

Sometimes the compiler creates **bridge methods** to handle erasure conflicts.

```java
class Gen<T> {
    T ob;
    Gen(T o) { ob = o; }
    T getOb() { return ob; }
}

// Subclass with specific type
class Gen2 extends Gen<String> {
    Gen2(String o) { super(o); }
    
    // Override with specific return type
    String getOb() {
        System.out.print("You called String getOb(): ");
        return ob;
    }
}
```

#### After Erasure:
The compiler creates a bridge method:

```java
class Gen2 extends Gen {
    String getOb() {
        System.out.print("You called String getOb(): ");
        return ob;
    }
    
    // Bridge method created by compiler
    Object getOb() {
        return this.getOb(); // Calls String version
    }
}
```

### Key Points About Erasure

1. **No Generic Info at Runtime**: Cannot determine `T` at runtime
2. **Type Safety**: Enforced at compile time only
3. **Backward Compatibility**: Pre-generic code works with generic code
4. **Performance**: No runtime overhead
5. **Single Class File**: One `.class` file regardless of type parameters

---

## 14. Generic Restrictions {#restrictions}

### 1. Cannot Instantiate Type Parameters

```java
// This is ILLEGAL
class Gen<T> {
    T ob;
    
    Gen() {
        ob = new T(); // ERROR! Cannot instantiate T
    }
}
```

**Reason**: Compiler doesn't know what type of object to create.

**Solution**: Pass object through constructor or factory method:
```java
class Gen<T> {
    T ob;
    
    Gen(T o) {
        ob = o; // OK - object passed from outside
    }
}
```

### 2. Static Member Restrictions

```java
// These are ILLEGAL
class Wrong<T> {
    static T ob;           // ERROR! Static variable of type T
    
    static T getOb() {     // ERROR! Static method using T
        return ob;
    }
}
```

**Reason**: Static members belong to class, not instance. Type parameter `T` is instance-specific.

**Legal Alternative**: Static generic methods with their own type parameters:
```java
class Correct<T> {
    // This is OK - method defines its own type parameter
    static <U> void staticMethod(U param) {
        System.out.println(param);
    }
}
```

### 3. Generic Array Restrictions

#### Cannot Create Array of Type Parameter:
```java
class Gen<T extends Number> {
    T[] vals; // OK to declare
    
    Gen(T[] nums) {
        // vals = new T[10];    // ERROR! Cannot create array of T
        vals = nums;            // OK - assign existing array
    }
}
```

#### Cannot Create Array of Generic Type:
```java
class GenArrays {
    public static void main(String[] args) {
        // This is ILLEGAL
        // Gen<Integer>[] gens = new Gen<Integer>[10]; // ERROR!
        
        // This is OK - using wildcard
        Gen<?>[] gens = new Gen<?>[10]; // OK
    }
}
```

**Workarounds**:
```java
// Use ArrayList instead of array
List<T> list = new ArrayList<T>();

// Use Object array and cast (not recommended)
T[] array = (T[]) new Object[size]; // Unchecked warning

// Pass array from outside
class Gen<T> {
    T[] array;
    Gen(T[] arr) { array = arr; } // OK
}
```

### 4. Generic Exception Restriction

```java
// This is ILLEGAL
class MyException<T> extends Throwable { // ERROR!
    T detail;
}
```

**Reason**: Exception handling mechanism needs to know exact types at runtime, but generics are erased.

**Legal Alternative**: Generic methods in exception classes:
```java
class MyException extends Exception {
    public <T> void setDetail(T detail) {
        // Generic method is OK
    }
}
```

### 5. Cannot Use instanceof with Generic Types

```java
// These are ILLEGAL
Gen<Integer> iOb = new Gen<Integer>(88);

// if(iOb instanceof Gen<Integer>) // ERROR!
// if(iOb instanceof Gen<T>)       // ERROR!
```

**Legal Alternative**: Use wildcards:
```java
if(iOb instanceof Gen<?>) // OK
    System.out.println("iOb is some type of Gen");
```

### 6. Ambiguity Errors

```java
// This creates ambiguity
class MyGenClass<T, V> {
    void set(T o) { }    // After erasure: set(Object o)
    void set(V o) { }    // After erasure: set(Object o) - CONFLICT!
}
```

**Problem**: Both methods erase to same signature.

**Solutions**:
```java
// Solution 1: Use different method names
class MyGenClass<T, V> {
    void setFirst(T o) { }
    void setSecond(V o) { }
}

// Solution 2: Add bounds to differentiate
class MyGenClass<T, V extends Number> {
    void set(T o) { }      // Erases to: set(Object o)
    void set(V o) { }      // Erases to: set(Number o) - OK!
}
```

---

## Summary and Best Practices

### Key Benefits of Generics

1. **Type Safety**: Compile-time type checking prevents ClassCastException
2. **Elimination of Casts**: No manual casting required
3. **Code Reusability**: Same code works with different types
4. **Better Performance**: No boxing/unboxing overhead for primitives wrappers
5. **Clearer Code**: Intent is explicit in type declarations

### Best Practices

#### 1. Use Meaningful Type Parameter Names
```java
// Good
class Cache<Key, Value> { }
class Container<Element> { }

// Traditional single letters
class Gen<T> { }          // Type
class Map<K, V> { }       // Key, Value
class List<E> { }         // Element
```

#### 2. Use Bounded Types When Appropriate
```java
// Good - constrains type and enables method calls
class NumberProcessor<T extends Number> {
    double process(T num) {
        return num.doubleValue(); // Available because T extends Number
    }
}
```

#### 3. Prefer Wildcards for Flexibility
```java
// Good - accepts any list
void printList(List<?> list) {
    for(Object item : list) {
        System.out.println(item);
    }
}

// Less flexible - only accepts List<Object>
void printList(List<Object> list) { } // Too restrictive
```

#### 4. Use Diamond Operator (JDK 7+)
```java
// Good - concise and clear
List<String> list = new ArrayList<>();

// Verbose (but still valid)
List<String> list = new ArrayList<String>();
```

#### 5. Avoid Raw Types
```java
// Bad - loses type safety
List list = new ArrayList(); // Raw type

// Good - type safe
List<String> list = new ArrayList<>();
```

#### 6. Use Generic Methods When Appropriate
```java
// Good - generic method in utility class
public static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}
```

#### 7. Consider PECS (Producer Extends Consumer Super)
```java
// Producer - use extends
void processProducer(List<? extends Number> producer) {
    Number n = producer.get(0); // Can read
    // producer.add(1); // Cannot add - compile error
}

// Consumer - use super
void processConsumer(List<? super Integer> consumer) {
    consumer.add(42); // Can add Integer
    // Integer i = consumer.get(0); // Cannot read as Integer
}
```

### Common Pitfalls to Avoid

1. **Mixing Raw Types**: Don't mix generic and raw types
2. **Ignoring Unchecked Warnings**: Address compiler warnings
3. **Over-complicated Bounds**: Keep bounds simple when possible
4. **Premature Generification**: Don't make everything generic unnecessarily
5. **Forgetting Wildcards**: Use wildcards for API flexibility

### When to Use Generics

- **Collections**: Always use parameterized collections
- **APIs**: When methods work with multiple types
- **Utility Classes**: For reusable algorithms
- **Type Safety**: When you want compile-time type checking
- **Framework Development**: For flexible, reusable components

Generics are a powerful feature that, when used correctly, make Java code more type-safe, readable, and maintainable. Understanding the concepts, syntax, and restrictions is essential for effective Java programming.
