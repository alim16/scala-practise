import cats.effect.IO
import parser.{doStuff => doParsingStuff}
import mo.datastructs._
import mo.people.{doStuff => doPeopleStuff}

object program extends App {

  // val iowelcome = IO {println("welcome! enter some text?")}
  // val ioread = IO {scala.io.StdIn.readLine }

  // val program: IO[Unit] = 
  //   for {
  //     _ <- iowelcome
  //     something <- ioread
  //     _ <- IO {println(s"you have entered $something")}
  //     _ <- k //value from parser
  //   } yield ()

  val program: IO[Unit] = 
    for {
      _ <- doParsingStuff.apply()
      _ <- doPeopleStuff.apply()
      _ <- IO{println(s"finished printing ###")}
    } yield ()

  program.unsafeRunSync()

}