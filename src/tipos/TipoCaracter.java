package tipos;

import ast.AbstractAst;

public class TipoCaracter extends AbstractAst implements Tipo {

	private static TipoCaracter instance;

	private TipoCaracter(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoCaracter getInstance(int linea, int columna) {
		if (instance == null){
			instance = new TipoCaracter(linea, columna);
		}
		return instance;
	}

}
