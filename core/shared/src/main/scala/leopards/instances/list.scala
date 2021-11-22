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

given stdListInstances: Monad[List] with Traverse[List] with
  def pure[A](a: A) = List(a)
  extension[A](fa: List[A])
    def flatMap[B](f: A => List[B]) = fa.flatMap(f)
    def traverse[G[_], B](f: A => G[B])(using G: Applicative[G]): G[List[B]] =
      fa.foldRight(G.pure(Nil: List[B]))((a, acc) => f(a).map2(acc)(_ :: _))
    def foldLeft[B](b: B)(f: (B, A) => B): B = fa.foldLeft(b)(f)
    def foldRight[B](b: B)(f: (A, B) => B): B = fa.foldRight(b)(f)
