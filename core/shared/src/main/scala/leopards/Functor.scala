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

trait Functor[F[_]]:
  extension [A](fa: F[A])
    def map[B](f: A => B): F[B]
    def as[B](b: B): F[B] = map(_ => b)
    def void: F[Unit] = as(())
  def lift[A, B](f: A => B): F[A] => F[B] =
    _.map(f)

object Functor:
  export leopards.stdListInstances
  export leopards.stdOptionInstances
  export leopards.stdTryInstances
  export leopards.stdFunction1Instances
