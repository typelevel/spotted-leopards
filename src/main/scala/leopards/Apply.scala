package leopards

import scala.annotation.alpha

trait Apply[F[_]] extends Functor[F], Semigroupal[F]:
  extension [A, B](ff: F[A => B])
    @alpha("ap")
    def <*> (fa: F[A]): F[B]

  def mapN[T <: NonEmptyTuple : Tuple.IsMappedBy[F], B](t: T)(f: Tuple.InverseMap[T, F] => B): F[B] =
    tupled(t).map(f)

  def tupled[T <: NonEmptyTuple : Tuple.IsMappedBy[F], B](t: T): F[Tuple.InverseMap[T, F]] =
    def loop[X <: Tuple](x: X): F[Tuple] = x match
      case hd *: EmptyTuple => hd.asInstanceOf[F[Any]].map(_ *: EmptyTuple)
      case hd *: tl => hd.asInstanceOf[F[Any]].map2(loop(tl))(_ *: _)
      case EmptyTuple => sys.error("impossible")
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
