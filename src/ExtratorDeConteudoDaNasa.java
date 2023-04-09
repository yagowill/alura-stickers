import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa {
    public List<Conteudo> extraiConteudos(String json) {
        // extrair só os dados que interessam (titulo, imagem do satelite)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream()
            .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("url")))
            .toList();
    }
}
