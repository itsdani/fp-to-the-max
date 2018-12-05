package fpmax
import fpmax.app1.Typeclasses.Chainable

object CheatSheet {
  object Chainable {

    def apply[F[_]](implicit instance: Chainable[F]): Chainable[F] = instance

    implicit class ChainableSyntax[F[_]: Chainable, A](input: F[A]) {
      def map[B](f: A => B): F[B] = Chainable[F].map(input, f)

      def flatMap[B](f: A => F[B]): F[B] = Chainable[F].flatMap(input, f)
    }

  }
}
