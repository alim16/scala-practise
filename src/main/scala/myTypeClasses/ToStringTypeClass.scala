package myTypeClasses

//the typeclass
trait ToString[A] {
    def toString(a: A): String
}


//the typeclass instance
object ToStringInstances {
    implicit val pizzaAsString = new ToString[Pizza]{
        def toString(p: Pizza): String = {
            s"""|pizza(${p.crustSize}, ${p.crustType}),
            | toppings = ${p.toppings}""".stripMargin
        }
    }

    implicit val intAsString = new ToString[Int]{
        def toString(I: Int): String = {
            s"""|Integers value is:###${I}""".stripMargin
        }
    }
}


//typeclass API
object ToStringSyntax {
    implicit class ToStringOps[A](value: A){
        def asString(implicit toStringInstance: ToString[A]): String = {
            toStringInstance.toString(value)
        }
    }
}