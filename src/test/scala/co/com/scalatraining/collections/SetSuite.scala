package co.com.scalatraining.collections

import org.scalatest.FunSuite

import scala.collection.immutable.BitSet
import scala.collection.immutable.{HashSet, ListSet, TreeSet}

class SetSuite extends FunSuite {

  test("map en un set") {
    val set = Set("1", "2", "3")
    val set2 = set.map(dato => dato + "prueba")
    assert(set != set2)
    assertResult(Set("1prueba","2prueba","3prueba")){
      set2
    }
  }

  test("set en clase") {
    val set = Set(1, 1, 1)
    assertResult(1) {
      set.size
    }
  }

  test("set en clase 2") {
    val set = Set.empty[Int]
    val set2 = set + 1
    assert(Set(1)==set2)
    val set3 = set2 + 1
    assert(Set(1)==set2)
    val set4= set3 + 1
    assert(Set(1)==set2)
  }

  test("set en clase 3") {
    val set = Set.empty[Int]
    val set2 = set + 1
    val set3 = set2 + 2
    val set4= set3 + 3
    val set5= set4 + 4
    val set6= set5 + 5
    val set7= set6 + 6

    println(set7)
  }

  test("HashSet test") {
    val set = HashSet.empty[Int]
    val set2 = set + 1
    val set3 = set2 + 2
    val set4= set3 + 3
    val set5= set4 + 4
    val set6= set5 + 5
    val set7= set6 + 6

    assert(Set(5, 1, 6, 2, 3, 4)==set7)
  }
  test("ListSet test") {
    //es un ṕrepend
    val set = ListSet.empty[Int]
    val set2 = set + 1
    val set3 = set2 + 2
    val set4= set3 + 3
    val set5= set4 + 4
    val set6= set5 + 5
    val set7= set6 + 6

    assert(ListSet(6, 5, 4, 3, 2, 1)==set7)
  }

  test("ListSet test 2") {
    //es un ṕrepend
    val s = ListSet.empty[Int]
    val r1 = s + 1
    val r2 = r1 + 2
    val r3= r2 + 3
    println(r3)
    println(r3.head)
  }

  test("ListSet test 3") {
    //es un ṕrepend
    val s = ListSet.empty[Int]
    val r1 = s + 1 + 2 + 3
    println(r1)
    println(r1.head)
  }

  test("TreeSet test") {
    //ordered Set
    val set = TreeSet.empty[Int]
    val set2 = set + 1
    val set3 = set2 + 3
    val set4= set3 + 2
    val set5= set4 + 6
    val set6= set5 + 5
    val set7= set6 + 4

    println(set7)
  }
  test("BitSet test") {
    val set = BitSet.empty
    val set2 = set + 1

    println(set2)
  }

  test("head en un set") {
    val set = Set(1, 2, 3, 4)
    assertResult(1) {
      set.head
    }
  }


  test("tail en un set") {
    val set = Set(1, 2, 3, 4)
    assertResult(Set(2, 3, 4)) {
      set.tail
    }
  }

  test("split en un set") {
    val set = Set(1, 2, 3, 4)
    val (set2, set3) = set.splitAt(2)
    assert(set2 == Set(1, 2) && set3 == Set(3, 4))
  }

  test("crear nuevo set con un item mas") {
    val set = Set(1, 2, 3, 4)
    assertResult(Set(1, 2, 3, 4, 5)) {
      set + 5
    }
  }

  test("apply en set") {
    val set = Set(1, 2, 3, 4)
    assertResult(true) {
      set.apply(4)
    }
  }

  test("drop en set") {
    val set = Set(1, 2, 3, 4)
    assertResult(Set(3, 4)) {
      set.drop(2)
    }
  }

  test("dropRight set") {
    val set = Set(1, 2, 3, 4)
    assertResult(Set(1, 2)) {
      set.dropRight(2)
    }
  }


  test("filter en set") {
    val set = Set(1, 2, 3, 4)
    assertResult(Set(2, 4)) {
      set.filter(x =>
        x % 2 == 0
      )
    }
  }

  test("foreach en set") {
    val set = Set(1, 2, 3, 4)
    assertResult(10) {
      var sum = 0
      set.foreach((x) =>
        sum += x
      )
      sum
    }
  }

  test("mkString en set") {
    val set = Set(1, 2, 3, 4)
    assertResult("1&2&3&4") {
      set.mkString("&")
    }
  }

  test("sum en set") {
    val set = Set(1, 2, 3, 4)
    assertResult(10) {
      set.sum
    }
  }

}