package textanalystics.gova.service;

import com.azure.ai.textanalytics.models.*;
import com.azure.ai.textanalytics.TextAnalyticsClient;
import org.springframework.stereotype.Service;

import textanalystics.gova.model.Documento;
import textanalystics.gova.model.Entidades;
import textanalystics.govaconfig.TextAnalyticsConfig;

import java.util.ArrayList;
import java.util.List;

@Service
public class TextAnalyticsService extends TextAnalyticsConfig {

    TextAnalyticsClient client = this.authenticateClient();

    public String analizarSentimiento(Documento documento){
        DocumentSentiment documentSentiment = client.analyzeSentiment(documento.getTexto(), "es");
        return documentSentiment.getSentiment().toString();
    }

    public String detectarLenguaje(Documento documento) {
        DetectedLanguage detectedLanguage = client.detectLanguage(documento.getTexto());
        return detectedLanguage.getName();
    }

    public List<Entidades> reconocerEntidades(Documento documento){

        CategorizedEntityCollection result = client.recognizeEntities(documento.getTexto(), "es");
        List<Entidades> entidadesList = new ArrayList<>();

        for (CategorizedEntity entity : result) {
            Entidades entidades = new Entidades();
            entidades.setEntidad(entity.getText());
            entidades.setCategoria(entity.getCategory().toString());
            entidadesList.add(entidades);
        }
        return entidadesList;
    }

    public List<String> detectarPalabrasClaves(Documento documento){

        List<String> palabras = new ArrayList<>();
        KeyPhrasesCollection result = client.extractKeyPhrases(documento.getTexto(), "es");
        for (String keyPhrase : result) {
            palabras.add(keyPhrase);
        }
        return palabras;
    }
}
