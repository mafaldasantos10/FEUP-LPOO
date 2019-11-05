# LPOO_312 - Flappy Bird

Our project was based on the well-known mobile game **Flappy Bird**.

The goal is to navigate the bird through the pipes. The player's score increases for each set of pipes successfully passed, but if the bird hits one of them or the lower or the upper limit, the game ends.

> This project was developed by *Mafalda Santos* (*up201706791*@fe.up.pt) and *Diogo Silva* (*up201706892*@fe.up.pt) for LPOO 2018⁄19.

------

## Implemented Features

**Flying** - The bird will fly when the *Enter Key* is pressed.

**Diving** - The bird will fall at a given rate due to gravity.

**Moving Pipes** - Each pair of pipes will move in the screen at a given rate. If the bird hits any of the pipes the game ends.
Each pipe is randomly generated, i.e, the distance between them, their height and the respective gap is different for each one of them.

**Moving Map Limits** - As with pipes, each pair of limits will move in the screen at a given rate. If the bird hits any of them, the game ends.

**Score** - The score is incremented for each set of pipes successfully passed.

**Collecting Coins** - the player is also able to collect coins in order to increase the score.

![Game Play][game play]

##### Fig. 1 Game Play Swing

![Game Play Lanterna][game playl]

##### Fig. 2 Game Play Lanterna


## Planned Features

We were planing on implementing the following features:

* **HighScore Panel** - the players will be able to compare their scores with previous players.

* **Increased Difficulty** - after a given number of pipes are crossed, the game will become harder. One way of doing this will be by adding vertical movement to the pipes, thus increasing the skill to pass through them.

------

## Design

### **Problem in Context**
The pipes, limits and coins used in our game were being created ([createPipe()]), used ([draw()]), and then destroyed ([removeInvalidPipes()]). In our case, since there weren't many objects on the screen, the game was able to run smoothly anyway. However, to anticipate future problems, we agreed that the implementation of a design pattern was the best idea.

### **The Pattern**
The pattern used was the **Object Pool** and with it, we managed to reuse the pipes, limits, and coins that are already off the screen and turn them into the new ones that would appear. 

### **Implementation**
For starters, we added a boolean attribute named **_active_** in classes [Pipe], [Limit] and [coin], so that we are able to know which ones we can use. 
When the time comes (randomly generated) and a pipe, limit or coin has to be displayed, the method [computePipes()][compute pipes] and [computeCoins()][compute coins] is called. If all the pipes/limits or coins of their respective arrays are *active*, a new object is created. If that's not the case, the idle object will be reused (coordinates are changed accordingly and it goes back to the screen). 

The following figure shows how the pattern’s roles were mapped to the application classes (only in the case of the Pipes).

![Object Pool][objectPool]

##### Fig. 3 Object Pool UML

These classes can be found in the following files:

* [FlappyBird]

* [Map]

### **Consequences** 
Instead of the over-consuming cycle **create**-**destroy**-**create**, this pattern reuses the already created objects and changes them as needed, which ensures better performance.

---

### **Problem in Context**
We were asked to have our game running not only in **Lanterna** but also in **Swing**. Since we weren't using an MVC architecture the easiest and most reliable way to do it was by implementing a design pattern.

### **The Pattern**
The pattern used was the **Abstract Factory** and with it, we managed to  have two distinct factories: one for **Swing** and another for **Lanterna**, both capable of producing the required objects when necessary.

### **Implementation**
For starters, we added two new interfaces: [abstractDrawingFactory] and [drawingElement].
The first has all functions that will have different implementations between **Lanterna** and **Swing**, while the second, implemented by all drawable classes, enables the objects to ask to be drawn.

There were also created two new classes per object, one responsible for drawing in **Swing** and the other responsible for **Lanterna**.
At last, we added the two new factories that effectively produce **Swing** and **Lanterna** objects for drwaing, [swingDrawingFactory] and [lanternaDrawingFactory], respectively.

![abstractFactory][abstractFactory]
##### Fig. 4 Abstract Factory UML

These classes can be found in the following files:

* [abstractDrawingFactory]

* [swingDrawingFactory]

* [lanternaDrawingFactory]

* functions **drawSwing** and **drawLanterna** of each object

### **Consequences** 

The use of this design pattern allowed us to have the game running efficiently without changing the whole project to an MVC architecture.

---

### **Problem in Context**
When we were implementing the previous design pattern (Abstract Factory) we decided it was necessary so that the **Single Responsibility Principle** was not violated. The Abstract factory had to know which factory (swing laterna) would be used for drawing.

### **The Pattern**
The pattern used was the **Singleton** and with it, we managed to ensure that we had just a single instance of the game and provide global access to that instance.

### **Implementation**
We started by making our default constructor private, to prevent other objects from using instanciating it. Next, we created a static method that acts as a constructor. This method calls the private constructor to create an object if there's still none created and saves it in a static attribute. After that, all calls to this method return the the existing static object.

![Singleton][singleton]
##### Fig. 5 Singleton UML

These classes can be found in the following files:

* [FlappyBird]

* [Application]

### **Consequences** 

The use of this design pattern allowed us to have the game running efficiently without changing the whole project to an MVC architecture.

