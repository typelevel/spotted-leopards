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

opaque type Kleisli[F[_], A, B] = A => F[B]

extension [F[_], A, B](k: Kleisli[F, A, B])
  def apply(a: A): F[B] = k(a)

object Kleisli:
  def apply[F[_], A, B](f: A => F[B]): Kleisli[F, A, B] = f

given [F[_], A](using F: Monad[F]): Monad[[B] =>> Kleisli[F, A, B]] with
  def pure[B](b: B) = Kleisli(_ => F.pure(b))
  extension[B](k: Kleisli[F, A, B])
    def flatMap[C](f: B => Kleisli[F, A, C]) =
      a => k(a).flatMap(b => f(b)(a))
