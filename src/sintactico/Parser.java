//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package sintactico;



//#line 4 ".\sinj.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexico.Lexico;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;
import ast.*;
import tipos.*;
//#line 27 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short CTE_REAL=258;
public final static short CTE_CARACTER=259;
public final static short CTE_STRING=260;
public final static short ID=261;
public final static short MAIN=262;
public final static short VOID=263;
public final static short INT=264;
public final static short CHAR=265;
public final static short DOUBLE=266;
public final static short WHILE=267;
public final static short IF=268;
public final static short ELSE=269;
public final static short READ=270;
public final static short WRITE=271;
public final static short AND=272;
public final static short DISTINTO=273;
public final static short MAYORIGUAL=274;
public final static short STRUCT=275;
public final static short RETURN=276;
public final static short MENORIGUAL=277;
public final static short OR=278;
public final static short IGUAL=279;
public final static short PUNTOYCOMA=280;
public final static short COMA=281;
public final static short MENOSUNARIO=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    4,    4,    4,    2,    2,    8,    8,
    7,    9,    9,    5,    6,    6,   10,   10,   10,   10,
   13,   13,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   15,   15,    3,
    3,   17,   17,   17,   17,   17,   17,   17,   17,   18,
   18,   12,   12,   19,   19,   20,   11,   11,
};
final static short yylen[] = {                            2,
    9,    2,    0,    1,    1,    1,    2,    0,    1,    1,
    6,    2,    0,    3,    9,    9,    1,    1,    1,    4,
    1,    3,    3,    1,    1,    1,    1,    4,    4,    3,
    4,    1,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    2,    2,    0,    1,    2,
    0,    3,    3,    5,    5,    7,    3,    4,    5,    3,
    1,    1,    0,    3,    1,    2,    3,    1,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   17,   19,   18,    0,    2,    4,    5,
    6,    0,    0,    0,   13,    0,    0,    0,    0,    0,
    0,    0,    0,   14,    0,    0,    0,    0,   65,    0,
    0,   12,    0,    0,   20,   67,   66,    0,    0,    8,
    0,   68,    0,    8,   64,    0,   11,    8,    0,    0,
    9,   10,    7,    0,    0,   24,   26,   25,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    0,   32,
   50,    0,   15,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   16,    0,    0,    0,    0,    0,   53,    0,   52,
   57,    0,   23,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   30,    0,
    0,    0,    0,    0,    0,   58,   29,   59,   51,   61,
   54,    0,   31,    0,    0,   60,   56,
};
final static short yydgoto[] = {                          1,
    2,   46,   50,    8,   51,   10,   52,   53,   21,   33,
   18,   27,  103,   69,  104,   70,   71,  141,   28,   29,
};
final static short yysindex[] = {                         0,
    0, -223, -190,    0,    0,    0, -110,    0,    0,    0,
    0,  -90,    4,    9,    0,   36, -168, -181, -102,   50,
 -107, -102,    3,    0, -159,  -89,   67, -165,    0,   10,
 -148,    0,  -88,   78,    0,    0,    0,   20, -102,    0,
 -157,    0,   33,    0,    0, -114,    0,    0, -114,   15,
    0,    0,    0, -114,   99,    0,    0,    0,   87,   98,
  101,  136,  136,  136,  136,  136,  540,    0,  165,    0,
    0,  120,    0,  136,  136,  136,  128, -150,  547, -135,
   64,  558,  -32,   -4,  421,  136,  136,  136,  136,  136,
  136,  136,  136,  136,  136,  136,  136,  136,  136,  136,
  -91,    0, -103,  138,  433,  484,  136,    0,  136,    0,
    0,  136,    0,  558,  148,  148,  148,  558,  148,   75,
  148,  148,  159,  159,  -32,  -32,  -32,  495,    0, -100,
  144,  144,  141,  547,  137,    0,    0,    0,    0,    0,
    0,  -81,    0,  164,  144,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -132,    0,    0,  151,    0,
    0,  151,    0,    0,    0,    0,    0,  157,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  189,    0,    0,  189,    0,
    0,    0,    0,  189,    0,    0,    0,    0,  506,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  158,    0,    0,  -37,    0,  -12,    0,
    0,   54,  -15,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  162,    0,    0,    0,  158,    0,    0,    0,
    0,    0,    0,   93,  392,  581,  591,  126,  615,    0,
  641,  682,  441,  569,   21,   32,   43,    0,    0,  517,
    0,    0,    0,  -10,  -26,    0,    0,    0,    0,    0,
    0,  247,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   13,  -42,    0,   18,    0,  198,    0,    0,   31,
    0,  191,  112,  902,  107,    0,   -3,  -94,    0,  176,
};
final static int YYTABLESIZE=1014;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         27,
   17,   17,   17,   27,   27,   27,   55,   27,   27,   27,
   28,   72,   15,  101,   28,   28,   28,   31,   28,    9,
   28,   47,   27,   27,   27,   47,   47,   47,   21,   47,
   22,   47,   12,   28,   28,   28,  112,  142,   32,    3,
    4,    5,    6,   19,   47,   47,   47,   65,   20,   26,
  147,    7,   26,   27,   67,   27,   49,   34,  100,   66,
   54,   34,   34,   34,   28,   34,   28,   34,   36,   26,
   13,   14,   36,   36,   36,   22,   36,   47,   36,   37,
   34,   34,   34,   37,   37,   37,   17,   37,   23,   37,
   30,   36,   36,   36,   46,   35,  144,   84,   24,   25,
   99,   36,   37,   37,   37,   97,   95,   38,   96,  101,
   98,   99,   41,   34,   46,   39,   97,   95,   43,   96,
  101,   98,   47,   94,   36,   93,   74,  140,  140,  108,
  109,   65,   40,   44,   94,   37,   93,   75,   67,   68,
   76,  140,   44,   66,  110,  109,   46,   68,   68,    4,
    5,    6,   65,   44,  100,   48,    4,    5,    6,   67,
    7,    4,    5,    6,   66,  100,   45,  107,   65,  129,
   16,   37,   42,   78,   80,   67,   65,  109,  130,  138,
   66,  143,  101,   67,   99,   44,   45,  145,   66,   97,
   95,   63,   96,  101,   98,   99,   65,   62,   48,   11,
   97,   99,   49,   67,  101,   98,   97,   95,   66,   96,
  101,   98,   34,  133,   45,    0,    0,    0,   45,    0,
    0,   51,    0,   73,   94,   92,   93,    0,   51,    0,
    0,    0,    0,   51,   27,   27,   27,    0,  100,   27,
   27,   27,   27,   27,  102,   28,   28,   28,    0,  100,
   28,   28,   28,   28,   28,  100,   47,   47,   47,    0,
    0,   47,   47,   47,   47,   47,  139,   21,   21,   22,
   22,   56,   57,   58,    0,   59,    0,    0,    0,   55,
    0,   60,   61,    0,   62,   63,   55,    0,  146,    0,
   64,   55,   34,   34,   34,    0,    0,   34,   34,   34,
   34,   34,    0,   36,   36,   36,    0,    0,   36,   36,
   36,   36,   36,   51,   37,   37,   37,    0,    0,   37,
   37,   37,   37,   37,    0,   46,    0,    0,    0,    0,
    0,   46,    0,   46,   46,   86,   87,   88,    0,    0,
   89,   90,   91,  111,    0,    0,   86,   87,   88,    0,
    0,   89,   90,   91,  136,   56,   57,   58,    0,   59,
    0,    0,    0,    0,   44,   60,   61,    0,   62,   63,
   44,   55,   44,   44,   64,    0,   56,   57,   58,    0,
   59,    0,    0,    0,    0,    0,   60,   61,    0,   62,
   63,    0,   56,   57,   58,   64,   77,   45,    0,    0,
   56,   57,   58,   45,   59,   45,   45,    0,    0,    0,
   60,   61,    0,   62,   63,    0,    0,    0,    0,   64,
   56,   57,   58,    0,   59,    0,    0,    0,    0,    0,
   60,   61,   41,   62,   63,    0,   86,   87,   88,   64,
    0,   89,   90,   91,    0,   51,   51,   51,    0,   51,
    0,   41,   41,   41,    0,   51,   51,   99,   51,   51,
    0,  113,   97,   95,   51,   96,  101,   98,    0,   99,
    0,    0,    0,  131,   97,   95,    0,   96,  101,   98,
   94,   33,   93,   33,   41,   33,    0,    0,    0,    0,
    0,    0,   94,    0,   93,    0,    0,    0,    0,    0,
   33,   33,   33,   55,   55,   55,    0,   55,    0,    0,
    0,  100,    0,   55,   55,    0,   55,   55,    0,    0,
   99,    0,   55,  100,  132,   97,   95,    0,   96,  101,
   98,   99,    0,   33,    0,    0,   97,   95,    0,   96,
  101,   98,   27,   94,    0,   93,    0,   27,   27,    0,
   27,   27,   27,   31,   94,    0,   93,    0,   31,   31,
    0,   31,   31,   31,    0,   27,   27,   27,    0,    0,
    0,    0,   65,    0,  100,    0,   31,   31,   31,   67,
    0,    0,    0,   99,   66,  100,    0,  137,   97,   95,
    0,   96,  101,   98,   99,    0,   27,    0,    0,   97,
   95,    0,   96,  101,   98,    0,   94,   31,   93,   35,
    0,   35,    0,   35,    0,    0,    0,   94,    0,   93,
    0,   39,    0,    0,    0,    0,    0,    0,   35,   35,
   35,   43,    0,    0,    0,    0,    0,  100,    0,    0,
   39,   39,   39,    0,    0,    0,    0,    0,  100,    0,
   43,   43,   43,    0,    0,   40,    0,    0,    0,    0,
    0,   35,    0,   41,   41,   41,    0,    0,   41,   41,
   41,   41,   41,   39,   40,   40,   40,    0,    0,    0,
    0,   38,    0,   43,    0,    0,    0,    0,    0,    0,
    0,    0,   86,   87,   88,    0,    0,   89,   90,   91,
   38,   38,   38,    0,   86,   87,   88,   40,    0,   89,
   90,   91,   33,   33,   33,    0,    0,   33,   33,   33,
   33,   33,   42,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   38,    0,    0,    0,    0,    0,    0,
    0,   42,   42,   42,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   86,   87,   88,    0,    0,
   89,   90,   91,    0,    0,    0,   86,   87,   88,    0,
    0,   89,   90,   91,   42,    0,    0,   27,   27,   27,
    0,    0,   27,   27,   27,    0,    0,    0,   31,   31,
   31,    0,    0,   31,   31,   31,   56,   57,   58,    0,
   77,    0,    0,    4,    5,    6,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   86,   87,
   88,    0,    0,   89,   90,   91,    0,    0,    0,    0,
   87,   88,    0,    0,   89,    0,   91,    0,    0,    0,
   35,   35,   35,    0,    0,   35,   35,   35,   35,   35,
    0,    0,   39,   39,   39,    0,    0,   39,   39,   39,
   39,   39,   43,   43,   43,    0,    0,   43,   43,   43,
   43,   43,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   40,   40,   40,    0,
    0,   40,   40,   40,   40,   40,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   38,   38,   38,    0,    0,   38,   38,   38,
   38,   38,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   42,   42,   42,    0,    0,   42,   42,
   42,   42,   42,   79,   79,   81,   82,   83,   85,    0,
    0,    0,    0,    0,    0,   79,  105,  106,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  114,  115,  116,
  117,  118,  119,  120,  121,  122,  123,  124,  125,  126,
  127,  128,    0,    0,    0,    0,    0,    0,   79,    0,
  134,    0,    0,  135,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,   91,   41,   42,   43,   49,   45,   46,   47,
   37,   54,  123,   46,   41,   42,   43,  125,   45,    2,
   47,   37,   60,   61,   62,   41,   42,   43,   41,   45,
   41,   47,    2,   60,   61,   62,   41,  132,   21,  263,
  264,  265,  266,   40,   60,   61,   62,   33,   40,   19,
  145,  275,   22,   91,   40,   93,   44,   37,   91,   45,
   48,   41,   42,   43,   91,   45,   93,   47,   37,   39,
  261,  262,   41,   42,   43,   40,   45,   93,   47,   37,
   60,   61,   62,   41,   42,   43,   91,   45,  257,   47,
   41,   60,   61,   62,   41,   93,  139,   67,  280,  281,
   37,  261,   60,   61,   62,   42,   43,   41,   45,   46,
   47,   37,  261,   93,   61,  281,   42,   43,   41,   45,
   46,   47,  280,   60,   93,   62,   40,  131,  132,  280,
  281,   33,  123,   41,   60,   93,   62,   40,   40,  125,
   40,  145,  123,   45,  280,  281,   93,  280,  281,  264,
  265,  266,   33,   61,   91,  123,  264,  265,  266,   40,
  275,  264,  265,  266,   45,   91,   41,   40,   33,  261,
  261,  261,  261,   62,   63,   40,   33,  281,   41,  280,
   45,   41,   46,   40,   37,   93,   61,  269,   45,   42,
   43,   41,   45,   46,   47,   37,   33,   41,   41,    2,
   42,   37,   41,   40,   46,   47,   42,   43,   45,   45,
   46,   47,   22,  107,   39,   -1,   -1,   -1,   93,   -1,
   -1,   33,   -1,  125,   60,   61,   62,   -1,   40,   -1,
   -1,   -1,   -1,   45,  272,  273,  274,   -1,   91,  277,
  278,  279,  280,  281,  125,  272,  273,  274,   -1,   91,
  277,  278,  279,  280,  281,   91,  272,  273,  274,   -1,
   -1,  277,  278,  279,  280,  281,  123,  280,  281,  280,
  281,  257,  258,  259,   -1,  261,   -1,   -1,   -1,   33,
   -1,  267,  268,   -1,  270,  271,   40,   -1,  125,   -1,
  276,   45,  272,  273,  274,   -1,   -1,  277,  278,  279,
  280,  281,   -1,  272,  273,  274,   -1,   -1,  277,  278,
  279,  280,  281,  125,  272,  273,  274,   -1,   -1,  277,
  278,  279,  280,  281,   -1,  272,   -1,   -1,   -1,   -1,
   -1,  278,   -1,  280,  281,  272,  273,  274,   -1,   -1,
  277,  278,  279,  280,   -1,   -1,  272,  273,  274,   -1,
   -1,  277,  278,  279,  280,  257,  258,  259,   -1,  261,
   -1,   -1,   -1,   -1,  272,  267,  268,   -1,  270,  271,
  278,  125,  280,  281,  276,   -1,  257,  258,  259,   -1,
  261,   -1,   -1,   -1,   -1,   -1,  267,  268,   -1,  270,
  271,   -1,  257,  258,  259,  276,  261,  272,   -1,   -1,
  257,  258,  259,  278,  261,  280,  281,   -1,   -1,   -1,
  267,  268,   -1,  270,  271,   -1,   -1,   -1,   -1,  276,
  257,  258,  259,   -1,  261,   -1,   -1,   -1,   -1,   -1,
  267,  268,   41,  270,  271,   -1,  272,  273,  274,  276,
   -1,  277,  278,  279,   -1,  257,  258,  259,   -1,  261,
   -1,   60,   61,   62,   -1,  267,  268,   37,  270,  271,
   -1,   41,   42,   43,  276,   45,   46,   47,   -1,   37,
   -1,   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,
   60,   41,   62,   43,   93,   45,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,
   60,   61,   62,  257,  258,  259,   -1,  261,   -1,   -1,
   -1,   91,   -1,  267,  268,   -1,  270,  271,   -1,   -1,
   37,   -1,  276,   91,   41,   42,   43,   -1,   45,   46,
   47,   37,   -1,   93,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   37,   60,   -1,   62,   -1,   42,   43,   -1,
   45,   46,   47,   37,   60,   -1,   62,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   60,   61,   62,   -1,   -1,
   -1,   -1,   33,   -1,   91,   -1,   60,   61,   62,   40,
   -1,   -1,   -1,   37,   45,   91,   -1,   93,   42,   43,
   -1,   45,   46,   47,   37,   -1,   91,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   60,   91,   62,   41,
   -1,   43,   -1,   45,   -1,   -1,   -1,   60,   -1,   62,
   -1,   41,   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,
   62,   41,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   60,   61,   62,   -1,   -1,   41,   -1,   -1,   -1,   -1,
   -1,   93,   -1,  272,  273,  274,   -1,   -1,  277,  278,
  279,  280,  281,   93,   60,   61,   62,   -1,   -1,   -1,
   -1,   41,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  272,  273,  274,   -1,   -1,  277,  278,  279,
   60,   61,   62,   -1,  272,  273,  274,   93,   -1,  277,
  278,  279,  272,  273,  274,   -1,   -1,  277,  278,  279,
  280,  281,   41,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  272,  273,  274,   -1,   -1,
  277,  278,  279,   -1,   -1,   -1,  272,  273,  274,   -1,
   -1,  277,  278,  279,   93,   -1,   -1,  272,  273,  274,
   -1,   -1,  277,  278,  279,   -1,   -1,   -1,  272,  273,
  274,   -1,   -1,  277,  278,  279,  257,  258,  259,   -1,
  261,   -1,   -1,  264,  265,  266,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,
  274,   -1,   -1,  277,  278,  279,   -1,   -1,   -1,   -1,
  273,  274,   -1,   -1,  277,   -1,  279,   -1,   -1,   -1,
  272,  273,  274,   -1,   -1,  277,  278,  279,  280,  281,
   -1,   -1,  272,  273,  274,   -1,   -1,  277,  278,  279,
  280,  281,  272,  273,  274,   -1,   -1,  277,  278,  279,
  280,  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,  274,   -1,
   -1,  277,  278,  279,  280,  281,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  272,  273,  274,   -1,   -1,  277,  278,  279,
  280,  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  272,  273,  274,   -1,   -1,  277,  278,
  279,  280,  281,   62,   63,   64,   65,   66,   67,   -1,
   -1,   -1,   -1,   -1,   -1,   74,   75,   76,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   86,   87,   88,
   89,   90,   91,   92,   93,   94,   95,   96,   97,   98,
   99,  100,   -1,   -1,   -1,   -1,   -1,   -1,  107,   -1,
  109,   -1,   -1,  112,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=282;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
