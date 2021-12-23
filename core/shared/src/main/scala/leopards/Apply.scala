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

import scala.annotation.alpha

trait Apply[F[_]] extends Functor[F], Semigroupal[F]:
  extension [A, B](ff: F[A => B])
    @alpha("ap")
    def <*> (fa: F[A]): F[B]

  // note: we should be able to take `Tuple.IsMappedBy[F]` constraint here but https://github.com/lampepfl/dotty/issues/14165
  extension [T <: NonEmptyTuple](t: T)
    def mapN[B](using Tuple.IsMappedBy[F][T])(f: Tuple.InverseMap[T, F] => B): F[B] =
      t.tupled.map(f)

    def tupled(using Tuple.IsMappedBy[F][T]): F[Tuple.InverseMap[T, F]] =
      def loop[X <: NonEmptyTuple](x: X): F[NonEmptyTuple] = x match
        case hd *: EmptyTuple => hd.asInstanceOf[F[Any]].map(_ *: EmptyTuple)
        case hd *: (tl: NonEmptyTuple) => hd.asInstanceOf[F[Any]].map2(loop(tl))(_ *: _)
      loop(t).asInstanceOf[F[Tuple.InverseMap[T, F]]]

  extension [A](fa: F[A])
    def map2[B, Z](fb: F[B])(f: (A, B) => Z): F[Z] =
      fa.product(fb).map(f.tupled)

    override def product[B](fb: F[B]): F[(A, B)] =
      fa.map(a => (b: B) => (a, b)) <*> fb

    @alpha("productL") def <*[B](fb: F[B]): F[A] =
      fa.map2(fb)((a, _) => a)

    @alpha("productR") def *>[B](fb: F[B]): F[B] =
      fa.map2(fb)((_, b) => b)
