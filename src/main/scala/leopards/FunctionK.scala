package leopards

type FunctionK[F[_], G[_]] = [X] => F[X] => G[X]
type ~>[F[_], G[_]] = FunctionK[F, G]
