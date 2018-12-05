package fpmax.app4

import Typclasses.{ Chainable, Console, Random }
import Typclasses.Chainable.ChainableSyntax
import ConsoleMessage._

import scala.util.Try

object AfterMessageObjects {

  case class Recipe[A](execute: () => A)

  def parseInt(input: String): Option[Int] =
    Try { input.toInt }.toOption

  def putLine[F[_]: Console](line: ConsoleMessage): F[Unit] =
    Console[F].putLine(line)

  def getLine[F[_]: Console](): F[String] =
    Console[F].getLine()

  def generateRandom[F[_]: Random](upperBound: Int): F[Int] =
    Random[F].generateRandom(upperBound)

  def main(args: Array[String]): Unit = {
    import RecipeInstances._

    mainRecipe[Recipe]().execute()
  }

  def mainRecipe[F[_]: Chainable: Console: Random](): F[Unit] =
    for {
      _    <- putLine(WhatIsYourName)
      name <- getLine()
      _    <- putLine(WelcomeToGame(name))
      _    <- gameLoop(name)
    } yield ()

  def gameLoop[F[_]: Chainable: Console: Random](name: String): F[Unit] =
    for {
      num   <- generateRandom(5).map(_ + 1)
      _     <- putLine(PleaseGuess(name))
      guess <- getLine().map(parseInt)
      _     <- printResult(name, num, guess)
      _     <- putLine(DoYouWantToContinue(name))
      cont  <- shouldContinue()
      _ <- if (cont) gameLoop(name)
          else Chainable[F].create(())
    } yield ()

  private def printResult[F[_]: Console](name: String, num: Int, guess: Option[Int]): F[Unit] =
    putLine(resultMessage(name, num, guess))

  private def resultMessage(name: String, num: Int, guess: Option[Int]): ConsoleMessage =
    if (guess.contains(num)) YouGuessedRight(name)
    else YouGuessedWrong(name, num)

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
