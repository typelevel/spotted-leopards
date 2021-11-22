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
import leopards.{~>, OptionT}

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
