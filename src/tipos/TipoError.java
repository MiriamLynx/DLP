package tipos;

import ast.AbstractAst;
import ast.NodoAST;

public class TipoError extends AbstractAst implements Tipo {

	public NodoAST error;

	public TipoError(int linea, int columna, NodoAST error) {
		super(linea, columna);
		this.error = error;
	}

}
