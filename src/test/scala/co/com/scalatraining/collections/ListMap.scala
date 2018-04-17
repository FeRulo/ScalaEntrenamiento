package co.com.scalatraining.collections

import org.scalatest.FunSuite

import scala.collection.immutable.ListMap

class ListMapSuite extends  FunSuite {

  test("test crear "){
    //se crea de forma estandar igual que un Map
    val sortedMap = ListMap("1"->1,"2"->2,"3"->3)
    assertResult(ListMap("1"->1,"2"->2,"3"->3)){
      sortedMap
    }
  }


  test("test crear vacío"){
    //para un ListMap vacío el tipo debe ser Ordenable
    val sortedMap = ListMap.empty[String, Int]
    assertResult(ListMap.empty ){
      sortedMap
    }
  }

  test("test adicionar"){
    //ingresamos valores en desorden pero el ListMap no los ordena tal como un Map
    val sortedMap = ListMap.empty[String, Int]
    assertResult(Map("zbdc"->4, "dwer"->1, "ascv"->3, "ced"->2) .toList){
      (sortedMap + ("zbdc"-> 4) + ("dwer"->1) +("ascv"->3) +("ced"->2)).toList
    }
  }


  test("test eliminar"){
    //se elimina como un Map de forma estandar
    val sortedMap = ListMap("1"->1,"2"->2,"3"->3, "4"-> 4)
    assertResult(Map("2"->2,"3"->3, "4"-> 4)){
      sortedMap.drop(1)
    }
  }

  test("test convertir"){
    //se transforman los elementos como un Map de forma estandar
    val sortedMap = ListMap("1"->1,"2"->2,"3"->3)
    assertResult(Map("1"->2,"2"->3, "3"-> 4)){
      sortedMap.map(t => (t._1, t._2 +1))
    }
  }

}
