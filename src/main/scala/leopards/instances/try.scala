package leopards

import scala.util.{Try, Failure, Success}
import scala.util.control.NonFatal

given ApplicativeError[Try, Throwable] with
  override def pure[A](a: A): Try[A] = Try(a)
  override  def raiseError[A](e: Throwable): Try[A] = Failure(e)
  
  extension [A](fa: Try[A])
    override def map[B](f: A => B): Try[B] = fa.map(f)
    override def handleError(f: Throwable => A): Try[A] = fa.recover[A] { case e => f(e) }
  
  extension [A, B](ff: Try[A => B])
    override def <*>(fa: Try[A]): Try[B] =
      ff.flatMap(f => fa.map(f))