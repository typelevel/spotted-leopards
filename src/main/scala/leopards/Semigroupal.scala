/* Derived in part from Cats -- see https://github.com/typelevel/cats/blob/master/COPYING for full license & copyright. */
package leopards

trait Semigroupal[F[_]]:
  extension [A] (fa: F[A])
    def product[B](fb: F[B]): F[(A, B)]
