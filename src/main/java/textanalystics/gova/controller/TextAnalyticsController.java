package textanalystics.gova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import textanalystics.gova.model.Documento;
import textanalystics.gova.service.TextAnalyticsService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class TextAnalyticsController {

    @Autowired
    private TextAnalyticsService textAnalyticsService;

    @PostMapping("/sentimiento")
    public ResponseEntity<?> Analizardocumento(@RequestBody() Documento documento) {
        Map<String, String> response= new HashMap<>();
        response.put("Texto", documento.getTexto());
        response.put("Tipo", textAnalyticsService.analizarSentimiento(documento));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/detectar-lenguaje")
    public ResponseEntity<?> detectarLenguaje(@RequestBody() Documento documento) {
        Map<String, String> response = new HashMap<>();
        response.put("Texto", documento.getTexto());
        response.put("Lenguaje", textAnalyticsService.detectarLenguaje(documento));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/reconocer-entidades")
    public ResponseEntity<?> reconocerEntidades(@RequestBody() Documento documento) {
        Map<String, Object> response= new HashMap<>();
        response.put("Texto", documento.getTexto());
        response.put("Entidades", textAnalyticsService.reconocerEntidades(documento));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/palabras-claves")
    public ResponseEntity<?> detectarPalabrasClaves(@RequestBody() Documento documento) {
        Map<String, Object> response = new HashMap<>();
        response.put("Texto", documento.getTexto());
        response.put("Palabras", textAnalyticsService.detectarPalabrasClaves(documento));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
