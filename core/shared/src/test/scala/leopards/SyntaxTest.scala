/*
 * Copyright 2019 Typelevel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package leopardstest

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
