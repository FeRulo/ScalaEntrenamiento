package co.com.scalatraining.collections

import org.scalatest.FunSuite

import scala.collection.immutable.SortedMap

class SortedMapSuite extends  FunSuite {

  test("test crear "){
    //se crea de forma estandar igual que un Map
    val sortedMap = SortedMap("1"->1,"2"->2,"3"->3)
    assertResult(SortedMap("1"->1,"2"->2,"3"->3)){
      sortedMap
    }
  }


  test("test crear vacío"){
    //para un SortedMap vacío el tipo debe ser Ordenable
    val sortedMap = SortedMap.empty[String, Int]
    assertResult(SortedMap.empty[String, Int] ){
      sortedMap
    }
  }

  test("test adicionar"){
    //ingresamos valores en desorden pero el SortedMap los ordena automáticamente
    val sortedMap = SortedMap.empty[String, Int]
    assertResult(Map("ascv"->3, "ced"->2, "dwer"->1, "zbdc"->4).toList){
        (sortedMap + ("zbdc"-> 4) + ("dwer"->1) +("ascv"->3) +("ced"->2)).toList
    }
  }


  test("test eliminar"){
    //se elimina como un Map de forma estandar
    val sortedMap = SortedMap("1"->1,"2"->2,"3"->3, "4"-> 4)
    assertResult(Map("2"->2,"3"->3, "4"-> 4)){
      sortedMap.drop(1)
    }
  }

  test("test convertir"){
    //se transforman los elementos como un Map de forma estandar
    val sortedMap = SortedMap("1"->1,"2"->2,"3"->3)
    assertResult(Map("1"->2,"2"->3, "3"-> 4)){
      sortedMap.map(t => (t._1, t._2 +1))
    }
  }

}
