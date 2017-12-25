package MiniC.TreeDrawer;

import java.awt.*;

import MiniC.AstGen.Program;

public class Drawer {

    private DrawerFrame frame;
    private DrawerPanel panel;

    private Program AST;
    private DrawingTree Drawing;

    // Draw the AST representing a complete program.

    public void draw(Program ast) {
	AST = ast;
	panel = new DrawerPanel(this);
	frame = new DrawerFrame(panel);

	Font font = new Font("SansSerif", Font.PLAIN, 12);
	frame.setFont(font);

	FontMetrics fontMetrics = frame.getFontMetrics(font);

	LayoutVisitor lv = new LayoutVisitor(fontMetrics);
	AST.accept(lv);
	Drawing = lv.Tree;
	Drawing.position(new Point(10, 10));
	int minx = Drawing.minx();
	if (minx < 0) {
	    Drawing.fix_x(-1*minx);
	}
	frame.setVisible(true);
    }

    public void paintAST (Graphics g) {
	g.setColor(panel.getBackground());
	Dimension d = panel.getSize();
	g.fillRect(0, 0, d.width, d.height);

	if (Drawing != null) {
	    Drawing.paint(g);
	}
    }
}
