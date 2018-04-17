package co.com.scalatraining.collections

import org.scalatest.FunSuite

import scala.collection.immutable.ListMap

class ListMapSuite extends  FunSuite {

  test("test crear "){
    //se crea de forma estandar igual que un Map
    val listMap = ListMap("1"->1,"2"->2,"3"->3)
    assertResult(ListMap("1"->1,"2"->2,"3"->3)){
      listMap
    }
  }


  test("test crear vacío"){
    //para un ListMap vacío el tipo debe ser Ordenable
    val listMap = ListMap.empty[String, Int]
    assertResult(ListMap.empty ){
      listMap
    }
  }

  test("test leer"){
    //con el listMap se puede acceder a un dato concreto desde su llave
    val listMap = ListMap("1"->1,"2"->2,"3"->3)
    assertResult(2){
      listMap("2")
    }
  }

  test("test adicionar"){
    //ingresamos valores en desorden pero el ListMap no los ordena dada el Ordenable
    val listMap = ListMap.empty[String, Int]
    assertResult(Map("zbdc"->4, "dwer"->1, "ascv"->3, "ced"->2) .toList){
      (listMap + ("zbdc"-> 4) + ("dwer"->1) +("ascv"->3) +("ced"->2)).toList
    }
  }
  test("test adicionar repetido"){
    //si ingresamos un nodo lo ingresa al final y elimina el existente
    val listMap = ListMap.empty[String, Int]
    val listMap2 = listMap + ("zbdc"-> 4) + ("dwer"->1) +("ascv"->3) +("ced"->2)
    assertResult(Map("dwer"->1, "ascv"->3, "ced"->2, "zbdc"->4).toList){
      (listMap2 + ("zbdc"-> 4)).toList
    }
  }

  test("test eliminar"){
    //se elimina como un Map de forma estandar
    val listMap = ListMap("1"->1,"2"->2,"3"->3, "4"-> 4)
    assertResult(Map("2"->2,"3"->3, "4"-> 4)){
      listMap.drop(1)
    }
  }

  test("test convertir"){
    //se transforman los elementos como un Map de forma estandar
    val listMap = ListMap("1"->1,"2"->2,"3"->3)
    assertResult(Map("1"->2,"2"->3, "3"-> 4)){
      listMap.map(t => (t._1, t._2 +1))
    }
  }

}
