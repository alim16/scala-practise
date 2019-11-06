package myTypeClasses

import cats.effect.IO
import ToStringInstances.pizzaAsString
import ToStringInstances.intAsString
import ToStringSyntax._

object doStuff {
    def apply() = IO[Unit]{
        // val p = Pizza(
        //     LargeCrustSize,
        //     ThinCrustType,
        //     Seq(Pepperoni,Cheese)
        // )
        // val doStuff = IO.pure(println(p.asString))

        val i = IO.pure(println(3.asString))
    }
}