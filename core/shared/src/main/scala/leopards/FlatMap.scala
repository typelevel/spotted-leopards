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

trait FlatMap[F[_]] extends Apply[F]:
  extension [A](fa: F[A])
    def flatMap[B](f: A => F[B]): F[B]

  extension [A, B](ff: F[A => B])
    override def <*>(fa: F[A]): F[B] =
      ff.flatMap(f => fa.map(f))

  extension [A](ffa: F[F[A]])
    def flatten: F[A] = ffa.flatMap(identity)
