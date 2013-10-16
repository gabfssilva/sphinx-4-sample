import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class Reconhecedor {
	private ConfigurationManager cm;
	private Recognizer recognizer;
	private Microphone microphone;

	public Reconhecedor() {
		// obtendo as configurações do xml
		cm = new ConfigurationManager(
				Reconhecedor.class.getResource("recognizer.config.xml"));

		recognizer = (Recognizer) cm.lookup("recognizer");
		microphone = (Microphone) cm.lookup("microphone");
		recognizer.allocate();
	}

	public String listenToIt() {
		recognizer.resetMonitors();

		microphone.clear(); // limpando TODOS os dados capturados pelo
							// microfone, p/ n�o ocorrer problemas na gravação

		microphone.startRecording(); // começando a gravar

		System.out.println("Speak!");

		Result result = recognizer.recognize(); // reconhecendo o que o
												// microfone capturou e jogando
												// p/ um objeto tipo Result
		String resultText;

		if (result != null) {
			resultText = result.getBestFinalResultNoFiller(); // obtendo o
																// melhor
																// resultado
			if (resultText.equals("")) {
				System.out
						.println("Sorry, I didn't understand what you said. \n");
				resultText = "Sorry, I didn't understand what you said.";
			} else {
				System.out.println("You said: " + resultText + '\n');
			}
		} else {
			resultText = "Sorry, I didn't understand what you said.";
		}

		microphone.stopRecording(); // Parando a gravação

		return resultText; // retornando resultado
	}
}