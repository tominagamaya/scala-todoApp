package models

case class Fib(num: Long)

object Fib {

  def makeFib(num: Long): Long = {
    fib2(num)
  }

  //処理速度が遅い
  def fib(n: Long): Long = n match {
    case 0 => 0
    case 1 => 1
    case n => fib(n - 2) + fib(n - 1)
  }

  //末尾再帰
  def fib2(n: Long): Long = {
    if (n <= 0) {
      return 0
    }
    def loop(i: Long, a: Long, result: Long): Long =
      i match {
        case i if (n <= i) => result
        case _ => loop(i + 1, result, result + a)
      }
    loop(1, 0, 1)
  }
}
