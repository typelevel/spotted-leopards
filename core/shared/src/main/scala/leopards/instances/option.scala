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

given stdOptionInstances: Monad[Option] with Traverse[Option] with
  def pure[A](a: A) = Some(a)
  extension[A](fa: Option[A])
    def flatMap[B](f: A => Option[B]) = fa.flatMap(f)
    def traverse[G[_], B](f: A => G[B])(using G: Applicative[G]): G[Option[B]] =
      fa.fold(G.pure(None: Option[B]))(a => f(a).map(Some(_)))
    def foldLeft[B](b: B)(f: (B, A) => B): B = fa.fold(b)(a => f(b, a))
    def foldRight[B](b: B)(f: (A, B) => B): B = fa.fold(b)(a => f(a, b))