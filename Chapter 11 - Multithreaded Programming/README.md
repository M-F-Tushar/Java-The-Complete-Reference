# Complete Java Multithreaded Programming Guide

## Table of Contents
1. [Introduction to Multithreading](#introduction-to-multithreading)
2. [The Java Thread Model](#the-java-thread-model)
3. [Thread Priorities](#thread-priorities)
4. [The Thread Class and Runnable Interface](#the-thread-class-and-runnable-interface)
5. [The Main Thread](#the-main-thread)
6. [Creating Threads](#creating-threads)
7. [Creating Multiple Threads](#creating-multiple-threads)
8. [Thread Control Methods](#thread-control-methods)
9. [Thread Priorities in Detail](#thread-priorities-in-detail)
10. [Synchronization](#synchronization)
11. [Interthread Communication](#interthread-communication)
12. [Deadlock](#deadlock)
13. [Thread Control (Modern Approach)](#thread-control-modern-approach)
14. [Thread States](#thread-states)
15. [Factory Methods for Threads](#factory-methods-for-threads)
16. [Best Practices](#best-practices)

---

## Introduction to Multithreading

Java provides built-in support for multithreaded programming. A multithreaded program contains two or more parts that can run concurrently. Each part is called a **thread**, and each thread defines a separate path of execution. Multithreading is a specialized form of multitasking.

### Types of Multitasking

There are two distinct types of multitasking:

#### 1. Process-Based Multitasking
- A **process** is a program that is executing
- Allows your computer to run two or more programs concurrently
- Example: Running Java compiler while using a text editor
- The program is the smallest unit of code dispatched by the scheduler
- Processes are heavyweight tasks requiring separate address spaces
- Interprocess communication is expensive and limited
- Context switching between processes is costly

#### 2. Thread-Based Multitasking
- The **thread** is the smallest unit of dispatchable code
- A single program can perform multiple tasks simultaneously
- Example: A text editor formatting text while printing
- Threads are lightweight and share the same address space
- Interthread communication is inexpensive
- Context switching between threads is lower in cost

### Benefits of Multithreading

1. **Efficient CPU Usage**: Keeps idle time to a minimum
2. **Better Resource Utilization**: Other threads can run when one is waiting
3. **Improved User Experience**: Prevents the entire program from freezing
4. **Multicore Support**: Can actually execute simultaneously on multicore systems

---

## The Java Thread Model

The Java runtime system depends on threads for many operations, and all class libraries are designed with multithreading in mind. Java uses threads to enable the entire environment to be asynchronous, reducing inefficiency by preventing waste of CPU cycles.

### Single-Threaded vs. Multithreaded Systems

#### Single-Threaded Approach (Event Loop with Polling)
- Single thread runs in infinite loop
- Polls a single event queue to decide what to do next
- When an event handler is processing, nothing else can happen
- Wastes CPU time and can cause system domination

#### Java's Multithreaded Approach
- Eliminates the main loop/polling mechanism
- One thread can pause without stopping other parts
- Idle time from network reads or user input can be utilized elsewhere
- Only the blocked thread pauses; all others continue running

### Multicore vs. Single-Core Systems

- **Single-Core**: Threads share CPU time through time slicing
- **Multicore**: Multiple threads can execute simultaneously
- Java's multithreading works efficiently in both environments

---

## Thread Priorities

Java assigns each thread a priority that determines scheduling treatment. Thread priorities are integers specifying relative priority between threads.

### Key Points About Priorities
- Higher priority doesn't mean faster execution if it's the only running thread
- Priority is used to decide when to switch from one thread to another (context switch)
- Priority values range from `MIN_PRIORITY` (1) to `MAX_PRIORITY` (10)
- Default priority is `NORM_PRIORITY` (5)

### Context Switch Rules
1. **Voluntary Control Relinquishment**: Thread yields, sleeps, or blocks
2. **Preemption**: Higher-priority thread preempts lower-priority thread

### Equal Priority Handling
- Varies by operating system
- Some systems use automatic round-robin time slicing
- Others require voluntary yielding
- **Caution**: Portability problems can arise from OS differences

---

## The Thread Class and Runnable Interface

Java's multithreading system is built upon the `Thread` class and the `Runnable` interface.

### Key Thread Methods

| Method | Description |
|--------|-------------|
| `getName()` | Obtain a thread's name |
| `getPriority()` | Obtain a thread's priority |
| `isAlive()` | Determine if a thread is still running |
| `join()` | Wait for a thread to terminate |
| `run()` | Entry point for the thread |
| `sleep()` | Suspend a thread for a period of time |
| `start()` | Start a thread by calling its run method |

---

## The Main Thread

When a Java program starts, one thread begins running immediately - the **main thread**.

### Importance of Main Thread
1. It spawns other "child" threads
2. Often must be the last thread to finish execution (performs shutdown actions)

### Controlling the Main Thread

```java
// Controlling the main Thread
class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Current thread: " + t);
        
        // change the name of the thread
        t.setName("My Thread");
        System.out.println("After name change: " + t);
        
        try {
            for(int n = 5; n > 0; n--) {
                System.out.println(n);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }
}
```

**Output:**
```
Current thread: Thread[main,5,main]
After name change: Thread[My Thread,5,main]
5
4
3
2
1
```

### Key Methods Explained

#### `currentThread()`
```java
static Thread currentThread()
```
Returns a reference to the currently executing thread.

#### `sleep()`
```java
static void sleep(long milliseconds) throws InterruptedException
static void sleep(long milliseconds, int nanoseconds) throws InterruptedException
```
Causes the calling thread to suspend execution for the specified period.

#### Thread Naming Methods
```java
final void setName(String threadName)
final String getName()
```

---

## Creating Threads

Java defines two ways to create threads:

### Method 1: Implementing Runnable (Recommended)

The `Runnable` interface abstracts a unit of executable code and requires implementing a single method:

```java
public void run()
```

#### Complete Example:

```java
// Create a second thread by implementing Runnable
class NewThread implements Runnable {
    Thread t;
    
    NewThread() {
        // Create a new, second thread
        t = new Thread(this, "Demo Thread");
        System.out.println("Child thread: " + t);
    }
    
    // This is the entry point for the second thread
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}

class ThreadDemo {
    public static void main(String[] args) {
        NewThread nt = new NewThread(); // create a new thread
        nt.t.start(); // Start the thread
        
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }
}
```

**Sample Output:**
```
Child thread: Thread[Demo Thread,5,main]
Main Thread: 5
Child Thread: 5
Child Thread: 4
Main Thread: 4
Child Thread: 3
Child Thread: 2
Main Thread: 3
Child Thread: 1
Exiting child thread.
Main Thread: 2
Main Thread: 1
Main thread exiting.
```

### Method 2: Extending Thread Class

```java
// Create a second thread by extending Thread
class NewThread extends Thread {
    NewThread() {
        // Create a new, second thread
        super("Demo Thread");
        System.out.println("Child thread: " + this);
    }
    
    // This is the entry point for the second thread
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}

class ExtendThread {
    public static void main(String[] args) {
        NewThread nt = new NewThread(); // create a new thread
        nt.start(); // start the thread
        
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }
}
```

### Choosing an Approach

**Runnable is Generally Preferred Because:**
1. Classes should be extended only when being enhanced or adapted
2. Implementing Runnable allows the class to inherit from another class
3. Promotes better object-oriented design
4. More flexible architecture

**When to Extend Thread:**
- When you need to override other Thread methods beyond `run()`
- When the class is specifically designed to be a specialized thread

---

## Creating Multiple Threads

You can create as many threads as needed. Here's an example with three child threads:

```java
// Create multiple threads
class NewThread implements Runnable {
    String name; // name of thread
    Thread t;
    
    NewThread(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
    }
    
    // This is the entry point for thread
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " Interrupted");
        }
        System.out.println(name + " exiting.");
    }
}

class MultiThreadDemo {
    public static void main(String[] args) {
        NewThread nt1 = new NewThread("One");
        NewThread nt2 = new NewThread("Two");
        NewThread nt3 = new NewThread("Three");
        
        // Start the threads
        nt1.t.start();
        nt2.t.start();
        nt3.t.start();
        
        try {
            // wait for other threads to end
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
    }
}
```

**Sample Output:**
```
New thread: Thread[One,5,main]
New thread: Thread[Two,5,main]
New thread: Thread[Three,5,main]
One: 5
Two: 5
Three: 5
One: 4
Two: 4
Three: 4
One: 3
Three: 3
Two: 3
One: 2
Three: 2
Two: 2
One: 1
Three: 1
Two: 1
One exiting.
Two exiting.
Three exiting.
Main thread exiting.
```

---

## Thread Control Methods

### `isAlive()` and `join()`

Rather than using arbitrary sleep times, use proper thread coordination:

#### `isAlive()`
```java
final boolean isAlive()
```
Returns `true` if the thread is still running, `false` otherwise.

#### `join()`
```java
final void join() throws InterruptedException
```
Waits until the thread on which it's called terminates.

#### Complete Example:

```java
// Using join() to wait for threads to finish
class NewThread implements Runnable {
    String name; // name of thread
    Thread t;
    
    NewThread(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
    }
    
    // This is the entry point for thread
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
        }
        System.out.println(name + " exiting.");
    }
}

class DemoJoin {
    public static void main(String[] args) {
        NewThread nt1 = new NewThread("One");
        NewThread nt2 = new NewThread("Two");
        NewThread nt3 = new NewThread("Three");
        
        // Start the threads
        nt1.t.start();
        nt2.t.start();
        nt3.t.start();
        
        System.out.println("Thread One is alive: " + nt1.t.isAlive());
        System.out.println("Thread Two is alive: " + nt2.t.isAlive());
        System.out.println("Thread Three is alive: " + nt3.t.isAlive());
        
        // wait for threads to finish
        try {
            System.out.println("Waiting for threads to finish.");
            nt1.t.join();
            nt2.t.join();
            nt3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        
        System.out.println("Thread One is alive: " + nt1.t.isAlive());
        System.out.println("Thread Two is alive: " + nt2.t.isAlive());
        System.out.println("Thread Three is alive: " + nt3.t.isAlive());
        
        System.out.println("Main thread exiting.");
    }
}
```

**Sample Output:**
```
New thread: Thread[One,5,main]
New thread: Thread[Two,5,main]
New thread: Thread[Three,5,main]
Thread One is alive: true
Thread Two is alive: true
Thread Three is alive: true
Waiting for threads to finish.
One: 5
Two: 5
Three: 5
One: 4
Two: 4
Three: 4
One: 3
Two: 3
Three: 3
One: 2
Two: 2
Three: 2
One: 1
Two: 1
Three: 1
Two exiting.
Three exiting.
One exiting.
Thread One is alive: false
Thread Two is alive: false
Thread Three is alive: false
Main thread exiting.
```

---

## Thread Priorities in Detail

### Setting and Getting Priorities

```java
final void setPriority(int level)
final int getPriority()
```

### Priority Constants
- `MIN_PRIORITY` (1)
- `NORM_PRIORITY` (5) - Default
- `MAX_PRIORITY` (10)

### Important Considerations
1. Higher-priority threads get more CPU time over time
2. Higher-priority threads can preempt lower-priority ones
3. Actual behavior depends on the operating system
4. Equal-priority threads should yield control periodically
5. For predictable behavior, use cooperative threading

---

## Synchronization

When multiple threads access shared resources, synchronization ensures that only one thread uses the resource at a time.

### The Monitor Concept
- A **monitor** is an object used as a mutually exclusive lock
- Only one thread can own a monitor at a time
- When a thread acquires a lock, it enters the monitor
- Other threads wait for the monitor
- A thread can reenter the same monitor

### Method 1: Synchronized Methods

#### Problem Example (Race Condition):

```java
// This program is NOT synchronized
class Callme {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;
    
    public Caller(Callme targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
    }
    
    public void run() {
        target.call(msg);
    }
}

class Synch {
    public static void main(String[] args) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "World");
        
        // Start the threads
        ob1.t.start();
        ob2.t.start();
        ob3.t.start();
        
        // wait for threads to end
        try {
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
```

**Output (Race Condition):**
```
[Hello[Synchronized[World]
]
]
```

#### Solution (Synchronized Method):

```java
class Callme {
    synchronized void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}
```

**Output (Synchronized):**
```
[Hello]
[Synchronized]
[World]
```

### Method 2: Synchronized Statements

When you can't modify the class to add synchronized methods:

```java
synchronized(objRef) {
    // statements to be synchronized
}
```

#### Example:

```java
// This program uses a synchronized block
class Callme {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;
    
    public Caller(Callme targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
    }
    
    // synchronize calls to call()
    public void run() {
        synchronized(target) { // synchronized block
            target.call(msg);
        }
    }
}
```

---

## Interthread Communication

Java provides elegant interthread communication through `wait()`, `notify()`, and `notifyAll()` methods.

### Communication Methods
```java
final void wait() throws InterruptedException
final void notify()
final void notifyAll()
```

### Rules for Using These Methods
- Must be called from within a synchronized context
- `wait()` tells calling thread to give up monitor and sleep
- `notify()` wakes up a thread that called `wait()` on the same object
- `notifyAll()` wakes up all threads that called `wait()` on the same object

### Producer-Consumer Problem

#### Incorrect Implementation:

```java
// An incorrect implementation of a producer and consumer
class Q {
    int n;
    
    synchronized int get() {
        System.out.println("Got: " + n);
        return n;
    }
    
    synchronized void put(int n) {
        this.n = n;
        System.out.println("Put: " + n);
    }
}

class Producer implements Runnable {
    Q q;
    Thread t;
    
    Producer(Q q) {
        this.q = q;
        t = new Thread(this, "Producer");
    }
    
    public void run() {
        int i = 0;
        while(true) {
            q.put(i++);
        }
    }
}

class Consumer implements Runnable {
    Q q;
    Thread t;
    
    Consumer(Q q) {
        this.q = q;
        t = new Thread(this, "Consumer");
    }
    
    public void run() {
        while(true) {
            q.get();
        }
    }
}

class PC {
    public static void main(String[] args) {
        Q q = new Q();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);
        
        // Start the threads
        p.t.start();
        c.t.start();
        
        System.out.println("Press Control-C to stop.");
    }
}
```

**Problematic Output:**
```
Put: 1
Got: 1
Got: 1
Got: 1
Got: 1
Got: 1
Put: 2
Put: 3
Put: 4
Put: 5
Put: 6
Put: 7
Got: 7
```

#### Correct Implementation Using wait() and notify():

```java
// A correct implementation of a producer and consumer
class Q {
    int n;
    boolean valueSet = false;
    
    synchronized int get() {
        while(!valueSet)
            try {
                wait();
            } catch(InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }
    
    synchronized void put(int n) {
        while(valueSet)
            try {
                wait();
            } catch(InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        
        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}

// Producer and Consumer classes remain the same

class PCFixed {
    public static void main(String[] args) {
        Q q = new Q();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);
        
        // Start the threads
        p.t.start();
        c.t.start();
        
        System.out.println("Press Control-C to stop.");
    }
}
```

**Correct Output:**
```
Put: 1
Got: 1
Put: 2
Got: 2
Put: 3
Got: 3
Put: 4
Got: 4
Put: 5
Got: 5
```

---

## Deadlock

Deadlock occurs when two threads have a circular dependency on synchronized objects.

### Deadlock Example:

```java
// An example of deadlock
class A {
    synchronized void foo(B b) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered A.foo");
        
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            System.out.println("A Interrupted");
        }
        
        System.out.println(name + " trying to call B.last()");
        b.last();
    }
    
    synchronized void last() {
        System.out.println("Inside A.last");
    }
}

class B {
    synchronized void bar(A a) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered B.bar");
        
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            System.out.println("B Interrupted");
        }
        
        System.out.println(name + " trying to call A.last()");
        a.last();
    }
    
    synchronized void last() {
        System.out.println("Inside B.last");
    }
}

class Deadlock implements Runnable {
    A a = new A();
    B b = new B();
    Thread t;
    
    Deadlock() {
        Thread.currentThread().setName("MainThread");
        t = new Thread(this, "RacingThread");
    }
    
    void deadlockStart() {
        t.start();
        a.foo(b); // get lock on a in this thread
        System.out.println("Back in main thread");
    }
    
    public void run() {
        b.bar(a); // get lock on b in other thread
        System.out.println("Back in other thread");
    }
    
    public static void main(String[] args) {
        Deadlock dl = new Deadlock();
        dl.deadlockStart();
    }
}
```

**Output (Program Hangs):**
```
MainThread entered A.foo
RacingThread entered B.bar
MainThread trying to call B.last()
RacingThread trying to call A.last()
```

### Deadlock Characteristics
- Occurs rarely when threads time-slice in specific ways
- Can involve multiple threads and objects
- Difficult to debug
- Program appears to freeze

### Prevention Strategies
1. Avoid nested locks
2. Use consistent lock ordering
3. Use timeout mechanisms
4. Careful design of synchronized sections

---

## Thread Control (Modern Approach)

### Deprecated Methods (DO NOT USE)
- `suspend()` - Can cause deadlock
- `resume()` - Deprecated with suspend()
- `stop()` - Can corrupt data structures

### Modern Approach: Flag-Based Control

Threads should check a flag variable periodically to determine their execution state.

```java
// Suspending and resuming a thread the modern way
class NewThread implements Runnable {
    String name; // name of thread
    Thread t;
    boolean suspendFlag;
    
    NewThread(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        suspendFlag = false;
    }
    
    // This is the entry point for thread
    public void run() {
        try {
            for(int i = 15; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized(this) {
                    while(suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
        }
        System.out.println(name + " exiting.");
    }
    
    synchronized void mysuspend() {
        suspendFlag = true;
    }
    
    synchronized void myresume() {
        suspendFlag = false;
        notify();
    }
}

class SuspendResume {
    public static void main(String[] args) {
        NewThread ob1 = new NewThread("One");
        NewThread ob2 = new NewThread("Two");
        
        ob1.t.start(); // Start the thread
        ob2.t.start(); // Start the thread
        
        try {
            Thread.sleep(1000);
            ob1.mysuspend();
            System.out.println("Suspending thread One");
            Thread.sleep(1000);
            ob1.myresume();
            System.out.println("Resuming thread One");
            ob2.mysuspend();
            System.out.println("Suspending thread Two");
            Thread.sleep(1000);
            ob2.myresume();
            System.out.println("Resuming thread Two");
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        
        // wait for threads to finish
        try {
            System.out.println("Waiting for threads to finish.");
            ob1.t.join();
            ob2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        
        System.out.println("Main thread exiting.");
    }
}
```

---

## Thread States

### `getState()` Method
```java
Thread.State getState()
```

### Thread States Enumeration

| State | Description |
|-------|-------------|
| `BLOCKED` | Thread suspended waiting to acquire a lock |
| `NEW` | Thread that has not begun execution |
| `RUNNABLE` | Thread currently executing or will execute when it gains CPU access |
| `TERMINATED` | Thread that has completed execution |
| `TIMED_WAITING` | Thread suspended for a specified time period (sleep, timed wait/join) |
| `WAITING` | Thread suspended waiting for some action (wait, join) |

### Example Usage:
```java
Thread.State ts = thrd.getState();
if(ts == Thread.State.RUNNABLE) {
    // Thread is runnable
}
```

### Important Notes
- A thread's state may change immediately after calling `getState()`
- Not intended for synchronization
- Primarily used for debugging and profiling

---

## Factory Methods for Threads

Factory methods can streamline thread creation and starting:

```java
// A factory method that creates and starts a thread
public static NewThread createAndStart() {
    NewThread myThrd = new NewThread();
    myThrd.t.start();
    return myThrd;
}
```

### Usage:
Instead of:
```java
NewThread nt = new NewThread(); // create a new thread
nt.t.start(); // Start the thread
```

Use:
```java
NewThread nt = NewThread.createAndStart();
```

### One-Line Creation (when no reference needed):
```java
new NewThread().t.start();
```

---

## Best Practices

### 1. Thread Creation
- **Prefer implementing Runnable** over extending Thread
- Use factory methods for common creation patterns
- Give threads meaningful names for debugging

### 2. Synchronization
- **Minimize synchronized sections** - keep them as small as possible
- **Avoid nested synchronization** to prevent deadlock
- Use `wait()` and `notify()` for thread coordination
- Consider using higher-level concurrency utilities (covered in Chapter 29)

### 3. Thread Management
- Use `join()` to wait for thread completion
- Implement proper cleanup in thread termination
- Use flag-based control instead of deprecated methods

### 4. Performance Considerations
- **Don't create too many threads** - context switching overhead
- Design for both single-core and multicore systems
- Consider the Fork/Join Framework for compute-intensive applications

### 5. Error Handling
- Always handle `InterruptedException` properly
- Use try-catch blocks around sleep() and wait() calls
- Implement proper exception handling in run() methods

### 6. Debugging
- Use `getState()` for debugging and profiling
- Name your threads for easier debugging
- Be aware of race conditions and test thoroughly

### 7. Modern Alternatives
- Consider using `java.util.concurrent` package for complex scenarios
- Explore `ExecutorService` for thread pool management
- Use `CompletableFuture` for asynchronous programming

---

## Summary

Java's multithreading capabilities provide powerful tools for creating efficient, responsive applications. Key takeaways:

1. **Two ways to create threads**: Implementing `Runnable` (preferred) or extending `Thread`
2. **Synchronization is crucial** for shared resource access using `synchronized` keyword
3. **Thread communication** through `wait()`, `notify()`, and `notifyAll()` methods
4. **Avoid deprecated methods** like `suspend()`, `resume()`, and `stop()`
5. **Use modern control mechanisms** with flag variables and proper synchronization
6. **Be aware of deadlock** and design to prevent it
7. **Proper thread lifecycle management** using `join()` and state monitoring
8. **Consider performance implications** of thread creation and context switching

### Thread Lifecycle Diagram

```
NEW → start() → RUNNABLE ⟷ BLOCKED
                    ↓         ↑
                TIMED_WAITING
                    ↓         ↑
                  WAITING → notify()/notifyAll()
                    ↓
                TERMINATED
```

### Common Patterns and Examples

#### Pattern 1: Worker Thread
```java
class WorkerThread implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private volatile boolean running = true;
    
    public WorkerThread(BlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }
    
    public void run() {
        while (running) {
            try {
                Task task = taskQueue.take();
                task.execute();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    public void shutdown() {
        running = false;
    }
}
```

#### Pattern 2: Thread-Safe Counter
```java
class ThreadSafeCounter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized void decrement() {
        count--;
    }
    
    public synchronized int getValue() {
        return count;
    }
}
```

#### Pattern 3: Producer-Consumer with Buffer
```java
class BoundedBuffer<T> {
    private final Queue<T> buffer = new LinkedList<>();
    private final int capacity;
    
    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }
    
    public synchronized void put(T item) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait(); // Buffer is full, wait for space
        }
        buffer.offer(item);
        notifyAll(); // Notify consumers that item is available
    }
    
    public synchronized T take() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); // Buffer is empty, wait for items
        }
        T item = buffer.poll();
        notifyAll(); // Notify producers that space is available
        return item;
    }
}
```

### Advanced Topics to Explore Further

1. **java.util.concurrent Package**
   - `ExecutorService` and thread pools
   - `CountDownLatch`, `CyclicBarrier`, `Semaphore`
   - `ReentrantLock` and `ReadWriteLock`
   - `Atomic` classes for lock-free programming

2. **Fork/Join Framework**
   - Designed for parallel processing
   - Work-stealing algorithm
   - Ideal for divide-and-conquer algorithms

3. **Concurrent Collections**
   - `ConcurrentHashMap`
   - `BlockingQueue` implementations
   - `CopyOnWriteArrayList`

4. **Memory Model and Visibility**
   - `volatile` keyword
   - Happens-before relationships
   - Memory barriers

### Troubleshooting Common Issues

#### Issue 1: Race Conditions
**Symptoms**: Inconsistent results, data corruption
**Solution**: Use proper synchronization

#### Issue 2: Deadlock
**Symptoms**: Program hangs, threads waiting indefinitely
**Solution**: 
- Avoid nested locks
- Use consistent lock ordering
- Implement timeout mechanisms

#### Issue 3: Performance Degradation
**Symptoms**: Slower performance with multiple threads
**Solution**:
- Reduce synchronization overhead
- Minimize context switching
- Balance thread count with available cores

#### Issue 4: Memory Leaks
**Symptoms**: Increasing memory usage over time
**Solution**:
- Properly terminate threads
- Clean up thread-local variables
- Avoid retaining references to finished threads

### Testing Multithreaded Code

```java
// Example unit test for thread safety
public class ThreadSafeCounterTest {
    @Test
    public void testConcurrentIncrement() throws InterruptedException {
        ThreadSafeCounter counter = new ThreadSafeCounter();
        int numThreads = 10;
        int incrementsPerThread = 1000;
        
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);
        
        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                try {
                    for (int j = 0; j < incrementsPerThread; j++) {
                        counter.increment();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        
        latch.await();
        executor.shutdown();
        
        Assert.assertEquals(numThreads * incrementsPerThread, counter.getValue());
    }
}
```

### Performance Monitoring

```java
// Thread monitoring example
class ThreadMonitor {
    public static void printThreadInfo() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        
        System.out.println("Total threads: " + threadBean.getThreadCount());
        System.out.println("Daemon threads: " + threadBean.getDaemonThreadCount());
        System.out.println("Peak threads: " + threadBean.getPeakThreadCount());
        
        // Get detailed thread information
        ThreadInfo[] threadInfos = threadBean.getThreadInfo(
            threadBean.getAllThreadIds());
        
        for (ThreadInfo info : threadInfos) {
            if (info != null) {
                System.out.println("Thread: " + info.getThreadName() + 
                                 " State: " + info.getThreadState());
            }
        }
    }
}
```

This comprehensive guide covers all the essential aspects of Java multithreading from the provided content, with detailed explanations, complete code examples, and practical insights for effective multithreaded programming. The examples progress from basic concepts to more advanced patterns, providing a solid foundation for understanding and implementing multithreaded applications in Java.
