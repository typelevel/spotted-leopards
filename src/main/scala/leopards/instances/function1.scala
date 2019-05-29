/* Disabled for now as it causes ambiguous resoultion for things like `List(1, 2) *> List(3, 4)`
package leopards

implied Function1Monad[A] for Monad[[X] => Function1[A, X]] {
  def (b: B) pure[B]: A => B = _ => b
  def (fb: A => B) flatMap[B, C](f: B => A => C): A => C =
    a => f(fb(a))(a)
}
*/