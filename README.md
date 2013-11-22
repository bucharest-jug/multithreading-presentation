Multithreading Concepts
=======================

This is sample code from my presentation at [BJUG #18](http://www.bjug.ro/editii/18.html).

Contents:

- [example1](src/main/java/example1/) is about the perils of atomicity and visibility or lack thereof
- [example2](src/main/java/example2/) is about [ThreadLocals](src/main/java/example2/ThreadLocalsMain.java) and [ThreadPools](src/main/java/example2/ThreadingPoolsMain.java) usage
- [example3](src/main/java/example3/) is about the implementation of a non thread-safe stack
- [example4](src/main/java/example4/) is about improving that stack for thread-safety and with proper encapsulation for `pop()`
- [example5](src/main/java/example5/) is an implementation of an immutable (persistent) Stack
- [example6](src/main/java/example6/) builds on the previously implemented immutable Stack for implementing an [immutable/persistent Queue](src/main/java/example6/ImmutableQueue.java) and a corresponding [mutable non-blocking Queue](src/main/java/example6/MutableQueue.java), contrasting the easiness with which this is achieved by means of persistent data-structures, in contrast with the classic [Maged M. Michael and Michael L. Scott](http://www.research.ibm.com/people/m/michael/podc-1996.pdf) algorithm for non-blocking queues
- [example7](src/main/java/example7/) builds on that non-blocking queue, providing the implementation for simple [Actors](src/main/java/example7/Actor.java), somewhat similar to what more potent libraries are doing (e.g. [Akka](http:/akka.io/))
- [example8](src/main/java/example8/) contrasts the difference between reads and writes, by building a very simple [in-memory cache](src/main/java/example8/Cache.java), in which the lock used is a [ReentrantReadWriteLock](http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html)
