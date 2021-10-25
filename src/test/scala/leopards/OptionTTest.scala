package leopards

import munit.FunSuite

class OptionTTest extends FunSuite:
  test("fromOption") {
    val x = OptionT.fromOption[List, Int](Some(42))
    assertEquals(x.value, List(Some(42)))
  }

  test("mapK") {
    val fk: List ~> Option = [X] => (_: List[X]).headOption

    val x: OptionT[List, Int] = OptionT(List(Some(3), Some(4)))
    assertEquals(x.mapK(fk).value, Some(Some(3)))
  }

  test("liftK") {
    assertEquals(OptionT.liftK[List](List(42)), OptionT.liftF(List(42)))
  }

  test("map") {
    val x = OptionT.fromOption[List, Int](Some(42))
    assertEquals(x.map(_ + 1).value, List(Some(43)))
  }
