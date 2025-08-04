# Chapter 2: An Overview of Java - Complete Learning Guide

## Table of Contents
1. [Object-Oriented Programming Fundamentals](#oop-fundamentals)
2. [The Three OOP Principles](#three-oop-principles)
3. [Your First Java Programs](#first-java-programs)
4. [Control Statements](#control-statements)
5. [Code Blocks](#code-blocks)
6. [Java Language Elements](#language-elements)
7. [Java Class Libraries](#class-libraries)

---

## Object-Oriented Programming Fundamentals {#oop-fundamentals}

### Introduction
Java is fundamentally object-oriented. Every Java program involves encapsulation, inheritance, and polymorphism. Understanding OOP principles is essential before writing even simple Java programs.

### Two Programming Paradigms

#### 1. Process-Oriented Model
- **Focus**: "What is happening"
- **Organization**: Around code/functions
- **Approach**: Code acting on data
- **Examples**: C, Pascal
- **Problem**: Becomes complex as programs grow larger

#### 2. Object-Oriented Model
- **Focus**: "Who is being affected"
- **Organization**: Around data (objects) and interfaces
- **Approach**: Data controlling access to code
- **Benefits**: Better organization, maintainability, scalability

### Abstraction
**Definition**: Managing complexity by focusing on essential features while hiding implementation details.

**Real-World Example**: 
- Think of a car as a single object with specific behavior
- You don't need to understand engine internals to drive
- Hierarchical classification: Car → Subsystems (steering, brakes) → Components

**Programming Application**:
- Transform process steps into object messages
- Each object has unique behavior
- Objects respond to messages telling them what to do

---

## The Three OOP Principles {#three-oop-principles}

### 1. Encapsulation

#### Definition
The mechanism that binds code and data together while protecting them from outside interference.

#### Real-World Analogy: Automatic Transmission
- **Encapsulates**: Hundreds of pieces of information about the engine
- **Interface**: Gear-shift lever (well-defined, unique)
- **Protection**: Can't affect transmission with turn signals or wipers
- **Benefit**: All manufacturers implement differently, but interface remains the same

#### Java Implementation
**Class**: The basis of encapsulation in Java
- Defines structure and behavior shared by objects
- Objects are instances of a class
- **Members**: Elements that constitute a class
  - **Member variables/Instance variables**: Data defined by the class
  - **Member methods/Methods**: Code that operates on the data

#### Access Control
- **Public**: Accessible by external code
- **Private**: Only accessible within the class
- **Purpose**: Ensures no improper actions take place

### 2. Inheritance

#### Definition
The process by which one object acquires properties of another object, supporting hierarchical classification.

#### Real-World Example: Animal Hierarchy
```
Animal (superclass)
├── Attributes: size, intelligence, skeletal system
├── Behaviors: eat, breathe, sleep
│
└── Mammal (subclass)
    ├── Inherits: All animal attributes
    ├── Adds: type of teeth, mammary glands
    │
    └── Dog (subclass)
        ├── Inherits: All animal and mammal attributes
        └── Adds: specific dog characteristics
```

#### Programming Benefits
- Objects need only define unique qualities
- Inherit general attributes from parent classes
- Enables linear complexity growth (not geometric)
- Reduces unpredictable interactions

#### Interaction with Encapsulation
- Subclasses inherit encapsulated attributes
- Can add specialized attributes
- Maintains encapsulation boundaries

### 3. Polymorphism

#### Definition
"Many forms" - One interface used for a general class of actions, with specific action determined by the situation.

#### Programming Example: Stack Operations
**Problem**: Need three types of stacks (integer, float, character)
- **Non-OOP approach**: Three different sets of routines with different names
- **OOP approach**: One set of stack routines sharing the same names

#### The Principle: "One Interface, Multiple Methods"
- Design generic interface for related activities
- Compiler selects specific method based on situation
- Programmer uses only the general interface

#### Real-World Analogy: Dog's Sense of Smell
- **Same interface**: Dog's nose
- **Different responses**:
  - Smells cat → bark and chase
  - Smells food → salivate and run to bowl
- **Polymorphic behavior**: Same sense, different actions based on data type

### How the Three Principles Work Together

#### Automobile Example
**Inheritance**: 
- All vehicles share common superclass (transmission concept)
- Drivers can operate different vehicle types (buses, sedans, minivans)

**Encapsulation**:
- Brake and gas pedals hide complexity
- Simple foot-operated interface
- Implementation details (engine type, brake style) don't affect interface

**Polymorphism**:
- Same interface for different implementations
- Antilock vs. traditional brakes
- Power vs. rack-and-pinion steering
- Different engines (4, 6, 8-cylinder, EV)
- Same controls: brake pedal, steering wheel, accelerator

---

## Your First Java Programs {#first-java-programs}

### Program 1: Basic Java Program

#### Source Code
```java
/*
 This is a simple Java program.
 Call this file "Example.java".
*/
class Example {
    // Your program begins with a call to main().
    public static void main(String[] args) {
        System.out.println("This is a simple Java program.");
    }
}
```

#### Output
```
This is a simple Java program.
```

#### Key Concepts

**File Naming Rules**:
- Source file must have `.java` extension
- Class name must match filename exactly
- Java is case-sensitive
- Convention makes maintenance easier

**Compilation Process**:
```bash
# Compile the program
C:\>javac Example.java

# Run the program
C:\>java Example
```

**What Happens**:
1. `javac` creates `Example.class` (bytecode)
2. `java` executes the bytecode in JVM
3. Searches for `.class` file with specified name

#### Program Analysis

**Comments**:
```java
/*
 This is a simple Java program.
 Call this file "Example.java".
*/
```
- **Multiline comment**: Begins with `/*`, ends with `*/`
- Content ignored by compiler
- Used for documentation and explanation

**Class Declaration**:
```java
class Example {
```
- **`class`**: Keyword declaring new class
- **`Example`**: Class name (identifier)
- **`{}`**: All class members go between braces
- **Important**: All Java program activity occurs within classes

**Single-Line Comment**:
```java
// Your program begins with a call to main().
```
- Begins with `//`, ends at line end
- Used for brief, line-by-line descriptions

**Main Method**:
```java
public static void main(String[] args) {
```

**Breakdown**:
- **`public`**: Access modifier - callable from outside class
- **`static`**: Can be called without instantiating class
- **`void`**: Returns no value
- **`main`**: Method name (entry point)
- **`String[] args`**: Parameter for command-line arguments

**Output Statement**:
```java
System.out.println("This is a simple Java program.");
```
- **`System`**: Predefined class providing system access
- **`out`**: Output stream connected to console
- **`println()`**: Method that outputs string followed by newline
- **`;`**: Statement terminator

### Program 2: Variables and Output

#### Source Code
```java
/*
 Here is another short example.
 Call this file "Example2.java".
*/
class Example2 {
    public static void main(String[] args) {
        int num; // this declares a variable called num
        
        num = 100; // this assigns num the value 100
        
        System.out.println("This is num: " + num);
        
        num = num * 2;
        
        System.out.print("The value of num * 2 is ");
        System.out.println(num);
    }
}
```

#### Output
```
This is num: 100
The value of num * 2 is 200
```

#### Key Concepts

**Variable Declaration**:
```java
int num; // this declares a variable called num
```
- **General form**: `type var-name;`
- **`int`**: Specifies integer type
- **`num`**: Variable name
- **Requirement**: Variables must be declared before use

**Multiple Variable Declaration**:
```java
int x, y; // Declares two variables
```

**Assignment**:
```java
num = 100; // this assigns num the value 100
```
- **`=`**: Assignment operator (single equal sign)
- Assigns value 100 to variable num

**String Concatenation**:
```java
System.out.println("This is num: " + num);
```
- **`+`**: Joins string with variable value
- `num` automatically converted to string
- Can join multiple items in single statement

**Arithmetic Operation**:
```java
num = num * 2;
```
- **`*`**: Multiplication operator
- Takes current value of num, multiplies by 2, stores back in num

**Output Methods**:
```java
System.out.print("The value of num * 2 is ");  // No newline
System.out.println(num);                        // With newline
```
- **`print()`**: Output without newline
- **`println()`**: Output with newline
- Both can output any built-in Java type

---

## Control Statements {#control-statements}

### The if Statement

#### Basic Syntax
```java
if(condition) statement;
```

#### How It Works
- **`condition`**: Boolean expression (evaluates to true/false)
- **`statement`**: Executed if condition is true
- If condition is false, statement is bypassed

#### Example Program
```java
/*
 Demonstrate the if.
 Call this file "IfSample.java".
*/
class IfSample {
    public static void main(String[] args) {
        int x, y;
        
        x = 10;
        y = 20;
        
        if(x < y) System.out.println("x is less than y");
        
        x = x * 2;
        if(x == y) System.out.println("x now equal to y");
        
        x = x * 2;
        if(x > y) System.out.println("x now greater than y");
        
        // this won't display anything
        if(x == y) System.out.println("you won't see this");
    }
}
```

#### Output
```
x is less than y
x now equal to y
x now greater than y
```

#### Step-by-Step Execution
1. **Initial**: x = 10, y = 20
2. **First if**: 10 < 20 is true → prints "x is less than y"
3. **After x = x * 2**: x = 20
4. **Second if**: 20 == 20 is true → prints "x now equal to y"
5. **After x = x * 2**: x = 40
6. **Third if**: 40 > 20 is true → prints "x now greater than y"
7. **Fourth if**: 40 == 20 is false → prints nothing

#### Common Relational Operators
| Operator | Meaning |
|----------|---------|
| `<` | Less than |
| `>` | Greater than |
| `==` | Equal to |
| `!=` | Not equal to |
| `<=` | Less than or equal to |
| `>=` | Greater than or equal to |

**Important**: Use `==` for comparison, `=` for assignment!

### The for Loop

#### Basic Syntax
```java
for(initialization; condition; iteration) statement;
```

#### Components
- **initialization**: Sets loop control variable initial value
- **condition**: Boolean test of loop control variable
- **iteration**: Changes loop control variable each iteration
- **statement**: Executed each time through loop

#### Example Program
```java
/*
 Demonstrate the for loop.
 Call this file "ForTest.java".
*/
class ForTest {
    public static void main(String[] args) {
        int x;
        
        for(x = 0; x < 10; x = x + 1)
            System.out.println("This is x: " + x);
    }
}
```

#### Output
```
This is x: 0
This is x: 1
This is x: 2
This is x: 3
This is x: 4
This is x: 5
This is x: 6
This is x: 7
This is x: 8
This is x: 9
```

#### Loop Execution Process
1. **Initialize**: x = 0
2. **Test**: x < 10? (0 < 10 = true)
3. **Execute**: Print "This is x: 0"
4. **Iterate**: x = x + 1 (x becomes 1)
5. **Test**: x < 10? (1 < 10 = true)
6. **Execute**: Print "This is x: 1"
7. **Continue** until x = 10
8. **Test**: x < 10? (10 < 10 = false)
9. **Exit** loop

#### Professional Style: Increment Operator
```java
// Instead of: x = x + 1
// Use: x++

for(x = 0; x < 10; x++)
    System.out.println("This is x: " + x);
```

**Increment/Decrement Operators**:
- **`++`**: Increment by 1 (more efficient)
- **`--`**: Decrement by 1

---

## Code Blocks {#code-blocks}

### Definition
A code block groups two or more statements using curly braces `{}`, creating a logical unit that can be used anywhere a single statement can.

### Block with if Statement

#### Example
```java
if(x < y) { // begin a block
    x = y;
    y = 0;
} // end of block
```

**Key Points**:
- Both statements execute together as a unit
- One cannot execute without the other
- Creates logical linkage between statements

### Block with for Loop

#### Example Program
```java
/*
 Demonstrate a block of code.
 Call this file "BlockTest.java"
*/
class BlockTest {
    public static void main(String[] args) {
        int x, y;
        
        y = 20;
        
        // the target of this loop is a block
        for(x = 0; x < 10; x++) {
            System.out.println("This is x: " + x);
            System.out.println("This is y: " + y);
            y = y - 2;
        }
    }
}
```

#### Output
```
This is x: 0
This is y: 20
This is x: 1
This is y: 18
This is x: 2
This is y: 16
This is x: 3
This is y: 14
This is x: 4
This is y: 12
This is x: 5
This is y: 10
This is x: 6
This is y: 8
This is x: 7
This is y: 6
This is x: 8
This is y: 4
This is x: 9
This is y: 2
```

#### Execution Analysis
Each loop iteration executes all three statements in the block:
1. Print current value of x
2. Print current value of y
3. Decrease y by 2

**Benefits of Blocks**:
- Create logically inseparable units of code
- Enable multiple statements to be treated as one
- Essential for complex control structures

---

## Java Language Elements {#language-elements}

### Whitespace
Java is a **free-form language** - no special indentation rules required.

**Whitespace Characters**:
- Space
- Tab
- Newline
- Form feed

**Rule**: At least one whitespace character needed between tokens not separated by operators/separators.

### Identifiers
Used to name classes, variables, methods, etc.

**Valid Characters**:
- Uppercase and lowercase letters
- Numbers
- Underscore (_)
- Dollar sign ($) - not for general use

**Rules**:
- Cannot begin with a number
- Case-sensitive
- Cannot be a keyword

**Valid Examples**:
```java
AvgTemp
count
a4
$test
this_is_ok
```

**Invalid Examples**:
```java
2count      // begins with number
high-temp   // contains hyphen
Not/ok      // contains slash
```

**Note**: As of JDK 9, underscore alone (_) cannot be used as identifier.

### Literals
Constant values created using literal representation.

**Examples**:
```java
100         // integer literal
98.6        // floating-point literal
'X'         // character literal
"This is a test"  // string literal
```

### Comments

#### Three Types

**1. Single-line Comment**:
```java
// This is a single-line comment
```

**2. Multiline Comment**:
```java
/*
 This is a multiline comment
 spanning several lines
*/
```

**3. Documentation Comment**:
```java
/**
 * This creates HTML documentation
 * Used for generating API docs
 */
```

### Separators

| Symbol | Name | Purpose |
|--------|------|---------|
| `( )` | Parentheses | Parameter lists, precedence, control statements, cast types |
| `{ }` | Braces | Array initialization, code blocks, class/method definitions |
| `[ ]` | Brackets | Array declarations and dereferencing |
| `;` | Semicolon | Statement termination |
| `,` | Comma | Separate identifiers, chain for statements |
| `.` | Period | Package separation, member access |
| `::` | Colons | Method/constructor references |
| `...` | Ellipsis | Variable-arity parameters |
| `@` | At-sign | Annotation marker |

### Java Keywords

Java currently defines **67 keywords**. Here are the most commonly used:

#### Core Keywords
```java
abstract    boolean     break       byte        case
catch       char        class       const       continue
default     do          double      else        extends
final       finally     float       for         goto
if          implements  import      instanceof  int
interface   long        native      new         null
package     private     protected   public      return
short       static      super       switch      synchronized
this        throw       throws      try         void
volatile    while
```

#### Context-Sensitive Keywords (added in recent versions)
```java
exports     module      open        opens       provides
record      requires    sealed      to          transitive
uses        var         with        yield       permits
non-sealed
```

#### Reserved Values
```java
true        false       null
```

**Important**: Keywords cannot be used as identifiers (variable names, class names, etc.).

---

## Java Class Libraries {#class-libraries}

### Introduction
Java environment relies on built-in class libraries that provide extensive functionality beyond the core language.

### Standard Library Features
- **I/O Operations**: Input/output handling
- **String Handling**: Text manipulation
- **Networking**: Network communication
- **Graphics**: Visual elements and drawing
- **GUI Support**: Graphical user interfaces
- **Data Structures**: Collections, arrays, etc.
- **Utilities**: Date/time, math functions, etc.

### System.out Methods
The sample programs use Java's built-in output methods:

```java
System.out.println("text");  // Output with newline
System.out.print("text");    // Output without newline
```

**Components**:
- **`System`**: Predefined class automatically included
- **`out`**: Standard output stream
- **`println()`/`print()`**: Methods for console output

### Learning Path
- **Part I**: Language fundamentals with necessary library elements
- **Part II**: Detailed exploration of specific class libraries
- **Goal**: Learn to effectively use Java's standard classes

### Key Concept
Java programming involves two aspects:
1. **Java Language**: Syntax, keywords, operators
2. **Standard Classes**: Pre-built functionality and methods

Mastering both is essential for effective Java programming.

---

## Summary

This overview covered the fundamental concepts needed to begin Java programming:

1. **OOP Foundation**: Encapsulation, inheritance, polymorphism
2. **Basic Programs**: File structure, compilation, execution
3. **Control Flow**: if statements and for loops
4. **Code Organization**: Blocks and logical units
5. **Language Elements**: Identifiers, literals, keywords
6. **Class Libraries**: Built-in functionality

These concepts form the foundation for all Java programming. Each topic will be explored in greater detail in subsequent chapters, building upon this fundamental understanding.
