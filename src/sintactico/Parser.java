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
/* * Definiciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexico.Lexico;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;
import tipos.*;
import ast.*;
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
public final static short ID=257;
public final static short CTE_ENTERA=258;
public final static short CTE_CARACTER=259;
public final static short CTE_REAL=260;
public final static short CTE_STRING=261;
public final static short STRUCT=262;
public final static short CHAR=263;
public final static short DOUBLE=264;
public final static short INT=265;
public final static short DISTINTO=266;
public final static short IGUAL=267;
public final static short MAYORIGUAL=268;
public final static short MENORIGUAL=269;
public final static short AND=270;
public final static short OR=271;
public final static short IF=272;
public final static short ELSE=273;
public final static short WHILE=274;
public final static short MAIN=275;
public final static short VOID=276;
public final static short RETURN=277;
public final static short WRITE=278;
public final static short READ=279;
public final static short MENOSUNARIO=280;
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
    0,    0,    0,   19,   18,   17,    0,    2,    4,    5,
    6,    0,   13,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   14,    0,    0,   12,    0,    0,    0,
    0,   65,    0,    0,   20,   67,    0,   68,   66,    0,
    0,    8,    0,   11,    8,   64,    0,    8,    0,    0,
    9,   10,    7,    0,    0,    0,   24,   25,   26,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    0,   32,
   50,    0,   15,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   16,    0,    0,    0,    0,    0,   57,   52,    0,
   53,    0,   23,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   30,    0,
    0,    0,    0,    0,    0,   58,   29,   59,   51,   61,
    0,   54,   31,    0,    0,   60,   56,
};
final static short yydgoto[] = {                          1,
    2,   47,   50,    8,   51,   10,   52,   53,   19,   28,
   18,   30,  103,   69,  104,   70,   71,  141,   31,   32,
};
final static short yysindex[] = {                         0,
    0, -177, -109,    0,    0,    0, -221,    0,    0,    0,
    0,  -88,    0,  -24,   33,   53, -156,    4, -123, -168,
   66, -168,   15,    0, -148, -146,    0,  -86,  -85,   73,
   71,    0,    5,   89,    0,    0,   72,    0,    0,    9,
 -168,    0,   10,    0,    0,    0, -138,    0, -138,  -33,
    0,    0,    0, -138,  -25,   99,    0,    0,    0,  103,
  104,  105,  105,  105,  105,  105,   65,    0,  394,    0,
    0,   -2,    0,  105,  105,  105,  106,  406,   13,  645,
   21,  733,  -35,  -32,  430,  105,  105,  105,  105,  105,
  105,  105,  105,  105,  105,  105,  105,  105,  105,  105,
 -110,    0,  108,  107,  457,  464,  105,    0,    0,  105,
    0,  105,    0,  -20,  -20,  -20,  -20,  733,  733,  491,
  -20,  -20,  -13,  -13,  -35,  -35,  -35,  498,    0,   90,
    6,    6,  112,  645,  117,    0,    0,    0,    0,    0,
 -113,    0,    0,   29,    6,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   24,    0,    0,    0,  123,
    0,  123,    0,    0,    0,    0,    0,    0,    0,    0,
  124,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   37,    0,   37,    0,
    0,    0,    0,   37,    0,  550,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  126,    0,    0,   75,    0,    0,   -4,
    0,  -40,  150,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  129,    0,    0,    0,  126,    0,    0,    0,
    0,    0,    0,  544,  678,  781,  787,   -9,   20,    0,
  824,  831,  740,  746,  310,  349,  385,    0,    0,  587,
    0,    0,    0,    1,  114,    0,    0,    0,    0,    0,
   61,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   -1,  -36,    0,   39,    0,  175,    0,    0,    8,
    0,  156,    3,  864,   74,    0,  -41,  -56,    0,  138,
};
final static int YYTABLESIZE=1102;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         65,
   46,   26,   17,   46,   17,   17,   67,   65,  112,   12,
  101,   66,   55,   13,   67,   20,   99,   72,   46,   66,
   46,   97,   95,   99,   96,  101,   98,   29,   97,   29,
   65,   44,  101,   98,   44,   14,   21,   67,   65,   21,
    9,   22,   66,   49,   22,   67,   54,   25,   29,   44,
   66,   44,   46,   15,   21,  100,  110,   27,   17,   22,
   45,   65,   24,   45,  110,   79,   81,   68,   67,   51,
  100,  109,   21,   66,   84,  142,   51,  100,   45,  111,
   45,   51,   68,   44,    3,    4,    5,    6,  147,  140,
  140,   68,   22,   55,    4,    5,    6,   65,    7,   73,
   55,   23,  144,  140,   67,   55,   33,   35,   36,   66,
   37,   27,   45,   40,   41,   27,   27,   27,   27,   27,
   27,   27,  102,    3,    4,    5,    6,   42,  139,   43,
   44,   45,   48,   27,   27,   27,   27,   65,   74,    4,
    5,    6,   75,   76,   67,  107,  129,  130,  138,   66,
   28,  110,  143,  146,   28,   28,   28,   28,   28,  145,
   28,   51,  101,   63,   62,   27,   48,   27,   16,   49,
   38,   39,   28,   28,   28,   28,   11,   34,   46,    0,
  133,    0,    0,    0,    0,   55,   47,    0,    0,    0,
   47,   47,   47,   47,   47,    0,   47,    0,    0,    0,
    0,    0,    0,    0,   28,    0,   28,    0,   47,   47,
   47,   47,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   56,   57,   58,   59,    0,    0,   46,
   46,   56,   57,   58,   59,    0,    0,    0,   60,    0,
   61,    0,   47,   62,   63,   64,   60,    0,   61,    0,
    0,   62,   63,   64,   56,   57,   58,   59,    0,    0,
   44,   44,   56,   57,   58,   59,    0,    0,    0,   60,
    0,   61,    0,    0,   62,   63,   64,   60,    0,   61,
    0,    0,   62,   63,   64,   56,   57,   58,   59,   45,
   45,    0,    0,   51,   51,   51,   51,    0,    0,    0,
   60,    0,   61,    0,    0,   62,   63,   64,   51,    0,
   51,    0,    0,   51,   51,   51,    0,   55,   55,   55,
   55,   77,   57,   58,   59,    0,    0,    4,    5,    6,
    0,    0,   55,    0,   55,    0,    0,   55,   55,   55,
   27,   27,   27,   27,   27,   27,   34,    0,    0,    0,
   34,   34,   34,   34,   34,    0,   34,    0,    0,    0,
    0,   77,   57,   58,   59,    0,    0,    0,   34,   34,
   34,   34,    0,    0,    0,    0,    0,    0,    0,   28,
   28,   28,   28,   28,   28,   36,    0,    0,    0,   36,
   36,   36,   36,   36,    0,   36,    0,    0,    0,    0,
    0,    0,   34,    0,    0,    0,    0,   36,   36,   36,
   36,    0,    0,    0,    0,   47,   47,   47,   47,   47,
   47,   37,    0,    0,    0,   37,   37,   37,   37,   37,
   99,   37,    0,    0,    0,   97,   95,    0,   96,  101,
   98,   36,   99,   37,   37,   37,   37,   97,   95,    0,
   96,  101,   98,   94,   92,   93,    0,    0,    0,    0,
    0,    0,    0,    0,  108,   94,   99,   93,    0,    0,
  113,   97,   95,    0,   96,  101,   98,   37,    0,    0,
    0,    0,    0,    0,  100,    0,    0,    0,    0,   94,
    0,   93,    0,   99,    0,    0,  100,  131,   97,   95,
   99,   96,  101,   98,  132,   97,   95,    0,   96,  101,
   98,    0,    0,    0,    0,    0,   94,    0,   93,    0,
  100,    0,    0,   94,    0,   93,    0,   99,    0,    0,
    0,    0,   97,   95,   99,   96,  101,   98,    0,   97,
   95,    0,   96,  101,   98,    0,    0,  100,    0,  136,
   94,    0,   93,    0,  100,    0,    0,   94,    0,   93,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   34,   34,   34,   34,   34,
   34,  100,    0,    0,   41,    0,   27,   41,  100,    0,
  137,   27,   27,    0,   27,   27,   27,    0,    0,    0,
    0,    0,   41,   41,   41,   41,    0,    0,    0,   27,
   27,   27,    0,    0,   36,   36,   36,   36,   36,   36,
    0,    0,    0,   31,    0,    0,    0,    0,   31,   31,
    0,   31,   31,   31,    0,    0,   41,    0,    0,    0,
   27,    0,    0,    0,    0,    0,   31,   31,   31,    0,
   37,   37,   37,   37,   37,   37,    0,    0,    0,   86,
   87,   88,   89,   90,   91,    0,    0,    0,    0,    0,
    0,   86,   87,   88,   89,   90,   91,   31,    0,    0,
    0,   99,    0,    0,    0,    0,   97,   95,    0,   96,
  101,   98,    0,    0,    0,   86,   87,   88,   89,   90,
   91,    0,    0,    0,   94,    0,   93,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   40,    0,
    0,   40,   86,   87,   88,   89,   90,   91,    0,   86,
   87,   88,   89,   90,   91,  100,   40,   40,   40,   40,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   86,   87,   88,   89,
   90,   91,    0,   86,   87,   88,   89,   90,   91,   99,
   40,    0,    0,    0,   97,   95,    0,   96,  101,   98,
   33,    0,   33,   33,   33,    0,   35,    0,   35,   35,
   35,    0,   94,    0,   93,    0,    0,    0,   33,   33,
   33,   33,    0,    0,   35,   35,   35,   35,    0,   41,
   41,   41,   41,   41,   41,   27,   27,   27,   27,   27,
   27,   39,    0,  100,   39,    0,    0,   43,    0,    0,
   43,    0,   33,    0,    0,    0,    0,    0,   35,   39,
   39,   39,   39,    0,    0,   43,   43,   43,   43,    0,
    0,    0,   31,   31,   31,   31,   31,   31,    0,    0,
    0,    0,    0,    0,   38,    0,    0,   38,    0,    0,
    0,   42,    0,   39,   42,    0,    0,    0,    0,   43,
    0,    0,   38,   38,   38,   38,    0,    0,    0,   42,
   42,   42,   42,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   86,   87,   88,   89,   90,   91,   38,    0,    0,    0,
    0,    0,    0,   42,    0,   78,   80,   80,   82,   83,
   85,    0,    0,    0,    0,    0,    0,   80,  105,  106,
    0,    0,    0,   40,   40,   40,   40,   40,   40,  114,
  115,  116,  117,  118,  119,  120,  121,  122,  123,  124,
  125,  126,  127,  128,    0,    0,    0,    0,    0,    0,
   80,    0,    0,  134,    0,  135,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   86,   87,
   88,   89,    0,    0,    0,   33,   33,   33,   33,   33,
   33,   35,   35,   35,   35,   35,   35,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   39,   39,   39,   39,
   39,   39,   43,   43,   43,   43,   43,   43,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   38,
   38,   38,   38,   38,   38,    0,   42,   42,   42,   42,
   42,   42,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,  125,   91,   44,   91,   91,   40,   33,   41,    2,
   46,   45,   49,  123,   40,   40,   37,   54,   59,   45,
   61,   42,   43,   37,   45,   46,   47,   20,   42,   22,
   33,   41,   46,   47,   44,  257,   41,   40,   33,   44,
    2,   41,   45,   45,   44,   40,   48,   44,   41,   59,
   45,   61,   93,  275,   59,   91,   44,   19,   91,   59,
   41,   33,   59,   44,   44,   63,   64,   44,   40,   33,
   91,   59,   40,   45,   67,  132,   40,   91,   59,   59,
   61,   45,   59,   93,  262,  263,  264,  265,  145,  131,
  132,  125,   40,   33,  263,  264,  265,   33,  276,  125,
   40,  258,  139,  145,   40,   45,   41,   93,  257,   45,
  257,   37,   93,   41,   44,   41,   42,   43,   44,   45,
   46,   47,  125,  262,  263,  264,  265,  123,  123,   41,
   59,  123,  123,   59,   60,   61,   62,   33,   40,  263,
  264,  265,   40,   40,   40,   40,  257,   41,   59,   45,
   37,   44,   41,  125,   41,   42,   43,   44,   45,  273,
   47,  125,   46,   41,   41,   91,   41,   93,  257,   41,
  257,  257,   59,   60,   61,   62,    2,   22,   41,   -1,
  107,   -1,   -1,   -1,   -1,  125,   37,   -1,   -1,   -1,
   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   91,   -1,   93,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,  270,
  271,  257,  258,  259,  260,   -1,   -1,   -1,  272,   -1,
  274,   -1,   93,  277,  278,  279,  272,   -1,  274,   -1,
   -1,  277,  278,  279,  257,  258,  259,  260,   -1,   -1,
  270,  271,  257,  258,  259,  260,   -1,   -1,   -1,  272,
   -1,  274,   -1,   -1,  277,  278,  279,  272,   -1,  274,
   -1,   -1,  277,  278,  279,  257,  258,  259,  260,  270,
  271,   -1,   -1,  257,  258,  259,  260,   -1,   -1,   -1,
  272,   -1,  274,   -1,   -1,  277,  278,  279,  272,   -1,
  274,   -1,   -1,  277,  278,  279,   -1,  257,  258,  259,
  260,  257,  258,  259,  260,   -1,   -1,  263,  264,  265,
   -1,   -1,  272,   -1,  274,   -1,   -1,  277,  278,  279,
  266,  267,  268,  269,  270,  271,   37,   -1,   -1,   -1,
   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  266,
  267,  268,  269,  270,  271,   37,   -1,   -1,   -1,   41,
   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,  266,  267,  268,  269,  270,
  271,   37,   -1,   -1,   -1,   41,   42,   43,   44,   45,
   37,   47,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   93,   37,   59,   60,   61,   62,   42,   43,   -1,
   45,   46,   47,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   59,   60,   37,   62,   -1,   -1,
   41,   42,   43,   -1,   45,   46,   47,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   60,
   -1,   62,   -1,   37,   -1,   -1,   91,   41,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   60,   -1,   62,   -1,
   91,   -1,   -1,   60,   -1,   62,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   37,   45,   46,   47,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   91,   -1,   59,
   60,   -1,   62,   -1,   91,   -1,   -1,   60,   -1,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  266,  267,  268,  269,  270,
  271,   91,   -1,   -1,   41,   -1,   37,   44,   91,   -1,
   93,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,   60,
   61,   62,   -1,   -1,  266,  267,  268,  269,  270,  271,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   93,   -1,   -1,   -1,
   91,   -1,   -1,   -1,   -1,   -1,   60,   61,   62,   -1,
  266,  267,  268,  269,  270,  271,   -1,   -1,   -1,  266,
  267,  268,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,
   -1,  266,  267,  268,  269,  270,  271,   91,   -1,   -1,
   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,  266,  267,  268,  269,  270,
  271,   -1,   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,
   -1,   44,  266,  267,  268,  269,  270,  271,   -1,  266,
  267,  268,  269,  270,  271,   91,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,  268,  269,
  270,  271,   -1,  266,  267,  268,  269,  270,  271,   37,
   93,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   41,   -1,   43,   44,   45,   -1,   41,   -1,   43,   44,
   45,   -1,   60,   -1,   62,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   59,   60,   61,   62,   -1,  266,
  267,  268,  269,  270,  271,  266,  267,  268,  269,  270,
  271,   41,   -1,   91,   44,   -1,   -1,   41,   -1,   -1,
   44,   -1,   93,   -1,   -1,   -1,   -1,   -1,   93,   59,
   60,   61,   62,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,   -1,  266,  267,  268,  269,  270,  271,   -1,   -1,
   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,
   -1,   41,   -1,   93,   44,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  266,  267,  268,  269,  270,  271,   93,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   -1,   62,   63,   64,   65,   66,
   67,   -1,   -1,   -1,   -1,   -1,   -1,   74,   75,   76,
   -1,   -1,   -1,  266,  267,  268,  269,  270,  271,   86,
   87,   88,   89,   90,   91,   92,   93,   94,   95,   96,
   97,   98,   99,  100,   -1,   -1,   -1,   -1,   -1,   -1,
  107,   -1,   -1,  110,   -1,  112,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,
  268,  269,   -1,   -1,   -1,  266,  267,  268,  269,  270,
  271,  266,  267,  268,  269,  270,  271,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,  268,  269,
  270,  271,  266,  267,  268,  269,  270,  271,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  266,
  267,  268,  269,  270,  271,   -1,  266,  267,  268,  269,
  270,  271,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=280;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
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
null,null,null,null,null,null,null,null,null,"ID","CTE_ENTERA","CTE_CARACTER",
"CTE_REAL","CTE_STRING","STRUCT","CHAR","DOUBLE","INT","DISTINTO","IGUAL",
"MAYORIGUAL","MENORIGUAL","AND","OR","IF","ELSE","WHILE","MAIN","VOID","RETURN",
"WRITE","READ","MENOSUNARIO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : listaDefiniciones VOID MAIN '(' ')' '{' listaCampos listaSentencias '}'",
"listaDefiniciones : listaDefiniciones Definicion",
"listaDefiniciones :",
"Definicion : defVariable",
"Definicion : defFuncion",
"Definicion : defStruct",
"listaCampos : listaCampos campos",
"listaCampos :",
"campos : defVariable",
"campos : defStruct",
"defStruct : STRUCT '{' listaDefVariables '}' ID ';'",
"listaDefVariables : listaDefVariables defVariable",
"listaDefVariables :",
"defVariable : tipo listaIds ';'",
"defFuncion : VOID ID '(' param ')' '{' listaCampos listaSentencias '}'",
"defFuncion : tipo ID '(' param ')' '{' listaCampos listaSentencias '}'",
"tipo : INT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipo : tipo '[' CTE_ENTERA ']'",
"listaExpresiones : expresion",
"listaExpresiones : listaExpresiones ',' expresion",
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
"sentencia : WRITE listaExpresiones ';'",
"sentencia : READ listaExpresiones ';'",
"sentencia : WHILE '(' expresion ')' cuerpo",
"sentencia : IF '(' expresion ')' cuerpo",
"sentencia : IF '(' expresion ')' cuerpo ELSE cuerpo",
"sentencia : RETURN expresion ';'",
"sentencia : expresion '=' expresion ';'",
"sentencia : ID '(' argumentos ')' ';'",
"cuerpo : '{' listaSentencias '}'",
"cuerpo : sentencia",
"param : parametros",
"param :",
"parametros : parametros ',' parametro",
"parametros : parametro",
"parametro : tipo ID",
"listaIds : listaIds ',' ID",
"listaIds : ID",
};

//#line 175 ".\sinj.y"

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
//#line 576 "Parser.java"
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
//#line 53 ".\sinj.y"
{ this.ast = new Programa(lexico.getLinea(), lexico.getColumna(), (List<DefVariable>)val_peek(8), (List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)); }
break;
case 2:
//#line 55 ".\sinj.y"
{List<Definicion> d = ((List<Definicion>)val_peek(1)); d.add((Definicion)val_peek(0)); yyval = d;}
break;
case 3:
//#line 56 ".\sinj.y"
{ yyval = new ArrayList<Definicion>(); }
break;
case 4:
//#line 59 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 5:
//#line 60 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 61 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 64 ".\sinj.y"
{List<Definicion> d = ((List<Definicion>)val_peek(1)); d.add((Definicion)val_peek(0)); yyval = d;}
break;
case 8:
//#line 65 ".\sinj.y"
{ yyval = new ArrayList<Definicion>(); }
break;
case 9:
//#line 68 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 10:
//#line 69 ".\sinj.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 72 ".\sinj.y"
{ yyval = new DefStruct(lexico.getLinea(), lexico.getColumna(),(String)val_peek(1),(List<DefVariable>)val_peek(3));}
break;
case 12:
//#line 75 ".\sinj.y"
{List<Definicion> d = (List<Definicion>)val_peek(1); d.add((Definicion)val_peek(0)); yyval = d ;}
break;
case 13:
//#line 76 ".\sinj.y"
{ yyval = new ArrayList<Definicion>();}
break;
case 14:
//#line 79 ".\sinj.y"
{ yyval = new DefVariable(lexico.getLinea(), lexico.getColumna(),(Tipo) val_peek(2), (List<String>)val_peek(1));}
break;
case 15:
//#line 82 ".\sinj.y"
{ yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), new TipoVoid(lexico.getLinea(), lexico.getColumna()),(String)val_peek(7),(List<DefVariable>)val_peek(5),(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));}
break;
case 16:
//#line 83 ".\sinj.y"
{ yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(8),(String)val_peek(7),(List<DefVariable>)val_peek(5),(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));}
break;
case 17:
//#line 86 ".\sinj.y"
{ yyval = TipoEntero.getInstance(lexico.getLinea(), lexico.getColumna()); }
break;
case 18:
//#line 87 ".\sinj.y"
{ yyval = TipoDouble.getInstance(lexico.getLinea(), lexico.getColumna()); }
break;
case 19:
//#line 88 ".\sinj.y"
{ yyval = TipoCaracter.getInstance(lexico.getLinea(), lexico.getColumna()); }
break;
case 20:
//#line 89 ".\sinj.y"
{ yyval = new TipoArray(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(3),(int)val_peek(1));}
break;
case 21:
//#line 92 ".\sinj.y"
{ List<Expresion> e= new ArrayList<Expresion>(); e.add((Expresion)val_peek(0)); yyval = e;  }
break;
case 22:
//#line 93 ".\sinj.y"
{ List<Expresion> e = (List<Expresion>)val_peek(2);e.add((Expresion)val_peek(0)); yyval = e; }
break;
case 23:
//#line 96 ".\sinj.y"
{ yyval = val_peek(1); }
break;
case 24:
//#line 97 ".\sinj.y"
{ yyval = new LiteralEntero(lexico.getLinea(), lexico.getColumna(),(int)val_peek(0)); }
break;
case 25:
//#line 98 ".\sinj.y"
{ String s=(String)val_peek(0); char c=s.charAt(0); yyval = new LiteralCaracter(lexico.getLinea(), lexico.getColumna(), c); }
break;
case 26:
//#line 99 ".\sinj.y"
{ yyval = new LiteralReal(lexico.getLinea(), lexico.getColumna(),(double)val_peek(0)); }
break;
case 27:
//#line 100 ".\sinj.y"
{ yyval = new Variable(lexico.getLinea(), lexico.getColumna(),(String)val_peek(0)) ;}
break;
case 28:
//#line 101 ".\sinj.y"
{ yyval = new Cast(lexico.getLinea(), lexico.getColumna(),(Tipo)val_peek(2), (Expresion)val_peek(0));}
break;
case 29:
//#line 102 ".\sinj.y"
{ yyval = new AccesoArray(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3),(Expresion)val_peek(1));}
break;
case 30:
//#line 103 ".\sinj.y"
{ yyval = new AccesoCampo(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), (String)val_peek(0)); }
break;
case 31:
//#line 104 ".\sinj.y"
{ yyval = new LlamadaFuncion(lexico.getLinea(), lexico.getColumna(),(String)val_peek(3),(List<Expresion>)val_peek(1));}
break;
case 33:
//#line 108 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "+", (Expresion)val_peek(0)); }
break;
case 34:
//#line 109 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "*", (Expresion)val_peek(0)); }
break;
case 35:
//#line 110 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "-", (Expresion)val_peek(0)); }
break;
case 36:
//#line 111 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "/", (Expresion)val_peek(0)); }
break;
case 37:
//#line 112 ".\sinj.y"
{ yyval = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "%", (Expresion)val_peek(0)); }
break;
case 38:
//#line 113 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), ">", (Expresion)val_peek(0)); }
break;
case 39:
//#line 114 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), ">=", (Expresion)val_peek(0)); }
break;
case 40:
//#line 115 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "==", (Expresion)val_peek(0)); }
break;
case 41:
//#line 116 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "!=", (Expresion)val_peek(0)); }
break;
case 42:
//#line 117 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "<", (Expresion)val_peek(0)); }
break;
case 43:
//#line 118 ".\sinj.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "<=", (Expresion)val_peek(0)); }
break;
case 44:
//#line 119 ".\sinj.y"
{ yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "&&", (Expresion)val_peek(0)); }
break;
case 45:
//#line 120 ".\sinj.y"
{ yyval = new OperacionLogica(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(2), "||", (Expresion)val_peek(0)); }
break;
case 46:
//#line 121 ".\sinj.y"
{ yyval = new Negacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(0)); }
break;
case 47:
//#line 122 ".\sinj.y"
{ yyval = new MenosUnario(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(0)) ;}
break;
case 48:
//#line 125 ".\sinj.y"
{ yyval = new ArrayList<Expresion>();}
break;
case 49:
//#line 126 ".\sinj.y"
{ yyval = val_peek(0);}
break;
case 50:
//#line 133 ".\sinj.y"
{List<Sentencia> s = (List<Sentencia>)val_peek(1); s.add((Sentencia)val_peek(0)); yyval = s ;}
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
//#line 144 ".\sinj.y"
{ yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3),(Expresion)val_peek(1));}
break;
case 59:
//#line 145 ".\sinj.y"
{ yyval = new LlamadaProcedimiento(lexico.getLinea(), lexico.getColumna(),(String)val_peek(4),(List<Expresion>)val_peek(2));}
break;
case 60:
//#line 148 ".\sinj.y"
{yyval = val_peek(1);}
break;
case 61:
//#line 149 ".\sinj.y"
{List<Sentencia> s = new ArrayList<Sentencia>(); s.add((Sentencia)val_peek(0)); yyval = s ;}
break;
case 62:
//#line 156 ".\sinj.y"
{ yyval = val_peek(0);}
break;
case 63:
//#line 157 ".\sinj.y"
{ yyval = new ArrayList<DefVariable>();}
break;
case 64:
//#line 160 ".\sinj.y"
{ List<DefVariable> df = (List<DefVariable>)val_peek(2); df.add((DefVariable)val_peek(0)); yyval = df ;}
break;
case 65:
//#line 161 ".\sinj.y"
{ List<DefVariable> dv = new ArrayList<DefVariable>(); dv.add((DefVariable)val_peek(0)); yyval=dv; }
break;
case 66:
//#line 164 ".\sinj.y"
{ List<String> s = new ArrayList<String>(); 
									s.add((String)val_peek(0)); 
									DefVariable v = new DefVariable(lexico.getLinea(), lexico.getColumna(),(Tipo) val_peek(1), s); 
									yyval = v;	}
break;
case 67:
//#line 170 ".\sinj.y"
{List<String> st = (List<String>)val_peek(2); st.add((String)val_peek(0)); yyval = st ;}
break;
case 68:
//#line 171 ".\sinj.y"
{List<String> s=new ArrayList<String>(); s.add((String)val_peek(0)); yyval = s;}
break;
//#line 996 "Parser.java"
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