null,"'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
null,"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CTE_ENTERA","CTE_REAL",
"CTE_CARACTER","CTE_STRING","ID","MAIN","VOID","INT","CHAR","DOUBLE","WHILE",
"IF","ELSE","READ","WRITE","AND","DISTINTO","MAYORIGUAL","STRUCT","RETURN",
"MENORIGUAL","OR","IGUAL","PUNTOYCOMA","COMA","MENOSUNARIO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : listaDefiniciones VOID MAIN '(' ')' '{' listaCampos listaSentencias '}'",
"listaDefiniciones : listaDefiniciones definicion",
"listaDefiniciones :",
"definicion : defVariable",
"definicion : defFuncion",
"definicion : defStruct",
"listaCampos : listaCampos campos",
"listaCampos :",
"campos : defVariable",
"campos : defStruct",
"defStruct : STRUCT '{' listaDefVariables '}' ID PUNTOYCOMA",
"listaDefVariables : listaDefVariables defVariable",
"listaDefVariables :",
"defVariable : tipo listaIds PUNTOYCOMA",
"defFuncion : VOID ID '(' param ')' '{' listaCampos listaSentencias '}'",
"defFuncion : tipo ID '(' param ')' '{' listaCampos listaSentencias '}'",
"tipo : INT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipo : tipo '[' CTE_ENTERA ']'",
"listaExpresiones : expresion",
"listaExpresiones : listaExpresiones COMA expresion",
"expresion : '(' expresion ')'",
"expresion : CTE_ENTERA",
"expresion : CTE_CARACTER",
"expresion : CTE_REAL",
"expresion : ID",
"expresion : '(' tipo ')' expresion",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '.' ID",
"expresion : ID '(' argumentos ')'",
"expresion : operacion",
"operacion : expresion '+' expresion",
"operacion : expresion '*' expresion",
"operacion : expresion '-' expresion",
"operacion : expresion '/' expresion",
"operacion : expresion '%' expresion",
"operacion : expresion '>' expresion",
"operacion : expresion MAYORIGUAL expresion",
"operacion : expresion IGUAL expresion",
"operacion : expresion DISTINTO expresion",
"operacion : expresion '<' expresion",
"operacion : expresion MENORIGUAL expresion",
"operacion : expresion AND expresion",
"operacion : expresion OR expresion",
"operacion : '!' expresion",
"operacion : '-' expresion",
"argumentos :",
"argumentos : listaExpresiones",
"listaSentencias : listaSentencias sentencia",
"listaSentencias :",
"sentencia : WRITE listaExpresiones PUNTOYCOMA",
"sentencia : READ listaExpresiones PUNTOYCOMA",
"sentencia : WHILE '(' expresion ')' cuerpo",
"sentencia : IF '(' expresion ')' cuerpo",
"sentencia : IF '(' expresion ')' cuerpo ELSE cuerpo",
"sentencia : RETURN expresion PUNTOYCOMA",
"sentencia : expresion '=' expresion PUNTOYCOMA",
"sentencia : ID '(' argumentos ')' PUNTOYCOMA",
"cuerpo : '{' listaSentencias '}'",
"cuerpo : sentencia",
"param : parametros",
"param :",
"parametros : parametros COMA parametro",
"parametros : parametro",
"parametro : tipo ID",
"listaIds : listaIds COMA ID",
"listaIds : ID",
};

