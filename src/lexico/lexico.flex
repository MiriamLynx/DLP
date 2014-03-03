// ************  Código a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************
// * El analizador sintáctico
private Parser parser;
public void setParser(Parser parser) {
	this.parser=parser;
}

// * Para acceder al número de línea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

%}

// ************  Patrones (macros) ********************
ConstanteEntera = [0-9]+
ConstanteCaracter = "'"({Letra}|\\n|\\{ConstanteEntera})"'"
ConstanteReal = ({ConstanteEntera}+ "."{ConstanteEntera}*)|
				({ConstanteEntera}+ "."{ConstanteEntera}*((E|e)("+"|"-")?{ConstanteEntera}+))?
ConstanteString = \"(.)*\"
Letra = [a-zA-ZñÑ]
Identificador = {Letra}({Letra}|{ConstanteEntera})*
SaltosDeLinea = \r\n|\n|\r
Espacios = [ \t\f]
ComentarioSimple = "//" .* {SaltosDeLinea}
ComentarioVariasLineas = "/*" ~ "*/"
Basura = {SaltosDeLinea}|{Espacios}|{ComentarioVariasLineas}|{ComentarioSimple}

%%
// ************  Acciones ********************

{ConstanteEntera}	{ parser.setYylval(new Integer(yytext())); return Parser.CTE_ENTERA; }

{ConstanteReal}     { parser.setYylval(new Double(yytext())); return Parser.CTE_REAL; }

{ConstanteCaracter}	{ parser.setYylval(yytext()); return Parser.CTE_CARACTER; }

{ConstanteString}   { parser.setYylval(yytext()); return Parser.CTE_STRING; }

"+"|

"-"|

"*"|

"/"|

"="|

"<"|

">"|

"("|

")"|

"{"|

"}"|

";"|

","|

"!"|

"."|

"["|

"]"                { parser.setYylval(yytext()); return (int) yycharat(0); }


"<="			   { parser.setYylval(yytext()); return Parser.MENORIGUAL; }

">="			   { parser.setYylval(yytext()); return Parser.MAYORIGUAL; }

"=="			   { parser.setYylval(yytext()); return Parser.IGUAL; }

"!="			   { parser.setYylval(yytext()); return Parser.DISTINTO; }

 "&&"			   { parser.setYylval(yytext()); return Parser.AND; }
 
 "||"			   { parser.setYylval(yytext()); return Parser.OR; }


read	           { parser.setYylval(yytext()); return Parser.READ; }

if		           { parser.setYylval(yytext()); return Parser.IF; }

else	           { parser.setYylval(yytext()); return Parser.ELSE; }

while	           { parser.setYylval(yytext()); return Parser.WHILE; }

write	           { parser.setYylval(yytext()); return Parser.WRITE; }

main	           { parser.setYylval(yytext()); return Parser.MAIN; }

int 	           { parser.setYylval(yytext()); return Parser.INT; }

double	           { parser.setYylval(yytext()); return Parser.DOUBLE; }

char               { parser.setYylval(yytext()); return Parser.CHAR; }

void	           { parser.setYylval(yytext()); return Parser.VOID; }

struct	           { parser.setYylval(yytext()); return Parser.STRUCT; }

return	           { parser.setYylval(yytext()); return Parser.RETURN; }

{Identificador}    { parser.setYylval(yytext()); return Parser.ID; }

{Basura}           {}

.					{System.err.println("Error léxico en la linea: "+ this.getLinea() + ", columna: "+ this.getColumna() +"\nCaracter: '"+yycharat(0)+"' no reconocido");}
