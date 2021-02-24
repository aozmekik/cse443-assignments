
**Rule 1** : Detected cases of plagiarism will lead to a penalty of your grade at the end of the semester.

**Rule 2** : no late submissions! Even if it is late by one minute, it will be ignored. Learning to plan
your schedule according to deadlines is part of your education and an invaluable professional asset.

**What to submit** : a) the source code of your project _fully documented (with javadoc),_ b) a nicely
formatted pdf report of your design decision explanations and class diagrams and c) an executable
demo that fully illustrates your program’s capabilities _whenever code is requested_.

**Java version >= 8**

*******************************************************************************
**Question 1 (35 points)** : You have been hired by the company “Numerical Solutions Inc” and you
are in charge of their new project “LinearSolverDeluxe”.

The project’s objective is to design and implement a Java application with a single functionality: it
admits as input from the user a system of linear equations and outputs its solution, if it exists, or an
error message otherwise.

The customer:

- wants the software to support at least two methods of solving linear equations: Gaussian
elimination and matrix inversion.
- wants to be able to change between solving methods dynamically.
- might need more functionalities in the future (e.g. determinant calculation, etc).

You will:

- Provide the class diagrams with the appropriate arrows and contents (in detail; all the methods and
variables involved with the appropriate access rights), that satisfies the customer’s requirements;
strive for maximum flexibility, loose coupling and minimize maintenance costs!
- Implement in Java the above software (user interfacing technique, way of admitting input, way of
outputting, that’s all up to you).

**Question 2 (30 points)** : after the last project, you were fed up with numerical analysis, and on the
spur of the moment, you quit your job. After a few weeks of lurking at your sofa and watching
depressive netflix series, you get a brilliant idea that no one has ever thought of before!

Everybody has their own list of favorite websites. But nobody wants to check them daily n times to
see if they have been updated with new content ( _content = either text, photographs or audio_ )!
Wouldn’t it be great if you could subscribe to your favorite website, and the websites somehow
notified the subscribers of new content? So much wow, such originality! Doge is impressed. You’re
certain that you’ll make millions from this and all major websites will support it!

However, as a seasoned professional (who has never heard of RSS) you must make sure that:

- it’s trivial to add and remove subscribers, as it’ll happen often
- a subscriber can be in the form of any software using your library. So your API must be easy to
use.


- a subscriber might be interested in only new text updates, or photograph updates or audio updates
_or a combination thereof_. There is no need to disturb them if the update is not of the desired type.
What kind of a design would support this?
- What if users or websites demand your software to support a fourth type of content? Will it be
easy to modify? It better be.

You will:

- Provide the class diagrams with the appropriate arrows and contents (in detail, all the methods and
variables involved with the appropriate access rights), that satisfy the design requirements; strive for
maximum flexibility, loose coupling and minimum cost of maintenance!

**Question 3 (35 points):** The last project didn’t do well on the market. You quit freelancing and you
are now employed by ZırhSan A.Ş, a private company specializing in the design and production of
exoskeleton armored suits for military personnel, equipped with various custom weapons.

There are 3 basic types of suits: **dec** (500k TL, 25kg), **ora** (1500k TL, 30kg) and **tor** (5000k TL,
50kg).
Each of these suits can be equipped with the following accessories:

- Flamethrower (50k TL per item, 2k)
- AutoRifle (30k TL per item, 1.5kg)
- RocketLauncher (150k TL per item, 7.5kg)
- Laser (200k TL per item, 5.5kg)

A customer can demand any custom combination of accessories: such as a dec suit with 1
flamethrower, 2 automatic rifles and 1 rocket launcher.

Your task is to develop a piece of software in Java _able to calculate the total cost and weight of an
equipped suit_ (total price = basic suit price + prices of the accessories). The user of the software
should be able to designate any combination of accessories _dynamically_ at runtime. Your design
should be flexible and easy to accommodate new accessories and suit types.


