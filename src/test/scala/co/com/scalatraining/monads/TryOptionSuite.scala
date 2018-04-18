package co.com.scalatraining.monads

import org.scalatest.FunSuite

import scala.util.{Failure, Success, Try}

class TryOptionSuite extends FunSuite{

  def foo(i: Int): Try[Option[Int]] = {
    if(i%2 !=0) Failure(new Error("El número es impar"))
    else {
      if (i <= 10) Success(Some(i))
      else Success(None)
    }
  }

  test("sumando pares"){
    val res = for{
      a<-foo(2)
      b<-foo(4)
      c<-foo(6)
    }yield for{
      a1<-a
      b1<-b
      c1<-c
    }yield(a1+b1+c1)

    assert(res == Success(Some(12)))
  }

}
