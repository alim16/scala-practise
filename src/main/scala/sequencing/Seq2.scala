package sequencing
import cats.effect.IO

sealed trait GenList[A]{
    def length():Int = this match {
            case End() => 0
            case Pair(head,tail) => 1 + tail.length
    }
    def contains(item: A):Boolean = this match {
        case End () => false
        case Pair(head,tail) => (item == head) || tail.contains(item)
    }
}
final case class End[A]() extends GenList[A]
final case class Pair[A](head: A, tail:GenList[A]) extends GenList[A]

object doStuff {
    def apply() = IO[Unit]{
        val nums:GenList[Int] = Pair(1,Pair(3,End[Int]))
        val strings:GenList[String] = Pair("test1",Pair("test2",End[String]))
        IO.pure(println(s"""####does nums contain 3:${nums.contains(3)}"""))
        IO.pure(println(s"""####does strings contain "test3":${strings.contains("test3")}"""))
    }
}