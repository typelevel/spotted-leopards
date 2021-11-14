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

opaque type OptionT[F[_], A] = F[Option[A]]

object OptionT:
  def apply[F[_], A](o: F[Option[A]]): OptionT[F, A] = o

  def fromOption[F[_], A](oa: Option[A])(using F: Applicative[F]): OptionT[F, A] =
    apply(F.pure(oa))

  def liftF[F[_], A](fa: F[A])(using Functor[F]): OptionT[F, A] =
    apply(fa.map(Some(_)))

  def liftK[F[_]](using Functor[F]): F ~> ([X] =>> OptionT[F, X]) = 
    [X] => liftF(_: F[X])

  extension [F[_], A](fa: OptionT[F, A])
    def value: F[Option[A]] = fa
    def mapK[G[_]](fk: F ~> G): OptionT[G, A] = fk(fa.value)

  given [F[_]](using F: Functor[F]): Functor[[X] =>> OptionT[F, X]] =
    new OptionTFunctor[F] {}

  private trait OptionTFunctor[F[_]](using F: Functor[F]) extends Functor[[X] =>> OptionT[F, X]]:
    extension [A](ota: OptionT[F, A])
      def map[B](f: A => B) = F.map(ota)(_.map(f))

  given [F[_]](using F: Monad[F]): Monad[[X] =>> OptionT[F, X]] =
    new OptionTFunctor[F] with Monad[[X] =>> OptionT[F, X]]:
      def pure[A](a: A) = F.pure(Some(a))
      extension [A](ota: OptionT[F, A])
        def flatMap[B](f: A => OptionT[F, B]): OptionT[F, B] =
          F.flatMap(ota)(oa => oa.fold(F.pure(None))(f))
