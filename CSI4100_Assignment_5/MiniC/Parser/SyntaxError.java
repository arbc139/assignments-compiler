package MiniC.Parser;

class SyntaxError extends Exception {

  public static final long serialVersionUID = 1L;

  SyntaxError()
  {
    super();
  };

  SyntaxError (String s)
  {
    super(s);
  }

}
