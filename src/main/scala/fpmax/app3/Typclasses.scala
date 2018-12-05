package fpmax.app3

object Typclasses {

  trait Chainable[F[_]] {
    def create[A](a: A): F[A]

    def map[A, B](fa: F[A], f: A => B): F[B]

    def flatMap[A, B](fa: F[A], f: A => F[B]): F[B]
  }

  object Chainable {

    // Chainable[Recipe].map(...)
    def apply[F[_]](implicit instance: Chainable[F]): Chainable[F] = instance

    // recipe.flatMap(...)
    implicit class ChainableSyntax[F[_]: Chainable, A](input: F[A]) {
      def map[B](f: A => B): F[B] = Chainable[F].map(input, f)

      def flatMap[B](f: A => F[B]): F[B] = Chainable[F].flatMap(input, f)
    }

  }

  trait Console[F[_]] {
    def putLine(line: String): F[Unit]

    def getLine(): F[String]
  }

  object Console {
    def apply[F[_]](implicit instance: Console[F]): Console[F] = instance
  }

  trait Random[F[_]] {
    def generateRandom(upperBound: Int): F[Int]
  }

  object Random {
    def apply[F[_]](implicit instance: Random[F]): Random[F] = instance
  }

}
