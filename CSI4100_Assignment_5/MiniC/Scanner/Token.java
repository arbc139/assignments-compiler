package MiniC.Scanner;

import MiniC.Scanner.SourcePos;

final public class Token extends Object {

  public int kind;
  protected SourcePos src_pos;
  private String lexeme;
  private static int overall_nrtokens = 0;
  private int my_tokennr;

  public Token (int kind, String lexeme, SourcePos src_pos) {
    if (kind == Token.ID) {
       int index = firstKeyword;
       boolean searching = true; 
       while (searching) {
          int compare = tokenTable[index].compareTo(lexeme);
          if (compare == 0) {
             this.kind = index;
             searching = false;
          } else if (compare > 0 || index == lastKeyword) {
             this.kind = Token.ID;
             searching = false;
          } else {
             index++;
          }
       }
    } else {
       this.kind = kind;
    }
    this.src_pos = src_pos;
    this.lexeme = lexeme;
    overall_nrtokens++;
    my_tokennr = overall_nrtokens;
  }

  public void print() {
      System.out.println("token" + my_tokennr + ".kind = Token."
                         + tokenTable[kind].toUpperCase());
      System.out.println("token" + my_tokennr + ".lexeme = \""
                         + lexeme + "\"");
      System.out.println("token" + my_tokennr + ".src_pos.StartLine = "
                         + src_pos.StartLine);
      System.out.println("token" + my_tokennr + ".src_pos.EndLine = "
                         + src_pos.EndLine);
      System.out.println("token" + my_tokennr + ".src_pos.StartCol = "
                         + src_pos.StartCol);
      System.out.println("token" + my_tokennr + ".src_pos.EndCol = "
                         + src_pos.EndCol + "\n");
  }

  public final static int

    // identifiers, operators, literals:
    ID			= 0,    // identifier
    ASSIGN		= 1,	// a = ...
    OR			= 2,    // ||
    AND			= 3,    // &&
    NOT			= 4,    // !
    EQ			= 5,    // ==
    NOTEQ		= 6,    // !=
    LESSEQ		= 7,    // <=
    LESS		= 8,    // <
    GREATER		= 9,    // >
    GREATEREQ		= 10,   // >=
    PLUS		= 11,   // +
    MINUS		= 12,   // -
    TIMES		= 13,   // *
    DIV			= 14,   // /
    INTLITERAL		= 15,
    FLOATLITERAL	= 16,
    BOOLLITERAL		= 17,
    STRINGLITERAL	= 18,

    // keywords:
    BOOL		= 19,   // bool
    ELSE		= 20,   // else
    FLOAT		= 21,   // float
    FOR			= 22,   // for
    IF			= 23,   // if
    INT			= 24,   // int
    RETURN		= 25,   // return
    VOID		= 26,   // void
    WHILE		= 27,   // while

    // punctuation:
    LEFTBRACE		= 28,	// {
    RIGHTBRACE		= 29,	// }
    LEFTBRACKET		= 30,	// [
    RIGHTBRACKET	= 31,	// ]
    LEFTPAREN		= 32,	// (
    RIGHTPAREN		= 33,	// )
    COMMA		= 34,	// ,
    SEMICOLON		= 35,	// ;

    // special tokens:
    ERROR		= 36,
    EOF			= 37;   // end-of-file

    private static String[] tokenTable = new String[] {
       "ID",
       "ASSIGN",
       "OR",
       "AND",
       "NOT",
       "EQ",
       "NOTEQ",
       "LESSEQ",
       "LESS",
       "GREATER",
       "GREATEREQ",
       "PLUS",
       "MINUS",
       "TIMES",
       "DIV",
       "INTLITERAL",
       "FLOATLITERAL",
       "BOOLLITERAL",
       "STRINGLITERAL",
       "bool",
       "else",
       "float",
       "for",
       "if",
       "int",
       "return",
       "void",
       "while",
       "LEFTBRACE",
       "RIGHTBRACE",
       "LEFTBRACKET",
       "RIGHTBRACKET",
       "LEFTPAREN",
       "RIGHTPAREN",
       "COMMA",
       "SEMICOLON",
       "ERROR",
       "EOF"
    };

    private static String[] lexemeTable = new String[] {
       "ID",
       "=",
       "||",
       "&&",
       "!",
       "==",
       "!=",
       "<=",
       "<",
       ">",
       ">=",
       "+",
       "-",
       "*",
       "/",
       "INTLITERAL",
       "FLOATLITERAL",
       "BOOLLITERAL",
       "STRINGLITERAL",
       "bool",
       "else",
       "float",
       "for",
       "if",
       "int",
       "return",
       "void",
       "while",
       "{",
       "}",
       "[",
       "]",
       "(",
       ")",
       ",",
       ";",
       "ERROR",
       "EOF"
    };

    private final static int firstKeyword = Token.BOOL,
                             lastKeyword = Token.WHILE;

    public static String spell (int kind) {
	return lexemeTable[kind];
    }

    public SourcePos GetSourcePos() {
	return src_pos;
    } 

    public String GetLexeme() {
	return lexeme;
    } 

}
