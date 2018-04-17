package co.com.scalatraining.collections

import org.scalatest.FunSuite

import scala.collection.immutable.TreeMap

class TreeMapSuite extends  FunSuite {

  test("test crear "){
    //se crea de forma estandar igual que un Map
    val sortedMap = TreeMap("1"->1,"2"->2,"3"->3)
    assertResult(TreeMap("1"->1,"2"->2,"3"->3)){
      sortedMap
    }
  }


  test("test crear vacío"){
    //para un TreeMap vacío el tipo debe ser Ordenable
    val sortedMap = TreeMap.empty[String, Int]
    assertResult(TreeMap.empty ){
      sortedMap
    }
  }

  test("test adicionar"){
    //ingresamos valores en desorden pero el TreeMap los ordena como un SortedMap
    val sortedMap = TreeMap.empty[String, Int]
    assertResult(Map("ascv"->3, "ced"->2, "dwer"->1, "zbdc"->4).toList){
      (sortedMap + ("zbdc"-> 4) + ("dwer"->1) +("ascv"->3) +("ced"->2)).toList
    }
  }


  test("test eliminar"){
    //se elimina como un Map de forma estandar
    val sortedMap = TreeMap("1"->1,"2"->2,"3"->3, "4"-> 4)
    assertResult(Map("2"->2,"3"->3, "4"-> 4)){
      sortedMap.drop(1)
    }
  }

  test("test convertir"){
    //se transforman los elementos como un Map de forma estandar
    val sortedMap = TreeMap("1"->1,"2"->2,"3"->3)
    assertResult(Map("1"->2,"2"->3, "3"-> 4)){
      sortedMap.map(t => (t._1, t._2 +1))
    }
  }

}
