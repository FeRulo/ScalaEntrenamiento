package co.com.scalatraining.monads

import java.util.Random
import java.util.concurrent.Executors

import org.scalatest.FunSuite
import scala.language.postfixOps
import scala.util.{Failure, Success}
import scala.concurrent.{ExecutionContext, Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class FutureSuite extends FunSuite {

  test("Un futuro se puede crear") {

    val hiloPpal = Thread.currentThread().getName

    var hiloFuture = ""
    println(s"El hilo ppal es ${hiloPpal}")

    val saludo = Future {
      hiloFuture = Thread.currentThread().getName
      println(s"El hilo del future es ${hiloFuture}")
      Thread.sleep(500)
      "Hola"
    }
    println(saludo)
    val resultado = Await.result(saludo, 10 seconds)
    println(saludo)
    assert(resultado == "Hola")
    assert(hiloPpal != hiloFuture)
  }

  test("map en Future") {


    val t1 = Thread.currentThread().getName
    println(s"El hilo del ppal es ${t1}")


    val saludo = Future {
      val t2 = Thread.currentThread().getName
      println(s"El hilo del future es ${t2}")

      Thread.sleep(500)
      "Hola"
    }
    val saludoCompleto = saludo.map(mensaje => {
      val t3 = Thread.currentThread().getName
      println(s"El hilo del map es ${t3}")
      mensaje + " muchachos"
    })
    val resultado = Await.result(saludoCompleto, 10 seconds)
    assert(resultado == "Hola muchachos")
  }

  test("Se debe poder encadenar Future con for-comp") {
    val f1 = Future {
      Thread.sleep(1000)
      1
    }

    val f2 = Future {
      Thread.sleep(2000)
      2
    }

    val f3 = for {
      res1 <- f1
      res2 <- f2
    } yield res1 + res2

    val res = Await.result(f3, 10 seconds)

    assert(res == 3)



  }

  test("Se debe poder manejar el error de un Future de forma imperativa") {
    val divisionCero = Future {
      Thread.sleep(100)
      10 / 0
    }
    var error = false

    val r: Unit = divisionCero.onFailure {
      case e: Exception => error = true
    }

    Thread.sleep(1000)

    assert(error == true)
  }

  test("Se debe poder manejar el exito de un Future de forma imperativa") {
    val division = Future {
      5
    }
    var r = 0

    val f = division.onComplete {
      case Success(res) => r = res
      case Failure(e) => r = 1
    }

    Thread.sleep(150)

    val res = Await.result(division, 10 seconds)

    assert(r == 5)
  }

  test("Se debe poder manejar el error de un Future de forma funcional sincronicamente") {

    var threadName1 = ""
    var threadName2 = ""

    val divisionPorCero = Future {
      threadName1 = Thread.currentThread().getName
      Thread.sleep(100)
      10 / 0
    }.recover {
      case e: ArithmeticException => {
        threadName2 = Thread.currentThread().getName
        "No es posible dividir por cero"
      }
    }

    val res = Await.result(divisionPorCero, 10 seconds)

    assert(threadName1 == threadName2)
    assert(res == "No es posible dividir por cero")

  }

  test("Se debe poder manejar el error de un Future de forma funcional asincronamente") {

    var threadName1 = ""
    var threadName2 = ""

    implicit val ecParaPrimerHilo = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(1))

    val f1 = Future {
      threadName1 = Thread.currentThread().getName
      2/0
    }(ecParaPrimerHilo)
    .recoverWith {
      case e: ArithmeticException => {

        implicit val ecParaRecuperacion = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(1))

        Future{
          threadName2 = Thread.currentThread().getName
          1
        }(ecParaRecuperacion)
      }
    }

    val res = Await.result(f1, 10 seconds)

    println(threadName1)
    println(threadName2)

    assert(threadName1 != threadName2)
  }

  test("Los future **iniciados** fuera de un for-comp deben iniciar al mismo tiempo") {

    val timeForf1 = 100
    val timeForf2 = 400
    val timeForf3 = 100

    val additionalTime = 50D

    val estimatedElapsed = (Math.max(Math.max(timeForf1, timeForf2), timeForf3) + additionalTime)/1000

    val f1 = Future {
      Thread.sleep(timeForf1)
      1
    }
    val f2 = Future {
      Thread.sleep(timeForf2)
      2
    }
    val f3 = Future {
      Thread.sleep(timeForf3)
      3
    }

    val t1 = System.nanoTime()

    val resultado = for {
      a <- f1
      b <- f2
      c <- f3
    } yield (a+b+c)

    val res = Await.result(resultado, 10 seconds)
    val elapsed = (System.nanoTime() - t1) / 1.0E09

    println(s"futuros iniciador fuera del for-comp: estimado: $estimatedElapsed ,real: $elapsed")

    assert(elapsed <= estimatedElapsed)
    assert(res == 6)

  }

  test("Los future **definidos** fuera de un for-comp deben iniciar secuencialmente") {

    val timeForf1 = 100
    val timeForf2 = 500
    val timeForf3 = 400

    val estimatedElapsed:Double = (timeForf1 + timeForf2 + timeForf3)/1000

    def f1 = Future {
      Thread.sleep(timeForf1)
      1
    }
    def f2 = Future {
      Thread.sleep(timeForf2)
      2
    }
    def f3 = Future {
      Thread.sleep(timeForf3)
      3
    }

    val t1 = System.nanoTime()

    val resultado = for {
      a <- f1
      b <- f2
      c <- f3
    } yield (a+b+c)

    val res = Await.result(resultado, 10 seconds)
    val elapsed = (System.nanoTime() - t1) / 1.0E09

    println(s"futuros iniciador fuera del for-comp: estimado: $estimatedElapsed ,real: $elapsed")

    assert(elapsed >= estimatedElapsed)
    assert(res == 6)

  }

  test("Los future **lazy val** fuera de un for-comp deben iniciar secuencialmente") {

    val timeForf1 = 100
    val timeForf2 = 500
    val timeForf3 = 400

    val estimatedElapsed:Double = (timeForf1 + timeForf2 + timeForf3)/1000

    lazy val lv1 = Future {
      Thread.sleep(timeForf1)
      1
    }
    lazy val lv2 = Future {
      Thread.sleep(timeForf2)
      2
    }
    lazy val lv3 = Future {
      Thread.sleep(timeForf3)
      3
    }

    val t1 = System.nanoTime()

    val resultado = for {
      a <- lv1
      b <- lv2
      c <- lv3
    } yield (a+b+c)

    val res = Await.result(resultado, 10 seconds)
    val elapsed = (System.nanoTime() - t1) / 1.0E09

    println(s"futuros iniciador fuera del for-comp: estimado: $estimatedElapsed ,real: $elapsed")

    assert(elapsed >= estimatedElapsed)
    assert(res == 6)

  }

  test("Los future declarados dentro de un for-comp deben iniciar secuencialmente") {

    val t1 = System.nanoTime()

    val timeForf1 = 100
    val timeForf2 = 100
    val timeForf3 = 100

    val estimatedElapsed = (timeForf1 + timeForf2 + timeForf3)/1000

    val resultado = for {
      a <- Future {
        Thread.sleep(timeForf1)
        1
      }
      b <- Future {
        Thread.sleep(timeForf2)
        2
      }
      c <- Future {
        Thread.sleep(timeForf3)
        3
      }
    } yield (a+b+c)

    val res = Await.result(resultado, 10 seconds)
    val elapsed = (System.nanoTime() - t1) / 1.0E09

    assert(elapsed >= estimatedElapsed)
    assert(res == 6)
  }

  test("taller"){
    val l = List(Future{1},Future{2},Future{3},Future{4},Future{5},Future{6},Future{7},Future{8},Future{9},Future{10})
    val r:Future[Int] = l.fold(Future{0}){(fr, fi)=> for{
        i1<-fr
        i2<-fi
      }yield(i1+i2)}
    val res = Await.result(r, 10 seconds)
    assert(res == 55)
  }
  test("taller con Sequence"){
    val l = Range(1,11).map(i=> Future(i))
    val res = Future.sequence(l)//Future of List
          .map(li=>li.sum)
    val prom = Await.result(res, 10 seconds)
    assert(prom == 55)
  }

  test("taller con Traverse"){
    val l = (1 to 10).map(i=> Future(i))
    val res = Future.traverse(l)(f=>f.map(i=> i*2)).map(li=>li.sum)
    val prom = Await.result(res, 10 seconds)
    assert(prom == 110)
  }
}