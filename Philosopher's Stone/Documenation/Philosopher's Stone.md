# Philosopher's Stone

The Philosopher's Stone is a special type of graph node acting as an interface between an isolated system and a larger system.

## Core Functionality

Philosopher's Stones may be connected to each other in graphs called a Philosopher's Atlas,
using one-way connections that may either be public or private.
Two stones that both have a one-way connection to the other are said to have a mutual connection.

Once connected, stones can communicate with other stones in the atlas by sending out packets across the graph using the "call" function.
When a packet is received,
a stone can process it using its unique "on call" function.

### Call Function

The call function is common to all Philosopher's Stones.

After taking a packet in the form of an undefined object,
it starts a traversal of the atlas beginning at the stone it was called upon.

It will traverse across the public and private connections of the stone it starts at,
and from that point onward will only traverse across the public connections of the stones it traverses across.
It will not traverse across the same stone twice.

At every stone it traverses across,
it will call the stone's on call function using the packet as an argument.
If the on call function returns anything other than a null value,
it will append the returned object to a list,
which it will return after finishing its traversal.

### On Call Function

The on call function is common to also Philosopher's Stones,
but implemented diferently on each one.

It takes a packet in the form of an undefined object as an argument.

Based on the content of the packet,
it will perform operations and return an undefined object.

By default,
it performs no operations and returns a null value.