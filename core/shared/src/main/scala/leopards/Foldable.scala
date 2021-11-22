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

package leopards

trait Foldable[F[_]]:
  extension [A](fa: F[A])
    def foldLeft[B](b: B)(f: (B, A) => B): B
    def foldRight[B](b: B)(f: (A, B) => B): B
    def foldMap[B](f: A => B)(using B: Monoid[B]): B =
      foldRight(B.empty)((a, b) => f(a) |+| b)

object Foldable:
  export leopards.stdListInstances
  export leopards.stdOptionInstances
  export leopards.stdTryInstances
  export leopards.stdFunction1Instances