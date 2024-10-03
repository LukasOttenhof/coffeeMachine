CoffeeMaker JUnit
=================================
In this assignment, you'll get some practice at building effective unit tests on a 
slightly larger example of a coffee maker. The coffee maker allows you to set up recipes, 
add ingredients, and to make one of several beverages, provided that you insert enough money.
It is an often-used pedagogical example from our colleague [Laurie Williams](https://www.csc.ncsu.edu/people/lawilli3) at 
NC State University. A list of the user stories (requirements) and use cases are included 
in the `Requirements-CoffeeMaker.pdf` file.

Below, are instructions to get you started.  We describe the prerequisites and provide 
instructions for building and running the tests.  We also include information about 
the directory structure provided.

### Deliverables
Your task is to create a file, `CoffeeMakerTest.java`, which properly tests 
the `CoffeeMaker` class to ensure it is working properly.

Inside the project, you will find the functional code, a couple of unit tests to get 
you started. The goal is to construct a sufficient number of unit tests to find 
most bugs in the coffee maker that is included. 

You should be able to detect at least 5 bugs in the code using your unit tests.

This exercise focuses entirely on testing.  **You are NOT to fix the coffee maker.**  
You are only to develop tests to exercise the 
functionality as described in the requirements.


Directory Structure
-------------------
* `Requirements-CoffeeMaker.pdf` -- the requirements
* `ClassDiagram-CoffeeMaker.pdf` -- the class diagram of the coffee maker software
* `src/main/java` -- contains the system under test (SUT; in this case, the coffee maker code) and all of its dependencies.  **Do not modify any of this code.**
* `src/test/java` -- the test code
