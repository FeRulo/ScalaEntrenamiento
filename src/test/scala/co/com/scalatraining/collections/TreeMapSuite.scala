package co.com.scalatraining.collections

import org.scalatest.FunSuite

import scala.collection.immutable.TreeMap

class TreeMapSuite extends  FunSuite {

  test("test crear "){
    //se crea de forma estandar igual que un Map
    val treeMap = TreeMap("1"->1,"2"->2,"3"->3)
    assertResult(TreeMap("1"->1,"2"->2,"3"->3)){
      treeMap
    }
  }


  test("test crear vacío"){
    //para un TreeMap vacío el tipo debe ser Ordenable
    val treeMap = TreeMap.empty[String, Int]
    assertResult(TreeMap.empty ){
      treeMap
    }
  }

  test("test adicionar"){
    //ingresamos valores en desorden pero el TreeMap los ordena como un SortedMap
    val treeMap = TreeMap.empty[String, Int]
    assertResult(Map("ascv"->3, "ced"->2, "dwer"->1, "zbdc"->4).toList){
      (treeMap + ("zbdc"-> 4) + ("dwer"->1) +("ascv"->3) +("ced"->2)).toList
    }
  }


  test("test eliminar"){
    //se elimina como un Map de forma estandar
    val treeMap = TreeMap("1"->1,"2"->2,"3"->3, "4"-> 4)
    assertResult(Map("2"->2,"3"->3, "4"-> 4)){
      treeMap.drop(1)
    }
  }

  test("test convertir"){
    //se transforman los elementos como un Map de forma estandar
    val treeMap = TreeMap("1"->1,"2"->2,"3"->3)
    assertResult(Map("1"->2,"2"->3, "3"-> 4)){
      treeMap.map(t => (t._1, t._2 +1))
    }
  }

}
