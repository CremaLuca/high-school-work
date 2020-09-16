# High school work

A collection of projects, scripts, and other resources I produced in high school for IT classes.

## Native Java

Most of my work in high school has been done in Java.

### [Supermarket](https://github.com/Acerkacke/Prodotti-Grafica) - Jan 2016 - [@davidenardi](https://github.com/davidenardi)

Excercise on file saving and loading.
The supermarket UI lets you choose a list of products and store them in a temporary cart. When a new product is added the cart gets automatically saved in a file.
When the applet is closed and re-opened the user can press a button to load a previous cart from the file.

### [Slot machine](https://github.com/Acerkacke/SlotMachine) - Feb-Mar 2016 - [@Davidefedato](https://github.com/Davidefedato) [@kawkabwassim](https://github.com/kawkabwassim)

Threads exercise.
When the lever on the graphics interface gets clicked the three wheels start spinning for a random amount of time, with the first one from the left stopping first, and the one on right last.
It's not completely random because there was a hard-coded high probability of winning a small amount of money every few spins.
The three wheels are controlled by three threads that change the image on the screen, when they stop spinning a closing method is called that checks the outcome of the spin.

### Thread concurrency

Exercises on how to use multiple threads and make sure they don't interfere with each other.

#### [Pizzeria](https://github.com/Acerkacke/PizzeriaClasse) - Nov 2016

A graphics interface lets you order a list of pizzas which are put in the oven that can hold a maximum amount of pizzas.
The target was to control the oven making sure it is fully exploited and that it doesn't exceed the maximum amount of pizzas.

#### Bank - Dec 2016

Multiple threads write and read the value of a bank account from a single instance of it. To do it without interfering the methods to edit the account value are `synchronized`.

### Generic sockets

Exercises on web socket communication between pcs or in the same pc.

#### Chat - Dec 2016

A simple chat where strings are passed through the socket and displayed in both UIs. Personal addition was the "online" or "last login" indicator.

#### Calculator - Dec 2016

Numbers and operations are sent through the socket, solved by the server and the result is returned to the client.

#### Guess party - Jan 2017

This game requires two players: one selects an item and the other one must send its guesses. The player with the earliest correct guesses wins.

#### Blackjack - May 2017

Easy blackjack game, a client at a time could join the server and ask for cards, the game finishes when the client stops or the sum of the cards goes over 21.

### Carsharing - Jan 2017

Exercise on the database connection.
A "carsharing agency" that handles users and rentals using a shared database. A user can rent a car for a fixed amount of time and can return it before expiration.

## Android

Part of our program was learning the basic notions of Android and Android Studio.

### Whack-a-mole - Mar 2016

A thread handles a single mole on the screen that pops up and becomes clickable. If the player clicks on a hole it loses.
