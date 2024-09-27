package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.main.OurBlock;
import edu.grinnell.csc207.blocks.Line;

/**
 * Tests of the new block.
 */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+


  @Test
  public void BasicInput() {
    assertEquals("{]A.J.~|hello|~Luis[}", "{]A.J.~|hello|~Luis[}");
  } // placeholder()

    @Test
  public void OurBlockLine() {
    AsciiBlock line = new Line("Hello");

    AsciiBlock oneRun = new OurBlock(line);
    assertEquals(21, oneRun.width(),
        "M: Correct width for OurBlocked hello");
    assertEquals(1, oneRun.height(),
        "M: Correct height for OurBlocked hello");
    assertEquals("{]A.J.~|Hello|~Luis[}\n", TestUtils.toString(oneRun),
        "M: Correct contents for OurBlocked hello");

    AsciiBlock doubleTrouble = new OurBlock(oneRun);
    assertEquals(37, doubleTrouble.width(),
        "M: Correct width for doubly OurBlocked hello");
    assertEquals(1, doubleTrouble.height(),
        "M: Correct height for doubly OurBlocked hello");
    assertEquals("{]A.J.~|{]A.J.~|Hello|~Luis[}|~Luis[}\n", TestUtils.toString(doubleTrouble),
        "M: Correct contents for doubly OurBlocked hello");
  } // testOurBlockLine()

} // class TestNewBlock
