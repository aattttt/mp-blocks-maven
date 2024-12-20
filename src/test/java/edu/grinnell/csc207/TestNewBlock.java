package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.main.OurBlock;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.VFlip;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HFlip;

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
  public void testOurBlockEmpty() {
    AsciiBlock empty = new Empty();
    AsciiBlock emptyOurBlock = new OurBlock(empty);
    assertEquals(0, empty.width(),
        "R: Empty block has no width");
    assertEquals(0, empty.height(),
        "R: Empty block has no height");
    assertEquals("", TestUtils.toString(empty),
        "R: Empty block has no contents");
  } // testEmpty()

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

  @Test
  public void testDoubleTrouble() {
    AsciiBlock line = new Line("Hello");
    AsciiBlock oneRun = new OurBlock(line);
    AsciiBlock doubleTrouble = new OurBlock(oneRun);
    assertEquals(37, doubleTrouble.width(),
        "M: Correct width for doubly OurBlocked hello");
    assertEquals(1, doubleTrouble.height(),
        "M: Correct height for doubly OurBlocked hello");
    assertEquals("\n", TestUtils.toString(doubleTrouble),
        "M: Correct contents for doubly OurBlocked hello");
  } // testOurBlockLine()

  @Test
  public void testHFliped() {
    AsciiBlock line = new Line("Hello");
    AsciiBlock oneRun = new OurBlock(line);
    AsciiBlock flippedRun = new HFlip(oneRun);
    assertEquals(21, flippedRun.width(),
        "M: Correct width for hflipped OurBlock");
    assertEquals(1, flippedRun.height(),
        "M: Correct height for hflipped OurBlock");
    assertEquals("}[siuL~|olleH|~.J.A]{\n", TestUtils.toString(flippedRun),
        "M: Correct contents for hflipped OurBlock");
  } // testHFliped()

  @Test
  public void testFullBlock() {
    Line a = new Line("alfa");
    Line b = new Line("bravo");
    Line c = new Line("charlie");
    Line d = new Line("delta");
    AsciiBlock abcd = new VComp(HAlignment.LEFT, new AsciiBlock[] { a, b, c, d });
    AsciiBlock OurBlock = new OurBlock(abcd);
    assertEquals("{]A.J.~|alfa   |~Luis[}\n{]A.J.~|bravo  |~Luis[}\n{]A.J.~|charlie|~Luis[}\n{]A.J.~|delta  |~Luis[}\n", TestUtils.toString(OurBlock),
        "M: Correct contents for a OurBlocked full block");
  } // testVFlipChange()

} // class TestNewBlock