------

## Known Code Smells and Refactoring Suggestions

### Long Method
In some parts of the project there are still some methods that might be doing to much, thus being too long.

This can be easily solved by refactoring those methods and extract smaller methods.

### Solved Code Smells

In the process, we found some code smells that were solved in the meanwhile, for instance:

* Methods were hard to read due to the amount of hard-coded values, which was solved solved by using **macros**.
> Example: [createPipes()].

* **Long Method**: methods that were too long, solved by refactoring/extracting new methods.
> Example: enterOcupiedSpaces() [before][enterOcupiedSpaces()] → [after][enterOcupiedSpaces2()].

* **Inappropriate Intimacy**: some classes' attributes were public and were being acceded by some were classes. Solved by making such attributes private and creating methods to access them.

> Example: [getScore()].

* **Speculative Generality**: some classes' methods, usually *sets* and *gets*, were created in antecipation. Solved by eliminating the unused methods via *Inline Method Refactoring*.

* **Dead Code**: some code was becoming obsolete as some features changed and was usually commented to avoid interfering with everything else. Solved by eliminating those lines of code.

------

## Testing Results

* We tried to implement a test for each method of each class of [Game].

* We started to develop some simple tests that involve the bird mechanics, e.g., to test if it flies, dives and dies as it is supposed to.

* In the [Object Pool Test Class][object pool], we test the correct usage of this pattern by checking the number of objects on the screen. 

* In the [Map Test] test Class, we verify if the pipes, coins, and limits are moving correctly and if their boolean attribute isActive is being set correctly. It also checks if when the bird catches a coin its attribute isActive is set to false and the score increments.

* For all objects, we check their constructor, getters, setters and other minor impacct methods.

* At last, we check if the score is being incremented correctly.

![Mutation][mutationG]
##### Fig. 6 Mutation

![Mutation Game][mutationGame]
##### Fig. 7 Mutation Game

![Mutation][coverage]
##### Fig. 8 Mutation

![Mutation Game][coverageGame]
##### Fig. 9 Mutation Game

**Complete Test Coverage and Mutation Results** can be found [here][tests].

------

## Self-evaluation

All the work was equally divided by the two of us.

* Ana Mafalda Santos: **50**%
* Diogo Silva: **50**%


[comment]: <> ( ------ IMAGES ------ )
[game play]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/screenshots/features.gif "Game Play"

[game playl]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/screenshots/lanterna.gif "Game Play Lanterna"

[objectPool]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/screenshots/objectPoolPipe.png "Object Pool"

[abstractFactory]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/screenshots/abstractFactory.png "abstractFactory"

[singleton]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/screenshots/singleton.png "Singleton"

[mutationG]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/screenshots/mutationGeral.PNG "Mutation"

[mutationGame]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/screenshots/mutationGame.PNG "Mutation Game"

[coverage]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/screenshots/coverage.PNG "Coverage"

[coverageGame]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/screenshots/coverageGame.PNG "Coverage Game"

[comment]: <> ( ------- LINKS ------- )
[object pool]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/test/java/lpoo312/Tests/ObjectPoolTest.java

[FlappyBird]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Game/FlappyBird.java

[Application]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Game/Application.java

[Map]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Game/Map.java

[compute pipes]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/57d457f9cce563a375dcbb9fbc5f0b643574a00f/code/src/main/java/Map.java#L191

[compute coins]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/3d53ecd0bceb21f8154e2658cf0ad3b2f86525be/code/src/main/java/Game/Map.java#L270

[limit]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Game/Limit.java

[pipe]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Game/Pipe.java

[coin]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Game/Coin.java

[abstractDrawingFactory]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Game/AbstractDrawingFactory.java

[drawingElement]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Game/DrawingElement.java

[swingDrawingFactory]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Swing/SwingDrawingFactory.java

[lanternaDrawingFactory]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/main/java/lpoo312/Lanterna/LanternaDrawingFactory.java

[createPipe()]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/f4b2d73529d174d76a55191720fdb7cb7853d032/flappybird/src/main/java/Map.java#L162-L174

[draw()]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/f4b2d73529d174d76a55191720fdb7cb7853d032/flappybird/src/main/java/Map.java#L176-L188

[removeInvalidPipes()]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/f4b2d73529d174d76a55191720fdb7cb7853d032/flappybird/src/main/java/Map.java#L51-L55

[tests]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/docs/testResults/index.html

[createPipes()]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/b4eaa45b422f100624b8530fd6f94092d20e51a2/flappybird/src/main/java/Map.java#L131-L153

[enterOcupiedSpaces()]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/b4eaa45b422f100624b8530fd6f94092d20e51a2/flappybird/src/main/java/Map.java#L131-L153

[enterOcupiedSpaces2()]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/f4b2d73529d174d76a55191720fdb7cb7853d032/flappybird/src/main/java/Map.java#L78-L83

[getScore()]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/f4b2d73529d174d76a55191720fdb7cb7853d032/flappybird/src/main/java/Map.java#L78-L83

[Map Test]:https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/blob/master/code/src/test/java/lpoo312/Tests/MapTest.java

[Game]: https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_312/tree/master/code/src/main/java/lpoo312/Game