package leopards.testsyntax

import munit.FunSuite

import leopards.{*, given}

class SyntaxExample extends FunSuite:

  test("f poly summon ") {
    def fPolySummon[F[_]: Monad](value: Int): F[Int] = Monad[F].pure(42)
    assertEquals(fPolySummon[Option](42), Some(42))
  }

  test("f poly flatMap") {
    // for the flatMap and value.pure[F] we need the leopards.given import
    def fPolyFlatMap[F[_]: Monad](value: Int): F[Int] = 
      for {
        v <- value.pure[F]
      } yield v
    
    assertEquals(fPolyFlatMap[Option](42), Some(42))
  }

  test("applicative error syntax") {
    import scala.util.Try
    val thr = new RuntimeException("stuff")
    //val tried = thr.raiseError[Try, String] // -- does not compile because thr is inferred as RuntimeException, not Throwable
    val tried = (thr: Throwable).raiseError[Try, String]
    assert(tried.isFailure)
    assertEquals(tried.handleError(_ => "42"), Try("42"))
  }
