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

trait Traverse[F[_]] extends Functor[F], Foldable[F]:
  extension [A](fa: F[A])
    def traverse[G[_], B](f: A => G[B])(using G: Applicative[G]): G[F[B]]
  extension [G[_], A](fga: F[G[A]])
    def sequence(using Applicative[G]): G[F[A]] = fga.traverse(identity)
