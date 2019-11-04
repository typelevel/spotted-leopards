package leopards

import org.junit.Test
import org.junit.Assert._

class FirstExample {
  @Test def listProductR(): Unit = {
    assertEquals(List(1, 2) *> List(3, 4), List(3, 4, 3, 4))
  }

  @Test def listVoid(): Unit = {
    assertEquals(List(1, 2, 3).void, List((), (), ()))
  }

  @Test def listSequence(): Unit = {
    // Doesn't work b/c List[Some[Int]] is inferred and there's no Applicative[Some]
    // assertEquals(List(Some(1), Some(2)).sequence, Some(List(1, 2)))
    assertEquals(List(Option(1), Option(2)).sequence, Some(List(1, 2)))
    assertEquals(List(Option(1), None).sequence, None)
  }
}
