

%{
// * Definiciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexico.Lexico;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;
import tipos.*;
import ast.*;
%}

// * Definiciones Yacc
%token CTE_ENTERA
%token CTE_REAL
%token CTE_CARACTER
%token CTE_STRING
%token ID
%token MAIN
%token VOID
%token INT
%token CHAR
%token DOUBLE
%token WHILE
%token IF
%token ELSE
%token READ
%token WRITE
%token AND
%token DISTINTO
%token MAYORIGUAL
%token STRUCT
%token RETURN
%token MENORIGUAL
%token OR
%token IGUAL

%right '='
%left AND OR '!'
%left '>' MAYORIGUAL '<' MENORIGUAL IGUAL DISTINTO
%left '+' '-'
%left '*' '/' '%'
%right MENOSUNARIO
%nonassoc '[' ']'
%nonassoc '(' ')'
%nonassoc '.'


%%
// * Gramática y acciones Yacc

programa: listaDefiniciones VOID MAIN '(' ')' '{' listaCampos listaSentencias '}'	{ this.ast = new Programa(lexico.getLinea(), lexico.getColumna(), (List<DefVariable>)$1, (List<DefVariable>)$7, (List<Sentencia>)$8); }

listaDefiniciones: listaDefiniciones Definicion	{List<Definicion> d = ((List<Definicion>)$1); d.add((Definicion)$2); $$ = d;}
				  |										{ $$ = new ArrayList<Definicion>(); }
				  ;

Definicion: defVariable 		{ $$ = $1; }
			|defFuncion			{ $$ = $1; }
			|defStruct			{ $$ = $1; }
		    ;
		    
listaCampos: listaCampos campos	{List<Definicion> d = ((List<Definicion>)$1); d.add((Definicion)$2); $$ = d;}
				  |										{ $$ = new ArrayList<Definicion>(); }
				  ;

campos: defVariable 		{ $$ = $1; }
			|defStruct			{ $$ = $1; }
		    ;
		   
defStruct:	STRUCT '{' listaDefVariables '}' ID ';'	{ $$ = new DefStruct(lexico.getLinea(), lexico.getColumna(),(String)$5,(List<DefVariable>)$3);}
		;
		   
listaDefVariables: listaDefVariables defVariable	{List<Definicion> d = (List<Definicion>)$1; d.add((Definicion)$2); $$ = d ;}
			|										{ $$ = new ArrayList<Definicion>();}
			;
		   
defVariable: tipo listaIds ';'	{ $$ = new DefVariable(lexico.getLinea(), lexico.getColumna(),(Tipo) $1, (List<String>)$2);}
			;

defFuncion: VOID ID '(' param ')' '{' listaCampos listaSentencias '}'		{ $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), new TipoVoid(lexico.getLinea(), lexico.getColumna()),(String)$2,(List<DefVariable>)$4,(List<DefVariable>)$7, (List<Sentencia>)$8);}
			| tipo ID '(' param ')' '{' listaCampos listaSentencias '}'		{ $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(),(Tipo)$1,(String)$2,(List<DefVariable>)$4,(List<DefVariable>)$7, (List<Sentencia>)$8);}
			;

tipo: INT						{ $$ = TipoEntero.getInstance(lexico.getLinea(), lexico.getColumna()); }
	| DOUBLE					{ $$ = TipoDouble.getInstance(lexico.getLinea(), lexico.getColumna()); }	
	| CHAR						{ $$ = TipoCaracter.getInstance(lexico.getLinea(), lexico.getColumna()); }	
	| tipo '[' CTE_ENTERA ']'	{ $$ = new TipoArray(lexico.getLinea(), lexico.getColumna(),(Tipo)$1,(int)$3);}
	;
	
listaExpresiones: expresion	 { List<Expresion> e= new ArrayList<Expresion>(); e.add((Expresion)$1); $$ = e;  }	
				| listaExpresiones ',' expresion	 { List<Expresion> e = (List<Expresion>)$1;e.add((Expresion)$3); $$ = e; }
				;

expresion: '(' expresion ')'				{ $$ = $2; }
	| CTE_ENTERA							{ $$ = new LiteralEntero(lexico.getLinea(), lexico.getColumna(),(int)$1); }
	| CTE_CARACTER							{ String s=(String)$1; char c=s.charAt(0); $$ = new LiteralCaracter(lexico.getLinea(), lexico.getColumna(), c); }
	| CTE_REAL								{ $$ = new LiteralReal(lexico.getLinea(), lexico.getColumna(),(double)$1); }
	| ID									{ $$ = new Variable(lexico.getLinea(), lexico.getColumna(),(String)$1) ;}
	| '(' tipo ')' expresion				{ $$ = new Cast(lexico.getLinea(), lexico.getColumna(),(Tipo)$2, (Expresion)$4);}
	| expresion '[' expresion ']'			{ $$ = new AccesoArray(lexico.getLinea(), lexico.getColumna(),(Expresion)$1,(Expresion)$3);}
	| expresion '.' ID 						{ $$ = new AccesoCampo(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, (String)$3); }
	| ID '(' argumentos ')' 				{ $$ = new LlamadaFuncion(lexico.getLinea(), lexico.getColumna(),(String)$1,(List<Expresion>)$3);}					
	| operacion							
	;

