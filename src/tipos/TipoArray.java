package tipos;

import ast.AbstractAst;

public class TipoArray extends AbstractAst implements Tipo {

	public Tipo contenido;
	public int tama�o;

	public TipoArray(int linea, int columna, Tipo contenido, int tama�o) {
		super(linea, columna);
		this.contenido = contenido;
		this.tama�o = tama�o;
	}

	@Override
	public String toString() {
		return "TipoArray [contenido=" + contenido + ", tama�o=" + tama�o + "]";
	}

}
