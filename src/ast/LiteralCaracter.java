package ast;

public class LiteralCaracter extends AbstractAst implements Expresion {
	
	public char valor;
	
	public LiteralCaracter(int linea, int columna, char valor){
		super(linea,columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralCaracter [valor=" + valor + "]";
	}

}
