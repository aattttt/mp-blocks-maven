package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
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
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if (align.equals(HAlignment.LEFT)) {
      int blockIndex = 0;
      int sumHeight = 0;
      while (i > sumHeight){
        sumHeight += blocks[blockIndex].height();
        blockIndex ++;
      } // end while
      if (i - sumHeight == 0){
        return blocks[blockIndex].row(i - (sumHeight)) + " ".repeat(width() - blocks[blockIndex].width());
      } else {
        return blocks[blockIndex -1].row((sumHeight) - i) + " ".repeat(width() - blocks[blockIndex -1].width());
      } // end if else

    
    } else if (align.equals(HAlignment.RIGHT)) {
      int blockIndex = 0;
      int sumHeight = 0;
      while (i > sumHeight){
        sumHeight += blocks[blockIndex].height();
        blockIndex ++;
      } // end while
      if (i - sumHeight == 0){
        return " ".repeat(width() - blocks[blockIndex].width()) + blocks[blockIndex].row(i - (sumHeight));
      } else {
        return " ".repeat(width() - blocks[blockIndex -1].width()) + blocks[blockIndex -1].row((sumHeight) - i);
      } // end if else

    } else if (align.equals(HAlignment.CENTER)) {
      int blockIndex = 0;
      int sumHeight = 0;
      while (i > sumHeight){
        sumHeight += blocks[blockIndex].height();
        blockIndex ++;
      } // end while
      if (i - sumHeight == 0){
        int diff = (width() - blocks[blockIndex].width()) / 2;
        return " ".repeat(diff) + blocks[blockIndex].row(i - (sumHeight)) + " ".repeat(width() - diff - blocks[blockIndex].width());
      } else {
        int diff = (width() - blocks[blockIndex - 1].width()) / 2;
        return " ".repeat(diff) + blocks[blockIndex -1].row((sumHeight) - i) + " ".repeat(width() - diff - blocks[blockIndex - 1].width());
      }

    } // end VA checks

    return "";  // STUB
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
    }
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
      }
    }
    return max;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return false;       // STUB
  } // eqv(AsciiBlock)
} // class VComp
