package tipos;

import ast.AbstractAst;

public class TipoArray extends AbstractAst implements Tipo {

	public Tipo contenido;
	public int tamaño;

	public TipoArray(int linea, int columna, Tipo contenido, int tamaño) {
		super(linea, columna);
		this.contenido = contenido;
		this.tamaño = tamaño;
	}

	@Override
	public String toString() {
		return "TipoArray [contenido=" + contenido + ", tamaño=" + tamaño + "]";
	}

}
