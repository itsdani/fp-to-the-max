package fpmax

object Intro {

  /*
   * FP to the MAX!
   *
   * (John de Goes, Fun(c)tional Programming Group)
   *  https://www.youtube.com/watch?v=sxudIMiOo68
   */

  /*
   *   "Functional" functions:
   *     (1) deterministic - returns the same value for the same input
   *     (2) total - always returns a value, for any input
   *     (3) pure - they only effect is computing the return value
   */

  /*
   *   Benefits:
   *      * No hidden state-changes!
   *      * Inversion of control - the caller has the control over caller
   *      * Equational reasoning
   *      * Refactor without worry
   *      * Easier tests
   *      * Type based reasoning
   */

  object Examples {
    def function1(input: String): Unit     = ???
    def function2(): String                = ???
    def function3(input1: Segment, input2: Segment): Segment = ???

    trait Segment {
      def union(other: Segment): Segment
      def intersect(other: Segment): Segment
    }
  }



  object Examples2 {
    def doSomething1(input: Int): Int = ???

    def doSomething2[A](input: A): A = ???
  }

  object Examples3 {
    def doSomething3[A: Number](input: A): A = ???

    trait Number[A] {
      def add(first: A, second: A): A
    }

    object Number {
      def apply[A](implicit number: Number[A]): Number[A] = number
    }

    object NumberInstances {
      implicit val intInstance: Number[Int] = new Number[Int] {
        override def add(first: Int, second: Int): Int = first + second
      }
      implicit val floatInstance: Number[Float] = new Number[Float] {
        override def add(first: Float, second: Float): Float = first + second
//        override def add(first: Float, second: Int): Float   = first + second
      }
      implicit val doubleInstance: Number[Double] = new Number[Double] {
        override def add(first: Double, second: Double): Double = first + second
//        override def add(first: Double, second: Int): Double    = first + second
      }
    }
  }
}
