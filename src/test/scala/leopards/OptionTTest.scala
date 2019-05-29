package leopards

import org.junit.Test
import org.junit.Assert._

class OptionTTest {
  @Test def fromOption(): Unit = {
    val x = OptionT.fromOption[F = List](Some(42))
    assertEquals(x.value, List(Some(42)))
  }

  @Test def map(): Unit = {
    val x = OptionT.fromOption[F = List](Some(42))
// https://github.com/typelevel/spotted-leopards/issues/1
//     x.map(_ + 1)
//     // Fails to compile with:
//     // [error] -- [E008] Member Not Found Error: spotted-leopards/src/test/scala/leopards/OptionTTest.scala:9:6 
//     // [error]  9 |    x.map(_ + 1)
//     // [error]    |    ^^^^^
//     // [error]    |value map is not a member of leopards.OptionT.OptionT[List, Int] - did you mean x.==?.
//     // [error]    |An extension method was tried, but could not be fully constructed:
//     // [error]    |
//     // [error]    |    leopards.OptionT$package.OptionT.FunctorInstance[([_$15] => Any)](
//     // [error]    |      /* ambiguous */implicitly[leopards.Functor[([_$15] => Any)]]
//     // [error]    |    ).map()
//     assertEquals(x.value, List(Some(43)))
  }
}