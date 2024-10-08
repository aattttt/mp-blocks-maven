package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Luis Lopez
 * @author A.J. Trimble
 */
public class VComp implements AsciiBlock {
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
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *                    The way in which the blocks should be aligned.
   * @param topBlock
   *                    The block on the top.
   * @param bottomBlock
   *                    The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock };
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *                        The alignment of the blocks.
   * @param blocksToCompose
   *                        The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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
    if (align.equals(HAlignment.LEFT)) {
      int blockIndex = 0;
      int sumHeight = 0;
      while (i > sumHeight) {
        sumHeight += blocks[blockIndex].height();
        blockIndex++;
      } // end while
      if (i - sumHeight == 0) {
        return blocks[blockIndex].row(i - (sumHeight))
            + " ".repeat(width() - blocks[blockIndex].width());
      } else {
        return blocks[blockIndex - 1].row((sumHeight) - i)
            + " ".repeat(width() - blocks[blockIndex - 1].width());
      } // end if else

    } else if (align.equals(HAlignment.RIGHT)) {
      int blockIndex = 0;
      int sumHeight = 0;
      while (i > sumHeight) {
        if (blocks[blockIndex].width() == 0) {
          blockIndex++;
        } else {
          sumHeight += blocks[blockIndex].height();
          blockIndex++;
        } // updates index to proper block in array
      } // end while
      if (i - sumHeight == 0) {
        if (blocks[blockIndex].width() == 0) {
          blockIndex++;
        } // check if block width is 0, if so presume empty and increment
        return " ".repeat(width() - blocks[blockIndex].width())
            + blocks[blockIndex].row(i - (sumHeight));
      } else {
        if (blocks[blockIndex - 1].width() == 0) {
          blockIndex++;
        } // returns string with spaces to fill out entire block width
        return " ".repeat(width() - blocks[blockIndex - 1].width())
            + blocks[blockIndex - 1].row((sumHeight) - i);
      } // end if else

    } else if (align.equals(HAlignment.CENTER)) {

      int blockIndex = 0;
      int sumHeight = 0;
      while (i > sumHeight) {
        if (blocks[blockIndex].width() == 0) {
          blockIndex++;
        } else {
          sumHeight += blocks[blockIndex].height();
          blockIndex++;
        } // updates index to proper block in array
      } // end while
      if (i - sumHeight == 0) {
        if (blocks[blockIndex].width() == 0) {
          blockIndex++;
        } // check if block width is 0, if so presume empty and increment
        int diff = (width() - blocks[blockIndex].width()) / 2;
        return " ".repeat(diff) + blocks[blockIndex].row(i - (sumHeight))
          + " ".repeat(width() - diff - blocks[blockIndex].width());
      } else {
        if (blocks[blockIndex - 1].width() == 0) {
          blockIndex++;
        } // returns string with spaces to fill out entire block width
        int diff = (width() - blocks[blockIndex - 1].width()) / 2;
        return " ".repeat(diff) + blocks[blockIndex - 1].row((sumHeight) - i)
          + " ".repeat(width() - diff - blocks[blockIndex - 1].width());
      } // end if else

      // int blockIndex = 0;
      // int sumHeight = 0;
      // while (i > sumHeight) {
      //   sumHeight += blocks[blockIndex].height();
      //   blockIndex++;
      // } // end while
      // if (i - sumHeight == 0) {
      //   int diff = (width() - blocks[blockIndex].width()) / 2;
      //   return " ".repeat(diff) + blocks[blockIndex].row(i - (sumHeight))
      //       + " ".repeat(width() - diff - blocks[blockIndex].width());
      // } else {
      //   int diff = (width() - blocks[blockIndex - 1].width()) / 2;
      //   return " ".repeat(diff) + blocks[blockIndex - 1].row((sumHeight) - i)
      //       + " ".repeat(width() - diff - blocks[blockIndex - 1].width());
      // } // checks if within block heigh to return string with spaces on right side
    } // end VA checks

    return "";
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int sum = 0;
    for (int i = 0; i < blocks.length; i++) {
      sum += blocks[i].height();
    } // loop through blocks array
    return sum;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int max = 0;
    for (int i = 0; i < blocks.length; i++) {
      if (max < blocks[i].width()) {
        max = blocks[i].width();
      } // if width is bigger, update max to current width
    } // for to iterate thorugh blocks
    return max;
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
    return ((other instanceof VComp) && (this.eqv((VComp) other)));
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
  public boolean eqv(VComp other) {
    if (this.blocks.length != other.blocks.length) {
      return false;
    } else if (this.align != other.align) {
      return false;
    } // end of eqv check for alignment
    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
        return false;
      } // end of loop for eqv check each block
    } // end of else to check eqv of contents and fields
    return true;
  } // eqv(AsciiBlock)
} // class VComp
