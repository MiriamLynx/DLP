package ast;

public class MenosUnario extends AbstractAst implements Expresion {

	public Expresion operando;
	public String operador;
	
	public MenosUnario(int linea, int columna, Expresion operando) {
		super(linea,columna);
		this.operador = "-";
		this.operando = operando;
		
	}

	@Override
	public String toString() {
		return "MenosUnario [operando=" + operando + ", operador=" + operador
				+ "]";
	}



}
