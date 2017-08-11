package request;

public class TransferenciaRequest {

		private Double valor;
		private String contaOrigem;
		private String contaDestino;
		
		
		public String getContaOrigem() {
			return contaOrigem;
		}
		
		public void setContaOrigem(String contaOrigem){
			this.contaOrigem = contaOrigem;
		}

		public String getContaDestino(){
			return contaDestino;
		}
		
		public void setContaDestino (String contaDestino){
			this.contaDestino = contaDestino;
		}
		
		public double getValor(){
			return valor;
		}
		
		public void setValor(double valor){
			this.valor = valor;
		}
		
		
}
