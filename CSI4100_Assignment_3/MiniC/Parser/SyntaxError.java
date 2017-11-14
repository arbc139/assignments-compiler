package MiniC.Parser;

class SyntaxError extends Exception {

  SyntaxError()
  {
    super();
  };

  SyntaxError (String s)
  {
    super(s);
  }

  public static final long serialVersionUID = 3L;
  // http://mindprod.com/jgloss/serialization.html#SERIALVERSIONUID
}
