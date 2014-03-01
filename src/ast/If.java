package ast;

import java.util.List;

public class If extends AbstractAst implements Sentencia {

	public Expresion expresion;
	public List<Sentencia> sentencias;

	public If(int linea, int columna, Expresion expresion,
			List<Sentencia> sentencias) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "If [expresion=" + expresion + ", sentencias=" + sentencias
				+ "]";
	}

}
