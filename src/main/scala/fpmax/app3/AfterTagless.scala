package fpmax.app3


import fpmax.app3.TypeClasses.{Chainable, Console, Random}
import fpmax.app3.TypeClasses.Chainable.ChainableSyntax

import scala.util.Try

object AfterTagless {

  case class Recipe[A](execute: () => A)

  def parseInt(input: String): Option[Int] =
    Try { input.toInt }.toOption

  def putLine[F[_]: Console](line: String): F[Unit] =
    Console[F].putLine(line)

  def getLine[F[_]: Console](): F[String] =
    Console[F].getLine()

  def generateRandom[F[_]: Random](upperBound: Int): F[Int] =
    Random[F].generateRandom(upperBound)

  def main(args: Array[String]): Unit = {
    import RecipeInstances.chainableRecipe
    import RecipeInstances.consoleRecipe
    import RecipeInstances.randomRecipe

    mainRecipe[Recipe]().execute()
  }

  def mainRecipe[F[_]: Chainable: Console: Random](): F[Unit] =
    for {
      _    <- putLine("What is your name?")
      name <- getLine()
      _    <- putLine("Hello, " + name + ", welcome to the game!")
      _    <- gameLoop(name)
    } yield ()

  def gameLoop[F[_]: Chainable: Console: Random](name: String): F[Unit] =
    for {
      num   <- generateRandom(5).map(_ + 1)
      _     <- putLine("Dear " + name + ", please guess a number from 1 to 5:")
      guess <- getLine().map(parseInt)
      _     <- printResult(name, num, guess)
      _     <- putLine("Do you want to continue, " + name + "?")
      cont  <- shouldContinue()
      _ <- if (cont) gameLoop(name)
      else Chainable[F].create(())
    } yield ()

  private def printResult[F[_]: Console](name: String, num: Int, guess: Option[Int]): F[Unit] =
    putLine(resultMessage(name, num, guess))

  private def resultMessage(name: String, num: Int, guess: Option[Int]): String =
    if (guess.contains(num)) "You guessed right, " + name + "!"
    else "You guessed wrong, " + name + "! The number was: " + num

  def shouldContinue[F[_]: Chainable: Console](): F[Boolean] =
    for {
      answer <- getLine()
      cont <- answer match {
        case "y" => Chainable[F].create(true)
        case "n" => Chainable[F].create(false)
        case _   => shouldContinue()
      }
    } yield cont

}
