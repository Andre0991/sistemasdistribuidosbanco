package request;

public class Transferencia {

		private Double valor;
		private String contaOrigem;
		private String contaDestino;
		
		
		public String getContaOrigem() {
			return contaOrigem;
		}
		
		public void setContaOrigem(String conta1){
			this.contaOrigem = conta1;
		}

		public String getContaDestino(){
			return contaDestino;
		}
		
		public void setContaDestino (String conta2){
			this.contaDestino = conta2;
		}
		
		public double getValor(){
			return valor;
		}
		
		public void setValor(double valor){
			this.valor = valor;
		}
		
		
}
