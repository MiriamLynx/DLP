package tipos;

import ast.AbstractAst;

public class TipoDouble extends AbstractAst implements Tipo {

	private static TipoDouble instance;

	private TipoDouble(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoDouble getInstance(int linea, int columna) {
		if (instance == null){
			instance = new TipoDouble(linea, columna);
		}
		return instance;
	}

}
