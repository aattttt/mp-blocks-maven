package edu.grinnell.csc207.blocks;

/**
 * A vertically flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class VFlip implements AsciiBlock {
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
   *   The original block.
   */
  public VFlip(AsciiBlock original) {
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
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i  > block.height() + 1) {
      throw new Exception("Invalid value of i");  
     } else { 
      return block.row(block.height() - i - 1);
     }
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
    return block.width();
  } // width()

  
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof VFlip) && (this.eqv((VFlip) other)));
  } // eqv(AsciiBlock)

  public boolean eqv(VFlip other) {
    return this.block.eqv(other.block);
  } // eqv(Grid)

} // class VFlip
