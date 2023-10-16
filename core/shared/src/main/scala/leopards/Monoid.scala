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

trait Monoid[A] extends Semigroup[A]:
  def empty: A

  extension (as: IterableOnce[A])
    def combineAll: A =
      as.foldMap(identity)(using this)

extension [A](as: IterableOnce[A])
  def foldMap[B](f: A => B)(using m: Monoid[B]): B =
    as.iterator.foldLeft(m.empty)((acc, a) => acc |+| f(a))
