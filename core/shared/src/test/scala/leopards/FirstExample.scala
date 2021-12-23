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
    assertEquals((Option(1), Option(2), Option(3)).mapN(_ + _ + _), Option(6))
  }

  test("coherence") {
    def increment[F[_]: Monad: Traverse](fa: F[Int]): F[Int] = fa.map(_ + 1)
    assertEquals(increment(List(1, 2, 3)), List(2, 3, 4))
  }
