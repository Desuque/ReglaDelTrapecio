public class main {
	static double pi = 3.141592654;
	static double b = pi/2;
	static double a = 0;
	
	public static void main(String[] args) {		
		calcularT();
	}
	
	private static void calcularT(){
		int n=1;
		double resultadoObtenido = 0.0;
		
		//Calculando la integral con WolframAlpha se obtiene 2.3081
		//Multiplico por 4*raiz(L/g)
		double resultadoEsperado = 2.9476; // 3 digitos significativos
		double constante = 4*(Math.sqrt(1/9.81));
		boolean resultadoValido = false;
		
		while (!resultadoValido){
			resultadoObtenido = (constante * reglaDelTrapecio(n));
			System.out.println("El resultado es " + resultadoObtenido + " con n: " + n );
			resultadoValido = calcularError(resultadoEsperado, resultadoObtenido);
			n++;
		}
	}
	
	private static boolean calcularError(double resultadoEsperado, double resultadoObtenido){
		double diferencia = resultadoEsperado-resultadoObtenido;
		double errorMaximo = 0.0001;
		if (Math.abs(diferencia) <= errorMaximo) {
			return true;
		}
		return false;
	}
	
	private static double reglaDelTrapecio(int n){
		double deltaX = (b-a)/n;
		double pasoActual = a;
		double resultado = 0;
		
		while(pasoActual<b){
			if (pasoActual == a){
				resultado += evaluarFuncion(pasoActual);
			}
			else {
				resultado += 2 * evaluarFuncion(pasoActual);
			}
			pasoActual = pasoActual + deltaX;
		}
		resultado += evaluarFuncion(n); 
		return (((b-a)/(2*n)) * resultado);	
	}
 
	public static double evaluarFuncion(double pasoActual){
		double A = pi*0.722; //((700 + 2*11)/1000)
		double funcion = Math.pow(1 - (Math.pow((Math.sin(A/2)),2) * (Math.pow((Math.sin(pasoActual)),2))), -0.5);
		return funcion;
	}
}
