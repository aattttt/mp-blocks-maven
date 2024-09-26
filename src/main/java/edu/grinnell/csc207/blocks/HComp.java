package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
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
    this.blocks = new AsciiBlock[] { leftBlock, rightBlock };
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
        if (blocks[x].height() < i) {
          currStr = currStr + " ".repeat(blocks[x].width());
        } else {
          currStr = currStr + blocks[x].row(i);
        } // end ifelse
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
        }
      }
    } else {
      String str = "error";
      return str;
    }
    return currStr;
  }

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
      }
    }
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
    }
    return sum; // STUB
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
    return false; // STUB
  } // eqv(AsciiBlock)
} // class HComp
