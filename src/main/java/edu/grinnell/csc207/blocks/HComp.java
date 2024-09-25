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
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if (align.equals(VAlignment.TOP)) {
      if (i < blocks[0].height() && i < blocks[1].height() ) {
        return blocks[0].row(i) + blocks[1].row(i);
      } else if (i < blocks[0].height()) {
        return blocks[0].row(i) + " ".repeat(blocks[1].width());
      } else if (i < blocks[1].height()) {
        return " ".repeat(blocks[0].width()) + blocks[1].row(i);
      }
    } else if (align.equals(VAlignment.CENTER)) {
      if (blocks[0].height() == blocks[1].height()) { // both equal
        return blocks[0].row(i) + blocks[1].row(i);

      } else if (blocks[0].height() > blocks[1].height()) { // left taller
        int diff = blocks[0].height() - blocks[1].height();
        int topSpace = diff/2;
        //int bottomSpace = diff/2 + diff % 2;
        if (i < topSpace ) {
          return blocks[0].row(i) + " ".repeat(blocks[1].width());
        } else if (i < blocks[1].height() + topSpace) {
          return blocks[0].row(i) + blocks[1].row(i - topSpace);;
        } else if (i > blocks[1].height() + topSpace) {
          return blocks[0].row(i) + " ".repeat(blocks[1].width());
        }
      } else if (blocks[0].height() < blocks[1].height()) { // right taller
        
      }

    }

    return "";  // STUB
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return 0;   // STUB
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return 0;   // STUB
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
} // class HComp
