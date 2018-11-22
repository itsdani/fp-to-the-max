package fpmax.app2

import scala.io.StdIn.readLine
import scala.util.Try

object AfterRecipes {

  case class Recipe[A](execute: () => A) { self =>

    def map[B](f: A => B): Recipe[B] =
      Recipe(() => f(self.execute()))

    def andThen[B](f: A => Recipe[B]): Recipe[B] =
      Recipe(() => f(self.execute()).execute())

    def flatMap[B](f: A => Recipe[B]): Recipe[B] =
      andThen(f)

  }

  def parseInt(input: String): Option[Int] =
    Try { input.toInt }.toOption

  def putLine(line: String): Recipe[Unit] =
    Recipe(() => println(line))

  def getLine(): Recipe[String] =
    Recipe(() => readLine())

  def generateRandom(upperBound: Int): Recipe[Int] =
    Recipe(() => scala.util.Random.nextInt(upperBound))

  def main(args: Array[String]): Unit = mainRecipe().execute()

  def mainRecipe(): Recipe[Unit] =
    for {
      _    <- putLine("What is your name?")
      name <- getLine()
      _    <- putLine("Hello, " + name + ", welcome to the game!")
      _    <- gameLoop(name)
    } yield ()

  def gameLoop(name: String): Recipe[Unit] =
    for {
      num   <- generateRandom(5).map(_ + 1)
      _     <- putLine("Dear " + name + ", please guess a number from 1 to 5:")
      guess <- getLine().map(parseInt)
      _     <- printResult(name, num, guess)
      _     <- putLine("Do you want to continue, " + name + "?")
      cont  <- shouldContinue()
      _ <- if (cont) gameLoop(name)
          else Recipe(() => ())
    } yield ()

  private def printResult(name: String, num: Int, guess: Option[Int]): Recipe[Unit] =
    if (guess.contains(num)) putLine("You guessed right, " + name + "!")
    else putLine("You guessed wrong, " + name + "! The number was: " + num)

  def shouldContinue(): Recipe[Boolean] =
    for {
      answer <- getLine()
      cont <- answer match {
               case "y" => Recipe(() => true)
               case "n" => Recipe(() => false)
               case _   => shouldContinue()
             }
    } yield cont

}
