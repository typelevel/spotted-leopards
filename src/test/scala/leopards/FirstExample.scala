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
}