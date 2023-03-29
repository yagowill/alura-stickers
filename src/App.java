import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.lang.Integer;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os tops 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body); 
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[37m\u001b[45mAvaliação: " + filme.get("imDbRating") + "\u001b[m");
            Float classificacao = Float.parseFloat(filme.get("imDbRating"));
            int numEstrelas = Math.round(classificacao);
            for (int n = 1; n <= numEstrelas ; n++ ) {
                System.out.print("\u2B50");
            }
            System.out.println();
            System.out.println("Title: " + "\u001b[1m" + filme.get("title") + "\u001b[m");
            System.out.println("Poster: " +"\u001b[1m" + filme.get("image") + "\u001b[m");
            System.out.println();
        }
    }
}
