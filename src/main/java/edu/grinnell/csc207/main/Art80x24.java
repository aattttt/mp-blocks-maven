package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;

import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author A.J. Trimble
 * @author Luis Lopez
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    AsciiBlock carrot = new Rect('^', 80, 12);
    AsciiBlock ast = new Rect('*', 80, 12);
    AsciiBlock both = new VComp(HAlignment.RIGHT, new AsciiBlock[] {ast, carrot});

    AsciiBlock.print(pen, both);
    pen.close();
  } // main(String[])
} // class Art80x24
