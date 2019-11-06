package com.mo.animals
import cats.effect.IO

case class Cat(val colour: String, val food: String) {}

object doStuff {
    def apply():IO[Unit] = {
        val cat = Cat(colour="noColour",food="noFood")
        IO.pure(println(cat.colour))
    }
}