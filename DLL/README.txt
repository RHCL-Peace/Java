****************
* Project 2/Double-linked List
* cs221-1
* 11/20/2020
* Hesham Natouf
**************** 

OVERVIEW:

 Double-linked implementation of IndexedUnsortedList.
 An Iterator with working remove() method is implemented and a
 ListIterator that is supported and uses working methods set(), 
 add() nextIndex(), and previousIndex(). 

INCLUDED FILES:

 List the files required for the project with a brief
 explanation of why each is included.

 * IUDoubleLinkedList.java - source file
 * ListTester.java - source file
 * Node.java - source file
 * IndexedUnsortedList.java - source file
 * ListTestingScenarios - this file
 * README - this file


COMPILING AND RUNNING:

 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ $ javac ListTester.java

 Run the compiled class file with the command:
 $ $ java ListTester

 Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 The main idea of ​​the double linked list versus the single linked list is now
 instead of only being able to go next you can now go to the previous node allowing you
 to go both directions. 

 in order to make this program work I needed 3 main class, IUDoubleLInkedList,
 Node and ListTester. this double linked list class has serverl method that allow the list to add,
 addFront, addRear, addAfter, remove, removeAtIndex, and other method that allow the list to be fully
 functional in addation this time the double linked list has the listIterator that have serverl new method.
 
 Node class. The Node class gives basic methods that should be used in list
 iterator methods of double linked list class.
 
 ListTester was a class created to test methods in the double
 linked list where functioning working. List tester class was compiled with
 change scenarios, tests using methods in IndexedUnsortedList and tests for
 List Iterator. Each of these tests where used in order to try and break the
 methods within the double linked list. If test would fail they would print
 to the console what scenario and the physical test being done.

TESTING:

 ListTester is a class used to test the methods with double linked list.
 ListTester was created before double linked list to help make sure that
 the methods would work right away. 

 Some Method being tested by ListTester include:
    addToFront()
    addToRear()
    add()
    addAfter()
    removeFirst()
    removeLast()
    remove()
    set()

    Iterator methods

    iterator remove() after next() 
    iterator remove() after previous()
    iterator add()
    add() after next()
    add() after previous()

   
  Testing currently at 100% with 82 change scenarios from LIST TESTING SCENARIOS, and to my knowledge I believe
  my program takes in just about every type of situation that can or will happen. 


DISCUSSION:
 
 Creating the test class behind Test-driven development is a some thing i have not 
 experinced before. adding the test scenarios or just adding the actual test in general.
 I may not have learned everything about how to make a fully function tester on my own,
 but I can take this knowledge I've learned and most likely make one of my own. 
 
 The one thing that was hard to wrap my head around and took me awhile to understand 
 was how to properly code methods in the double linked list. In the single linked list
 I only had to work on a one way moving node, but with the double I could now traverse
 either way. This concept took some time to understand. 
 
 While programming the methods and checking them with the list tester class I found 
 errors constantly. These errors where anywhere from concurrent errors to actually methods
 that where failing tests. To fix the concurrent errors I found out that I needed to add a modCount++
 to my set methods, adding it to my set methods instantly made the errors go away.
----------------------------------------------------------------------------