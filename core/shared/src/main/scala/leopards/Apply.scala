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
    def <*>(fa: F[A]): F[B]

  extension [T <: NonEmptyTuple](tuple: T)(using toMap: Tuple.IsMappedBy[F][T])
    inline def mapN[B](f: Tuple.InverseMap[T, F] => B): F[B] =
      tuple.tupled.map(f)

    inline def tupled: F[Tuple.InverseMap[T, F]] =
      tupledGeneric(toMap(tuple))

  // A helper for pattern-matching, which lets us unify type variables in a `def` with
  // the variables in pattern-match cases
  private case class IsMap[T <: Tuple](value: Tuple.Map[T, F])
  
  // We can't propagate the `T <: NonEmptyTuple` constraint here because the `IsMappedBy`
  // implicit doesn't preserve it
  private inline def tupledGeneric[T <: Tuple](tuple: Tuple.Map[T, F]): F[T] =
    inline IsMap(tuple) match
      case t: IsMap[h *: EmptyTuple] => t.value.head.map(_ *: EmptyTuple)
      case t: IsMap[h *: t] => 
        val head =  t.value.head
        val tail = tupledGeneric(t.value.tail)
        head.map2(tail)(_ *: _) 

  extension [A](fa: F[A])
    def map2[B, Z](fb: F[B])(f: (A, B) => Z): F[Z] =
      fa.product(fb).map(f.tupled)

    override def product[B](fb: F[B]): F[(A, B)] =
      fa.map(a => (b: B) => (a, b)) <*> fb

    @alpha("productL") def <*[B](fb: F[B]): F[A] =
      fa.map2(fb)((a, _) => a)

    @alpha("productR") def *>[B](fb: F[B]): F[B] =
      fa.map2(fb)((_, b) => b)
