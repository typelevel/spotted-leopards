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

import scala.util.{Try, Failure, Success}
import scala.util.control.NonFatal

given stdTryInstances: ApplicativeError[Try, Throwable] with
  override def pure[A](a: A): Try[A] = Try(a)
  override  def raiseError[A](e: Throwable): Try[A] = Failure(e)
  
  extension [A](fa: Try[A])
    override def map[B](f: A => B): Try[B] = fa.map(f)
    override def handleError(f: Throwable => A): Try[A] = fa.recover[A] { case e => f(e) }
  
  extension [A, B](ff: Try[A => B])
    override def <*>(fa: Try[A]): Try[B] =
      ff.flatMap(f => fa.map(f))