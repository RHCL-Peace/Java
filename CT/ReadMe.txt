****************
* Project 3/Circuit Tracer
* cs221-1
* 12/11/2020
* Hesham Natouf
**************** 

OVERVIEW:

 The Circuit Tracer program reads in 2 dimensional arrays that comprise a circuit that 
 search for shortest paths between start and end points on a circuit board
 as read from an input file using either a stack or queue as the underlying
 search state storage structure and displaying output to the console or to
 a GUI according to options specified via command-line arguments.

INCLUDED FILES:

 List the files required for the project with a brief
 explanation of why each is included.

 * CircuitBoard.java - source file
 * CircuitTracer.java - source file
 * CircuitTracerTester.java - source file
 * InvalidFileFormatException.java - source file
 * OccupiedPositionException.java
 * ListTestingScenarios - this file
 * Storage.java
 * TraceState.java
 * README - this file


COMPILING AND RUNNING:

 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ $ javac CircuitBoard.java
 $ $ javac CircuitTracer.java
 $ $ javac CircuitTracerTester.java

 Run the compiled class file with the command:
 $ $ java CircuitTracer

 Console output will give the results after the program finishes.

PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 The CircuitTracer class has command line parameters, you can get a stacked storage object or 
 a queue storage object based on user preferences. The filename of the circuit board The user wants to
 solve is also passed as a command-line argument. The file name is passed to CircuitBoard.java class, 
 which checks for coordination issues within the file as it builds the circuit Panel from the network 
 contained in the file. This circuit board is returned and passed, as parameters, to the Sorting 
 algorithm with the user requested storage object. Then the file and storage object is used to keep track 
 of all possible solutions to access from the starting point From the circuit to the final component 
 of the circuit, returning only the shortest paths possible. The algorithm Itself is a brute-force algorithm 
 that starts trying to move to all four adjacent positions around Initiate the component and, if available, 
 store each new step, called TraceState, in storage Theme. Each TraceState is then removed from the 
 storage object and checked to see if it is next to the End component. 

TESTING:

 The provided CircuitTracerTester.java class was used to test both the CircuitTracer and CircuitBoard classes
 until it passed all tests. The CircuitBoard class handles exceptions that arise from any improper formatting  
 by throwing an InvalidFileFormatException with a useful message. A FileNotFoundException, if needed, is 
 thrown to the CircuitTracer class to be handled.

DISCUSSION:
 
 the most challenging aspect of this project was the CircuitBoard constructor. Between checking for 
 all of the different possible invalid format exceptions, as well as simultaneously building the circuit, 
 I had a lot of bugs to find and work out. I also tried to make the sorting and tracing method recursive after 
 I had it up and running. I kept  running into NullPointerExceptions and decided that I wasn't doing it properly
 and that there may be portions that are recursive in nature ("store") and parts that aren't and I should reserve my time 
 for the final and sleep. I'd really like to know what I was doing wrong for the recursion, but it seems 
 overly complicated to go through it all in the Discussion section without the actual code I tried.
----------------------------------------------------------------------------