import cats.data.StateT._
import cats.data.StateT
import cats.implicits._
import cats.effect.IO

//example from https://blog.leifbattermann.de/2018/02/03/parsers-in-scala-built-upon-existing-abstractions/

package object parser {
    //parser type alias
    type Parser[A] = StateT[Option, String, A]

    //parses any next character
    val item: Parser[Char] = 
        for {
            input <- get[Option, String]
            _ <- if (input.nonEmpty)
                modify[Option, String](_.tail)
            else
                ().raiseError[Parser, Nothing]
        } yield input.head


    //parses character satisfying a predicate
    def sat(p: Char => Boolean): Parser[Char] =
        for {
            c <- item
            _ <- if (p(c)) c.pure[Parser]
            else ().raiseError[Parser, Nothing]
        } yield c

    //more primitive parsers
    val digit: Parser[Char] = sat(_.isDigit)
    val lower: Parser[Char] = sat(_.isLower)
    val upper: Parser[Char] = sat(_.isUpper)
    val letter: Parser[Char] = sat(_.isLetter)
    val alphaNum: Parser[Char] = sat(_.isLetterOrDigit)
    def char(c: Char): Parser[Char] = sat(_ == c)

    //string literal parser
    def string(str: String): Parser[String]  =
        str.map(char).toList.sequence.map(_.mkString)

    //apply second parser if first one fails using combineK <+>
    val p = string("hello") <+> string("something")

    val k:IO[Unit] = p.run("something and this") match {
       case None => IO {println("didn't match anything")}
       case Some(tup) => IO {println(s"'${tup._1}' remaining after matching the value '${tup._2}'")}
    }
//p.run("something")

    object doStuff {
        def apply() =  IO[Unit]{
            val k:Unit = p.run("something and this") match {
                case None => println("####: parser didn't match anything")
                case Some(tup) => println(s"####: parser has '${tup._1}' remaining after matching the value '${tup._2}'")
            }
            IO.pure(k)
        }
    }
}