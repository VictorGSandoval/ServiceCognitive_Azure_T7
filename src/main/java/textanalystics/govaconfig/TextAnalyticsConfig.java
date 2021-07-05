package textanalystics.govaconfig;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.TextAnalyticsClient;

public class TextAnalyticsConfig {

    private static String KEY = "e7063f1efcdd4c569b13bd2c10dd9b85";
    private static String ENDPOINT = "https://textcognitive2021.cognitiveservices.azure.com/";

    public TextAnalyticsClient authenticateClient() {
        return new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(KEY))
                .endpoint(ENDPOINT)
                .buildClient();
    }
}
