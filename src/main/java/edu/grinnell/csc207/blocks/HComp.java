package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Luis Lopez
 * @author A.J. Trimble
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *                   The way in which the blocks should be aligned.
   * @param leftBlock
   *                   The block on the left.
   * @param rightBlock
   *                   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock };
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *                        The alignment of the blocks.
   * @param blocksToCompose
   *                        The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
    String currStr = "";

    if (align.equals(VAlignment.TOP)) { // top allign
      for (int x = 0; x < blocks.length; x++) {
        if (i > blocks[x].height() - 1) {
          currStr = currStr + " ".repeat(blocks[x].width());
        } else {
          currStr = currStr + blocks[x].row(i);
        } // else to print row with spacing of total width minus block's width

      } // end of for loop

    } else if (align.equals(VAlignment.CENTER)) {

      for (int x = 0; x < blocks.length; x++) {
        int topSpace = (height() - blocks[x].height()) / 2;
        if (topSpace > i) {
          currStr = currStr + " ".repeat(blocks[x].width());
        } else if (i < blocks[x].height() + topSpace) {
          currStr = currStr + blocks[x].row(i - topSpace);
        } else {
          currStr = currStr + " ".repeat(blocks[x].width());
        } // end of else if
      } // end of forloop

    } else if (align.equals(VAlignment.BOTTOM)) {
      for (int x = 0; x < blocks.length; x++) {
        int topSpace = height() - blocks[x].height();
        if (topSpace > i) {
          currStr = currStr + " ".repeat(blocks[x].width());
        } else {
          currStr = currStr + blocks[x].row(i - topSpace);
        } // checks if row is less than top space, to print appropriate row of block
      } // else to print row with spacing of total width minus block's width
    } else {
      String str = "error";
      return str;
    } // return error if not within alignment
    return currStr;
  } // row (i)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int max = 0;
    for (int i = 0; i < blocks.length; i++) {
      if (max < blocks[i].height()) {
        max = blocks[i].height();
      } // checks if block's height is greater than max, if so set max to it
    } // end of array of blocks to find max height
    return max;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int sum = 0;
    for (int i = 0; i < blocks.length; i++) {
      sum += blocks[i].width();
    } // increaes the width for the entirety of the blocks
    return sum;
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
    return ((other instanceof HComp) && (this.eqv((HComp) other)));
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
  public boolean eqv(HComp other) {
    if (this.blocks.length != other.blocks.length) {
      return false;
    } else if (this.align != other.align) {
      return false;
    } // checks if alignments
    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
        return false;
      } // if blocks not eqv, return false
    } // end of loop if blocks not eqv
    return true;
  } // eqv(AsciiBlock)
} // class HComp
