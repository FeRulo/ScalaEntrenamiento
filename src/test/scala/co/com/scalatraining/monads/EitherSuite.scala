package co.com.scalatraining.monads

import org.scalatest.FunSuite

class EitherSuite extends FunSuite{

  test("Either left"){
    val e = Left(1)
    assert(e.isLeft)
  }

  test("Either right"){
    val e = Right(1)
    assert(e.isRight)
  }

  def foo(i:Int): Either[String, Int] = {
    if(i%2==0) Right(i)
    else Left(s"El número $i es impar")
  }

  test("Either left or right"){

    assert(foo(2).isRight)
  }

  test("Either test"){
    val r: Int  = foo(2).fold[Int]( s => {
      assert(false)
      1
    }
      , i => {
        assert(true)
        6
      }
    )

    assert(r == 6)

  }

  test("Swap un Either"){
    val res: Either[Int, String] = foo(1).swap

    assert(res.isRight)

  }

  test("Swap un Either2"){
    val res: Either[String, Int] = foo(2)
    val res2: Either[Int, String] = res.swap
    assert(res2.isLeft)
  }

  test("Flatmap un Either"){
    val sum = for{
      a<- foo(2)
      b<- foo(4)
      c<- foo(6)
      d<- foo(8)
    }yield{
      a + b +c +d
    }
    assert(sum == Right(20))
  }

  test("Flatmap un Either 2"){
    val sum = for{
      a<- foo(2)
      b<- foo(1)
      c<- foo(6)
      d<- foo(8)
    }yield{
      a + b + c +d
    }
    assert(sum == Left("El número 1 es impar"))
  }

  test("map un Either 1"){
    val e1 = foo(2)
    val e2 = e1.map(i => i-1)
    assert(e2 == Right(1))
  }

  test("map un Either 2"){
    val e1 = foo(3)
    val e2 = e1.map(i => i-1)
    assert(e2 == Left("El número 3 es impar"))
  }

}