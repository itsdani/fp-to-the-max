package fpmax.app4

sealed trait ConsoleMessage {
  def en: String
}

object ConsoleMessage {
  case class YouGuessedRight(name: String) extends ConsoleMessage {
    def en: String = "You guessed right, " + name + "!"
  }
  case class YouGuessedWrong(name: String, num: Int) extends ConsoleMessage {
    def en: String = "You guessed wrong, " + name + "! The number was: " + num
  }
  case class DoYouWantToContinue(name: String) extends ConsoleMessage {
    def en: String = "Do you want to continue, " + name + "?"
  }
  case class PleaseGuess(name: String) extends ConsoleMessage {
    def en: String = "Dear " + name + ", please guess a number from 1 to 5:"
  }
  case class ThatIsNotValid(name: String) extends ConsoleMessage {
    def en: String = "That is not a valid selection, " + name + "!"
  }
  case object WhatIsYourName extends ConsoleMessage {
    def en: String = "What is your name?"
  }
  case class WelcomeToGame(name: String) extends ConsoleMessage {
    def en: String = "Hello, " + name + ", welcome to the game!"
  }
}
