# Spotted Leopards

Spotted Leopards = Dotty + Cats

Spotted Leopards is a proof of concept for a cats-like library built using Dotty features, with no concern for source compatibility with the existing Cats library. This project has a few goals:
 - explore how new language features in Dotty could be used to improve the encodings of functional type classes and related data types, perhaps informing future Cats / Dotty roadmap
 - provide feedback on Dotty features from the perspective of cats library authors
 - provide a code base of significant size that can be used for learning and exploring Dotty

In short, this project is an *experiment only* and will not in any way become a library that should be used for real work. This project is *NOT* a preview of a future Cats version. Rather, it provides a way to explore the design space of the Dotty language features without the constraints of source compatibility and cross-building expected of Cats proper.

## Getting Started

To get started, launch `sbt console` and run the following:

```scala
scala> import leopards.{*, given} // Import various types like Monad and OptionT and type class instances

scala> Some(1).map2(Some(2), _ + _)
val res0: Option[Int] = Some(3)

scala> OptionT.fromOption[List, Int](res0)
val res1: leopards.OptionT[List, Int] = List(Some(3))
```
