# high-school-work

Projects, scripts, and other resources I produced in high school.

## Native Java

Most of my work in high school has been done in Java.

### [Slot machine](https://github.com/Acerkacke/SlotMachine) - Feb-Mar 2016 - [@Davidefedato](https://github.com/Davidefedato) [@kawkabwassim](https://github.com/kawkabwassim)

Threads exercise.
When the lever on the graphics interface gets clicked the three wheels start spinning for a random amount of time, with the first one from the left stopping first, and the one on right last.
It's not completely random because there was a hard-coded high probability of winning a small amount of money every few spins.
The three wheels are controlled by three threads that change the image on the screen, when they stop spinning a closing method is called that checks the outcome of the spin.


### [Pizzeria](https://github.com/Acerkacke/PizzeriaClasse) - Nov 2016

Exercise on thread concurrency.
A graphics interface lets you order a list of pizzas which are put in the oven that can hold a maximum amount of pizzas.
The target was to control the oven making sure it is fully exploited and that it doesn't exceed the maximum amount of pizzas.


### [Supermarket](https://github.com/Acerkacke/Prodotti-Grafica) - Jan 2016 - [@davidenardi](https://github.com/davidenardi)

Excercise on file saving and loading.
The supermarket UI lets you choose a list of products and store them in a temporary cart. When a new product is added the cart gets automatically saved in a file.
When the applet is closed and re-opened the user can press a button to load a previous cart from the file.
