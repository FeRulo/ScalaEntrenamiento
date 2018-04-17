package co.com.scalatraining.scalatest.specs

import org.scalatest.FunSpec

class SetFunSpec extends FunSpec {
  val s = Set.empty
  describe("A Set") {
    describe("when empty") {
      it("should have size 0") {
        assert(s.size == 0)
      }

      it("should produce NoSuchElementException when head is invoked") {
        assertThrows[NoSuchElementException] {
          s.head
        }
      }
    }
  }
}