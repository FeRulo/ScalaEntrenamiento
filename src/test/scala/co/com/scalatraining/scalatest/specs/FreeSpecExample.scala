package co.com.scalatraining.scalatest.specs

import org.scalatest.FreeSpec

class SetFreeSpec extends FreeSpec {
  val s =Set.empty
  "A Set" - {
    "when empty" - {
      "should have size 0" in {
        assert(s.size == 0)
      }

      "should produce NoSuchElementException when head is invoked" in {
        assertThrows[NoSuchElementException] {
         s.head
        }
      }
    }
  }
}
