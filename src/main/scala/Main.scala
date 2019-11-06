import cats.effect.IO
import parser.{doStuff => doParsingStuff}
import mo.datastructs._
import mo.people.{doStuff => doPeopleStuff}
import com.mo.animals.{doStuff => doCatStuff}
import myTypeClasses.{doStuff => doTypeClassStuff}
import sequencing.{doStuff => doSequenceStuff}
//import myTypeClasses._

object program extends App {
  // val ioread = IO {scala.io.StdIn.readLine }

  val program: IO[Unit] = 
    for {
     // _ <- doParsingStuff()
     // _ <- doCatStuff()
     //_ <- doSequenceStuff()
     _ <- doTypeClassStuff()
      _ <- IO{println(s"finished printing ###")}
    } yield ()

  program.unsafeRunSync()

}