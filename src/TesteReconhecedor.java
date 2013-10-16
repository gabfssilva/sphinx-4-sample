public class TesteReconhecedor {
	public static void main(String[] args) {
		Reconhecedor reconhecedor = new Reconhecedor();

		String result = reconhecedor.listenToIt();

		System.out.println(result);
	}
}