package MiniC.CodeGen;

public class Frame {

    private int LabelNr;
    private int LocalVarNr;
    private boolean isMain;

    /*
     * local variables in main (static methods):
     * 0: argv
     * 1: mc$
     *
     * local variables for all other MiniC functions (instance methods)
     * 0: "this" ptr
     *
     */

    public Frame (boolean isMain) {
       this.isMain = isMain;
       LabelNr = -1;
       if(this.isMain)
         LocalVarNr = 1;
       else
         LocalVarNr = 0;
    }

    public int getNewLabel() {
      LabelNr++;
      return LabelNr;
    } 

    public int getNewLocalVarIndex() {
      LocalVarNr++;
      return LocalVarNr;
    } 

}
