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
    assertEquals(OptionT.mapK[List, Int](x)(fk).value, Some(Some(3)))

    // x.mapK(fk)
    //
    // Fails to compile with:
    // [error] -- [E007] Type Mismatch Error: spotted-leopards/src/test/scala/leopards/OptionTTest.scala:71:11 
    // [error] 71 |    x.mapK(fk)
    // [error]    |           ^^
    // [error]    |Found:    (fk : leopards.FunctionK[List, Option])
    // [error]    |Required: leopards.FunctionK[F, G]
    // [error]    |
    // [error]    |where:    F is a type variable with constraint >: List and <: [_$6] =>> Any
    // [error]    |          G is a type variable with constraint <: [_$7] =>> Any
  }

  test("liftK") {
    assertEquals(OptionT.liftK[List](List(42)), OptionT.liftF(List(42)))
  }

  test("map") {
    val x = OptionT.fromOption[List, Int](Some(42))
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
