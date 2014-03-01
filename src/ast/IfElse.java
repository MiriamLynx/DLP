package ast;

import java.util.List;

public class IfElse extends AbstractAst implements Sentencia {

	public Expresion expresion;
	public List<Sentencia> sentencias;
	public List<Sentencia> alternativas;

	public IfElse(int linea, int columna, Expresion expresion,
			List<Sentencia> sentencias, List<Sentencia> alternativas) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
		this.alternativas = alternativas;
	}

	@Override
	public String toString() {
		return "IfElse [expresion=" + expresion + ", sentencias=" + sentencias
				+ ", alternativas=" + alternativas + "]";
	}

}
