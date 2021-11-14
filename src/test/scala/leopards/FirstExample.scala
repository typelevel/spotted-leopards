package leopards

import munit.FunSuite

class FirstExample extends FunSuite:
  test("listProductR") {
    assertEquals(List(1, 2) *> List(3, 4), List(3, 4, 3, 4))
  }

  test("listVoid") {
    assertEquals(List(1, 2, 3).void, List((), (), ()))
  }

  test("listSequence") {
    // Doesn't work b/c List[Some[Int]] is inferred and there's no Applicative[Some]
    // assertEquals(List(Some(1), Some(2)).sequence, Some(List(1, 2)))
    assertEquals(List(Option(1), Option(2)).sequence, Some(List(1, 2)))
    assertEquals(List(Option(1), None).sequence, None)
  }

  test("mapN") {
    assertEquals(summon[Applicative[Option]].mapN(Option(1), Option(2), Option(3))(_ + _ + _), Option(6))
  }
