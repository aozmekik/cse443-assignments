
**Rule 1** : Detected cases of plagiarism will lead to an immediate failure of this course and
administrative penalties.

**Rule 2** : no late submissions! Even if it is late by one minute, it will be ignored. Learning to
plan your schedule according to deadlines is part of your education and an invaluable
professional asset.

**Rule 3** : I reserve my right to hold an oral examination if I deem it necessary.

**Rule 4:** I will not answer questions when their answer is already in this document.

**What to submit** :
a) the source code of your project fully documented (with javadoc) _,_
b) a pdf report of your **design decision explanations, class diagrams** and **screenshots**
of all of your applications’ operations.
c) an executable demo as a jar archive.

**Java version >= 8**

*******************************************************************************************************
**Question 1:** You will design and implement a visual simulation of an epidemic within a
human society. You can use either Java Swing or FX.

**Context**
The society will be modeled as an empty 2D canvas of size 1000x600 pixels. Each
individual in this society of population P will be modeled as a square of size 5x5 pixels on
this canvas, positioned randomly.

Each individual will move on this 2D canvas along an initially random direction, at a
constant speed of S pixels/second, and when s/he collides with another individual s/he will
spend C seconds with her/him at the collision spot to simulate social interaction with a
social distance D and then both will continue moving at once againt randomized directions.
Nobody can escape the canvas, if an individual reaches the edge of the map, her/his
movement direction should be once again randomized.

```
At the beginning there will be one random infected individual in the population.
```
Each individual will possess a numerical value indicating whether they wear a mask
(M=0.2) or not (M=1.0), their speed S \in [1,5] of movement in pixels/second, the social
distance D \in [0,9] (in pixels) that they practice when they collide with other individuals,
and how social they are in terms of C seconds \in [1,5] they spend with every individual
they collide with.

The disease will have a constant spreading factor R \in [0.5,1.0] and a constant
mortality rate Z \in [0.1, 0.9].


When two individuals with coefficients C_1 and C_2 collide they stay together (at
collision position) for time C=max{C_1,C_2} to simulate interaction and then continue their
randomized courses. If another individual is in collision course with either of them in the
meantime, s/he cannot interact with them and ignores them as if they weren’t there.

Let two individuals I_1 and I_2 collide, with mask statuses M_1 and M_2, and social
distances D_1 and D_2 respectively. Let I_1 be infected and I_2 be healthy. They stay
together for a duration C=max{C_1,C_2} before parting, and the social distance between
them is D=min{D_1,D_2}. The probability of I_1 infecting I_2 is P = R * (1+C/10) * M_1 *
M_2 * (1-D/10)

- An infected individual will die after 100 * (1-Z) seconds and disappear from the canvas.
- Update the canvas every second or less; provide a timer, and show the total count of
infected, healthy, hospitalized and dead.
- Every infected individual, 25 seconds after her/his initial infection will be assumed to be
at the hospital and will be removed temporarily from the canvas. The hospital however is
assumed to have only B=P/100 ventilators. After staying at the hospital for 10 seconds
s/he will return to the society at a random position as healthy. If the hospital ventilators are
all full the individual will remain and continue moving/infecting in the society, until a
ventilator becomes available or...s/he dies. The hospital is assumed to be able to cure all
cases.

**Goals**
1) Create the individuals using some design pattern. Choose wisely and justify your
decision. Some are more appropriate than others. Strive for maintenance cost reduction,
and flexibility. The user should be able to add them in bulk as well as one by one. **(
points)**
2) Model and implement the interaction between individuals using the Mediator design
pattern. **(25 points)**
3) Make sure your GUI is multi-threaded, always responsive. Allow the user to pause and
continue the simulation. **(25 points)**
4) Use the producer/consumer paradigm to implement the hospital functionality and pay
attention to synchronization. **(10 points)**
4) Produce graphical plots of how the infected and dead count is affected across time
depending on various chosen values of R, Z, percentage of mask use and average social
distance D in the population. How do these outcomes relate to the population P? Linearly?
Exponentially? Something else? **(10 points)**
5) Evaluation of your submitted report in terms of clarity, comprehensiveness and
presentation. **(10 points)**

Good luck.