operacion: expresion '+' expresion		{ $$ = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "+", (Expresion)$3); }
       | expresion '*' expresion		{ $$ = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "*", (Expresion)$3); }
       | expresion '-' expresion		{ $$ = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "-", (Expresion)$3); }
       | expresion '/' expresion		{ $$ = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "/", (Expresion)$3); }
       | expresion '%' expresion		{ $$ = new OperacionAritmetica(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "%", (Expresion)$3); }
       | expresion '>' expresion		{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, ">", (Expresion)$3); }
       | expresion MAYORIGUAL expresion{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, ">=", (Expresion)$3); }
       | expresion IGUAL expresion		{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "==", (Expresion)$3); }
       | expresion DISTINTO expresion	{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "!=", (Expresion)$3); }
       | expresion '<' expresion		{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "<", (Expresion)$3); }
       | expresion MENORIGUAL expresion{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "<=", (Expresion)$3); }
       | expresion AND expresion		{ $$ = new OperacionLogica(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "&&", (Expresion)$3); }
       | expresion OR expresion			{ $$ = new OperacionLogica(lexico.getLinea(), lexico.getColumna(),(Expresion)$1, "||", (Expresion)$3); }
       | '!' expresion					{ $$ = new Negacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$2); }
       | '-' expresion %prec MENOSUNARIO{ $$ = new MenosUnario(lexico.getLinea(), lexico.getColumna(),(Expresion)$2) ;}
		;

argumentos: 				{ $$ = new ArrayList<Expresion>();}
		| listaExpresiones	{ $$ = $1;}
		;

listaSentencias: listaSentencias sentencia	{List<Sentencia> s = (List<Sentencia>)$1; s.add((Sentencia)$2); $$ = s ;}
				|							{ $$ = new ArrayList<Sentencia>();}
				;
				
sentencia:	WRITE listaExpresiones ';'		{ $$ = new Escritura(lexico.getLinea(), lexico.getColumna(),(List<Expresion>)$2);}
		 |	READ listaExpresiones ';'		{ $$ = new Lectura(lexico.getLinea(), lexico.getColumna(),(List<Expresion>)$2);}
		 |	WHILE '(' expresion ')' cuerpo	{ $$ = new While(lexico.getLinea(), lexico.getColumna(),(Expresion)$3,(List<Sentencia>) $5);}
		 |	IF '(' expresion ')' cuerpo		{ $$ = new If(lexico.getLinea(), lexico.getColumna(),(Expresion)$3,(List<Sentencia>) $5);}
		 |	IF '(' expresion ')' cuerpo	ELSE cuerpo		{ $$ = new IfElse(lexico.getLinea(), lexico.getColumna(),(Expresion)$3,(List<Sentencia>) $5,(List<Sentencia>) $7);}
		 |  RETURN expresion ';'			{ $$ = new Return(lexico.getLinea(), lexico.getColumna(),(Expresion)$2); }
		 |	expresion '=' expresion	';'		{ $$ = new Asignacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1,(Expresion)$3);}
		 | ID '(' argumentos ')' 	';'		{ $$ = new LlamadaProcedimiento(lexico.getLinea(), lexico.getColumna(),(String)$1,(List<Expresion>)$3);}					
		;
	
cuerpo: '{'listaSentencias'}'		{$$ = $2;}
		| sentencia			{List<Sentencia> s = new ArrayList<Sentencia>(); s.add((Sentencia)$1); $$ = s ;}
		;

param: parametros			{ $$ = $1;}
		|					{ $$ = new ArrayList<DefVariable>();}
		;

parametros:	parametros ',' parametro	{ List<DefVariable> df = (List<DefVariable>)$1; df.add((DefVariable)$3); $$ = df ;}
		| parametro						{ List<DefVariable> dv = new ArrayList<DefVariable>(); dv.add((DefVariable)$1); $$=dv; }
		;
		
parametro: tipo ID					{ List<String> s = new ArrayList<String>(); 
									s.add((String)$2); 
									DefVariable v = new DefVariable(lexico.getLinea(), lexico.getColumna(),(Tipo) $1, s); 
									$$ = v;	}
		;

listaIds: listaIds ',' ID		{List<String> st = (List<String>)$1; st.add((String)$3); $$ = st ;}
		| ID					{List<String> s=new ArrayList<String>(); s.add((String)$1); $$ = s;}
		;

		%%

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
