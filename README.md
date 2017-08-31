# Philosopher's Stone

The Philosopher's Stone is an object upon which a software architecture called the Philosopher's Atlas is composed.
The Philosopher's Atlas allows for greater fault tolerance and modularity in sofware development.

## Contents of this repository

This repository contains documentation and APIs for the Philosopher's Stone.

### Direct links

[Documentation](https://github.com/Gallery-of-Kaeon/Philosophers-Stone/blob/master/Philosopher's%20Stone/Documentation/README.md)

[APIs](https://github.com/Gallery-of-Kaeon/Philosophers-Stone/tree/master/Philosopher's%20Stone/API)

## Contact info

For any questions or comments please email the following address:

kaeon.ace@gmail.com

## FAQs

### How does it work?

Individual Philosopher's Stones act as wrappers for components of larger systems.

Philosopher's Stones may be connected to one another to form a graph called a Philosopher's Atlas.
Connections between Philosopher's Stones are one way and may either be public or private.

Once connected, the Philosopher's Stones may communicate with each other by sending packets throughout the graph.

### What advantages are there to using Philosopher's Stones?

Philosopher's Stones allow the various components of an application to be isolated.
Entire components of your application can be added and removed without causing the application to crash.
You can even program parts of your program to interact with other parts that haven't been implemented yet.

For example,
lets say stone A activates a function in stone B by sending a packet to it.
If you decide to modify what happens after the packet is sent,
you can replace stone B with stone C without modifying stone A.