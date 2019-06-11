/* Disabled for now: https://github.com/typelevel/spotted-leopards/issues/2
package leopards

delegate [A] for Monad[[X] =>> A => X] {
  def (b: B) pure[B]: A => B = _ => b
  def (fb: A => B) flatMap[B, C](f: B => A => C): A => C =
    a => f(fb(a))(a)
}
*/
