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

extension [A](a: A)
  inline def pure[F[_]](using F: Applicative[F]): F[A] = F.pure(a)
  inline def raiseError[F[_], B](using F: ApplicativeError[F, A]): F[B] = F.raiseError[B](a)
