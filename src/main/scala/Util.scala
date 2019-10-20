package mo.datastructs

sealed trait ListM[+A]
case object Nil extends ListM[Nothing]
case class Cons [+A] (head: A, tail: ListM[A]) extends ListM[A]

object ListM {
   def sum(ints: ListM[Int]): Int = ints match {
       case Nil => 0
       case Cons(x,xs) => x + sum(xs)
   }

   def product(ints: ListM[Double]): Double = ints match {
       case Nil => 1
       case Cons(0.0,_) => 0.0
       case Cons(x,xs) => x * product(xs)
   }

   def apply [A](as: A*):ListM[A] = {
       if (as.isEmpty) Nil
       else Cons(as.head, apply(as.tail: _*))
   }

}

//for main file
// import mo.datastructs._

// object Main extends App {
//   val l:ListM[Int] = Cons(1,Cons(2,Cons(3,Nil)))
//   println("Hello, World!")
//   println(ListM.sum(l))
// }