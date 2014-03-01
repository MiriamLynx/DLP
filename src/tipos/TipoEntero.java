package tipos;

import ast.AbstractAst;

public class TipoEntero extends AbstractAst implements Tipo {

	private static TipoEntero instance;

	private TipoEntero(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoEntero getInstance(int linea, int columna) {
		if (instance == null){
			instance = new TipoEntero(linea, columna);
		}
		return instance;
	}
}