//#line 168 ".\sinj.y"

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Lexico lexico;
public NodoAST ast;


// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Error Sintáctico en línea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+error);
}

// * El yylval no es un atributo público
public Object getYylval() {
    	return yylval;
}
public void setYylval(Object yylval) {
        this.yylval = yylval;
}

// * Constructor del Sintáctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
	lexico.setParser(this);
}
//#line 561 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 56 ".\sinj.y"
{ this.ast = new Programa(lexico.getLinea(), lexico.getColumna(), (List<Sentencia>)val_peek(1), (List<DefVariable>)val_peek(2), (List<DefVariable>)val_peek(8)); }
break;
case 2:
//#line 59 ".\sinj.y"
{ List<Definicion> d = ((List<Definicion>)val_peek(1)); d.add((Definicion)val_peek(0)); yyval = d;}
break;
case 3:
//#line 60 ".\sinj.y"
{ yyval = new ArrayList<Definicion>(); }
break;
case 4:
//#line 63 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 5:
//#line 64 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 65 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 68 ".\sinj.y"
{ List<Definicion> d = ((List<Definicion>)val_peek(1)); d.add((Definicion)val_peek(0)); yyval = d;}
break;
case 8:
//#line 69 ".\sinj.y"
{ yyval = new ArrayList<Definicion>(); }
break;
case 9:
//#line 72 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 10:
//#line 73 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 76 ".\sinj.y"
{ yyval = new DefStruct(lexico.getLinea(), lexico.getColumna(),(String)val_peek(1), (List<DefVariable>)val_peek(3));}
break;
case 12:
//#line 79 ".\sinj.y"
{ List<Definicion> d = (List<Definicion>)val_peek(1); d.add((Definicion)val_peek(0)); yyval = d ;}
break;
case 13:
//#line 80 ".\sinj.y"
{ yyval = new ArrayList<Definicion>();}
break;
case 14:
//#line 83 ".\sinj.y"
{ yyval = new DefVariable(lexico.getLinea(), lexico.getColumna(), (List<String>)val_peek(1), (Tipo) val_peek(2));}
break;
case 15:
//#line 86 ".\sinj.y"
{ yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), new TipoVoid(lexico.getLinea(), lexico.getColumna()),(String)val_peek(7),(List<DefVariable>)val_peek(5),(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));}
break;
case 16:
//#line 87 ".\sinj.y"
{ yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(8),(String)val_peek(7),(List<DefVariable>)val_peek(5),(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));}
break;
case 17:
//#line 90 ".\sinj.y"
{ yyval = TipoEntero.getInstance(lexico.getLinea(), lexico.getColumna()); }
break;
case 18:
//#line 91 ".\sinj.y"
{ yyval = TipoDouble.getInstance(lexico.getLinea(), lexico.getColumna()); }
break;
case 19:
//#line 92 ".\sinj.y"
{ yyval = TipoCaracter.getInstance(lexico.getLinea(), lexico.getColumna()); }
break;
case 20:
//#line 93 ".\sinj.y"
{ yyval = new TipoArray(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(3),(int)val_peek(1));}
break;
case 21:
//#line 96 ".\sinj.y"
{ List<Expresion> e= new ArrayList<Expresion>(); e.add((Expresion)val_peek(0)); yyval = e;  }
break;
case 22:
//#line 97 ".\sinj.y"
{ List<Expresion> e = (List<Expresion>)val_peek(2);e.add((Expresion)val_peek(0)); yyval = e; }
break;
case 23:
//#line 100 ".\sinj.y"
{ yyval = val_peek(1); }
break;
case 24:
//#line 101 ".\sinj.y"
{ yyval = new LiteralEntero(lexico.getLinea(), lexico.getColumna(),(int)val_peek(0)); }
break;
case 25:
//#line 102 ".\sinj.y"
{ String s=(String)val_peek(0); char c=s.charAt(0); yyval = new LiteralCaracter(lexico.getLinea(), lexico.getColumna(), c); }
break;
case 26:
//#line 103 ".\sinj.y"
{ yyval = new LiteralReal(lexico.getLinea(), lexico.getColumna(),(double)val_peek(0)); }
break;
case 27:
//#line 104 ".\sinj.y"
{ yyval = new Variable(lexico.getLinea(), lexico.getColumna(),(String)val_peek(0)) ;}
break;
case 28:
//#line 105 ".\sinj.y"
{ yyval = new Cast(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(2), (Expresion)val_peek(0));}
break;
case 29:
//#line 106 ".\sinj.y"
{ yyval = new AccesoArray(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3),(Expresion)val_peek(1));}
break;
case 30:
//#line 107 ".\sinj.y"
{ yyval = new AccesoCampo(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0), (Expresion)val_peek(2)); }
break;
case 31:
//#line 108 ".\sinj.y"
{ yyval = new LlamadaFuncion(lexico.getLinea(), lexico.getColumna(),(String)val_peek(3),(List<Expresion>)val_peek(1));}
break;
case 33:
//#line 112 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "+"); }
break;
case 34:
//#line 113 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "*"); }
break;
case 35:
//#line 114 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "-"); }
break;
case 36:
//#line 115 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "/"); }
break;
case 37:
//#line 116 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "%"); }
break;
case 38:
//#line 117 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), ">"); }
break;
case 39:
//#line 118 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), ">="); }
break;
case 40:
//#line 119 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "=="); }
break;
case 41:
//#line 120 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "!="); }
break;
case 42:
//#line 121 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "<"); }
break;
case 43:
//#line 122 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "<="); }
break;
case 44:
//#line 123 ".\sinj.y"
{ yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "&&"); }
break;
case 45:
//#line 124 ".\sinj.y"
{ yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (Expresion)val_peek(0), "||"); }
break;
case 46:
//#line 125 ".\sinj.y"
{ yyval = new Negacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(0)); }
break;
case 47:
//#line 126 ".\sinj.y"
{ yyval = new MenosUnario(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(0)) ;}
break;
case 48:
//#line 129 ".\sinj.y"
{ yyval = new ArrayList<Expresion>();}
break;
case 49:
//#line 130 ".\sinj.y"
{ yyval = val_peek(0);}
break;
case 50:
//#line 133 ".\sinj.y"
{ List<Sentencia> s = (List<Sentencia>)val_peek(1); s.add((Sentencia)val_peek(0)); yyval = s ;}
break;
case 51:
//#line 134 ".\sinj.y"
{ yyval = new ArrayList<Sentencia>();}
break;
case 52:
//#line 137 ".\sinj.y"
{ yyval = new Escritura(lexico.getLinea(), lexico.getColumna(),(List<Expresion>)val_peek(1));}
break;
case 53:
//#line 138 ".\sinj.y"
{ yyval = new Lectura(lexico.getLinea(), lexico.getColumna(),(List<Expresion>)val_peek(1));}
break;
case 54:
//#line 139 ".\sinj.y"
{ yyval = new While(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2),(List<Sentencia>) val_peek(0));}
break;
case 55:
//#line 140 ".\sinj.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2),(List<Sentencia>) val_peek(0));}
break;
case 56:
//#line 141 ".\sinj.y"
{ yyval = new IfElse(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(4),(List<Sentencia>) val_peek(2),(List<Sentencia>) val_peek(0));}
break;
case 57:
//#line 142 ".\sinj.y"
{ yyval = new Return(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(1)); }
break;
case 58:
//#line 143 ".\sinj.y"
{ yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3),(Expresion)val_peek(1));}
break;
case 59:
//#line 144 ".\sinj.y"
{ yyval = new LlamadaProcedimiento(lexico.getLinea(), lexico.getColumna(),(String)val_peek(4),(List<Expresion>)val_peek(2));}
break;
case 60:
//#line 147 ".\sinj.y"
{ yyval = val_peek(1);}
break;
case 61:
//#line 148 ".\sinj.y"
{ List<Sentencia> s = new ArrayList<Sentencia>(); s.add((Sentencia)val_peek(0)); yyval = s ;}
break;
case 62:
//#line 152 ".\sinj.y"
{ yyval = val_peek(0);}
break;
case 63:
//#line 153 ".\sinj.y"
{ yyval = new ArrayList<DefVariable>();}
break;
case 64:
//#line 156 ".\sinj.y"
{ List<DefVariable> df = (List<DefVariable>)val_peek(2); df.add((DefVariable)val_peek(0)); yyval = df ;}
break;
case 65:
//#line 157 ".\sinj.y"
{ List<DefVariable> dv = new ArrayList<DefVariable>(); dv.add((DefVariable)val_peek(0)); yyval=dv; }
break;
case 66:
//#line 160 ".\sinj.y"
{ List<String> s = new ArrayList<String>(); s.add((String)val_peek(0)); DefVariable v = new DefVariable(lexico.getLinea(), lexico.getColumna(), s, (Tipo) val_peek(1)); yyval = v;	}
break;
case 67:
//#line 163 ".\sinj.y"
{ List<String> st = (List<String>)val_peek(2); st.add((String)val_peek(0)); yyval = st ;}
break;
case 68:
//#line 164 ".\sinj.y"
{ List<String> s=new ArrayList<String>(); s.add((String)val_peek(0)); yyval = s;}
break;
//#line 978 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
