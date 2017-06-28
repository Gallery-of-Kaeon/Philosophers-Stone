# Philosopher's Stone

The Philosopher's Stone is a special type of graph node acting as an interface between an isolated system and a larger system.

## Core Functionality

Philosopher's Stones may be connected to each other in graphs called a Philosopher's Atlas,
using one-way connections that may either be public or private.
Two stones that both have a one-way connection to the other are said to have a mutual connection.

Once connected, stones can communicate with other stones in the atlas by sending out packets across the graph using the call function.
When a packet is received,
a stone can process it using its unique on call function.

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

## API Functionality

While the core functionality dictates the bare minimum functionality an object must have to classify as a Philosopher's Stone,
the Philosopher's Stone API provides additional functionality.

### Tags

The Philosopher's Stone API allows Philosopher's Stones to store a list of strings that act as tags for identification.
Letter case and whitespace in the tags is irrelevant.

### Functions

#### Publicly Connect

The publicly connect function takes a reference to a Philosopher's Stone,
and establishes a public connection from the stone it was called from to the referrenced stone.

#### Privately Connect

The privately connect function takes a reference to a Philosopher's Stone,
and establishes a private connection from the stone it was called from to the referrenced stone.

#### Publicly Connect Mutually

The publicly connect mutually function takes a reference to a Philosopher's Stone,
and establishes a public connection from the stone it was called from to the referrenced stone,
and a public connection from the referrenced stone to the stone it was called on.

#### Privately Connect Mutually

The privately connect mutually function takes a reference to a Philosopher's Stone,
and establishes a private connection from the stone it was called from to the referrenced stone,
and a private connection from the referrenced stone to the stone it was called on.

#### Disconnect

The disconnect function takes a referrence to a Philosopher's Stone,
and removes any connection from the Philosopher's Stone it was called from to the referrenced stone.

#### Disconnect Mutually

The disconnect function takes a referrence to a Philosopher's Stone,
and removes any connection from the Philosopher's Stone it was called from to the referrenced stone.
and removes any connection from the referrenced stone to the Philosopher's Stone it was called from.

#### Is Connected

The is connected function takes a referrence to a Philosopher's Stone if the stone it was called on is connected to the referrenced stone.

#### Is Publicly Connected

The is publicly connected function takes a referrence to a Philosopher's Stone if the stone it was called on is publicly connected to the referrenced stone.

#### Is Privately Connected

The is privately connected function takes a referrence to a Philosopher's Stone if the stone it was called on is privately connected to the referrenced stone.

#### Tag

The tag function takes a string and assigns it as a tag to the Philosopher's Stone it was called from.

#### Detag

The detag function takes a string and removes any tag that matches it from the Philosopher's Stone it was called from.

#### Get

The get function takes a list of strings,
each representing a tag,
and traverses the atlas,
returning a list of all stones that have each tag.

#### Has

The has function takes a list of strings,
each representing a tag,
and traverses the atlas,
returning true if a stone is present that has each tag,
and returning false otherwise.