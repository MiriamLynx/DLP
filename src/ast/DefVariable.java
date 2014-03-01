package ast;

import java.util.List;

import tipos.Tipo;

public class DefVariable extends AbstractAst implements Definicion {

	
	public List<String> variables;
	public Tipo tipo;

	public DefVariable(int linea, int columna, List<String> variables, Tipo tipo){
		super(linea,columna);
		this.variables = variables;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "DefVariable [variables=" + variables + ", tipo=" + tipo + "]";
	}

}
