package co.com.scalatraining.collections

import org.scalatest.FunSuite

class MapSuite extends FunSuite {

  test ("Creacion vacia") {
      val mapa1 = Map()
      val mapa2 = Map.empty
      assert(mapa1.isEmpty)
      assert(mapa2.isEmpty)
      assert(mapa1 == mapa2)
  }

  test("Un Map se debe poder operar en un for-comp"){
    val mapa = Map(1->"uno", 2->"dos")

    val res = for{
      i <- mapa
      if i._1 == 1
    } yield(i)

    println(res)
    assert(res.keys.size === 1)
    assert(res.keys.head === 1)
    assert(res.get(mapa.keys.head).get === "uno")
  }

  test("mapValue en un Map") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    assertResult(Map("1" -> 1, "2" -> 4, "3" -> 9)) {
      map.mapValues(valor => valor * valor)
    }
  }

  test("head en un Map") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    assertResult("1" -> 1) {
      map.head
    }
  }


  test("tail en un Map") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    assertResult(Map("2" -> 2, "3" -> 3)) {
      map.tail
    }
  }

  test("tail en un Map2") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    val v1 = map.head._1
    val v2 = map.head._2
    println(v1 +" " + v2)
    assertResult(Map("2" -> 2, "3" -> 3)) {

      map.tail
    }
  }
  test("tail en un Map3") {
    val map = Map()

    assertThrows[NoSuchElementException] {
      map.head
    }
  }

  test("map en un Map3") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    println("Hola: " + map.map(f => f._1 + f._2))
    assertResult(Map("1" -> 2, "2" -> 3, "3" -> 4)) {
      map.map(f=>(f._1,f._2+1))
    }
  }

  test("split en un Map") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    val (map2, map3) = map.splitAt(2)
    assert(map2 == Map("1" -> 1, "2" -> 2) && map3 == Map("3" -> 3))
  }

  test("crear nuevo Map con un item más") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    assertResult(Map("1" -> 1, "2" -> 2, "3" -> 3, "4" -> 4)) {
      map + ("4" -> 4)
    }
  }
  test("test adicionar2"){
    val map = Map("1"->1,"3"->3)
    assertResult(Map("1"->1,"3"->3, "4"-> 4, "2"->2)){
      map + ("4"-> 4)  +("2"->2)
    }
  }



  test("drop en un Map") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    assertResult(Map("2" -> 2, "3" -> 3)) {
      map.drop(1)
    }
  }

  test("dropRight en un Map") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    assertResult(Map("1" -> 1, "2" -> 2)) {
      map.dropRight(1)
    }
  }


  test("filter en un Map") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3, "4" -> 4)
    assertResult(Map("2" -> 2, "4" -> 4)) {
      map.filter(dato =>
        dato._2 % 2 == 0
      )
    }
  }

  test("filter en un Map2") {
    case class MyCaseClass (nombre:String)
    val map = Map("JP" -> MyCaseClass("JP"), "Nataly" -> MyCaseClass("Nataly"), "Jhonatan" -> MyCaseClass("Jhonatan"))
    assertResult(Map("JP" -> MyCaseClass("JP"), "Jhonatan" -> MyCaseClass("Jhonatan"))) {
      map.filter(t=> t._1.startsWith("J"))
    }
  }


  test("foreach en un Map") {
    val map = Map("1" -> 1, "2" -> 2, "3" -> 3)
    assertResult(6) {
      var sum = 0
      map.foreach((x) =>
        sum += x._2
      )
      sum
    }
  }

}
