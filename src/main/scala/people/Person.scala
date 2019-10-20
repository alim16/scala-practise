package mo.people
import cats.effect.IO

class Person (val firstName:String, val lastName:String){
    def name = s"###persons name is: $firstName $lastName"
}

object Person {
    def apply(name: String): Person= {
        val parts: Array[String]= name.split(' ')
        new Person(parts(0), parts(1))
    }
}

object doStuff {
    def apply() = IO[Unit]{
        val p = Person.apply("someFirstName someLastName")
        val doStuff = IO.pure(println(p.name))
    }
}