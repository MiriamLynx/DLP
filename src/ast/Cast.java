package ast;

import tipos.Tipo;

public class Cast extends AbstractAst implements Expresion {

	public Tipo tipo;
	public Expresion casteo;

	public Cast(int linea, int columna, Tipo tipo, Expresion casteo) {
		super(linea, columna);
		this.tipo = tipo;
		this.casteo = casteo;
	}

	@Override
	public String toString() {
		return "Cast [tipo=" + tipo + ", casteo=" + casteo + "]";
	}

}
