package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;

/**
 * Create and print our custom block.
 *
 * @author A.J. Trimble
 * @author Luis Lopez
 */
public class OurBlock implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *                 The original block.
   */
  public OurBlock(AsciiBlock original) {
    this.block = original;
  } // VFlip(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *                      if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i > block.height() + 1) {
      throw new Exception("Invalid value of i");
    } else {
      return "{]A.J.~|" + block.row(i) + "|~Luis[}";
    } // if within acceptable i, return string
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return block.width() + "{]A.J.~|".length() + "|~Luis[}".length();
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *              The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *         false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof OurBlock) && (this.eqv((OurBlock) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *              The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *         false otherwise.
   */
  public boolean eqv(OurBlock other) {
    return (this.block == other.block);
  } // eqv(AsciiBlock)
} // class OurBlocks
