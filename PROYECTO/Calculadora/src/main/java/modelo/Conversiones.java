package modelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import com.google.gson.*;

public class Conversiones {

    private static HashMap longitud = new HashMap<>();
    private static HashMap masa = new HashMap<>();
    private static HashMap area = new HashMap<>();
    private static  HashMap volumen = new HashMap<>();
    private static HashMap<String,Double> unidades = new HashMap<String,Double>();
    private static final String API_URL = "https://api.frankfurter.app/latest?from=%s&to=%s";

    public static HashMap obtenerHash(char a){
        llenarLogitud();
        llenarMasa();
        llenarArea();
        llenarVolumen();
        if (a == 'l'){
            return longitud;
        }
        if (a == 'm'){
            return masa;
        }
        if(a=='a'){
            return area;
        }
        if(a=='v'){
            return volumen;
        }
        return null;

    }

    private static void llenarLogitud(){
        longitud.put(1,"km");
        longitud.put(2,"hm");
        longitud.put(3,"dam");
        longitud.put(4,"m");
        longitud.put(5,"dm");
        longitud.put(6,"cm");
        longitud.put(7,"mm");
        longitud.put(8,"µm");
        longitud.put(9,"nm");
    }
    private static void llenarMasa(){
        masa.put(1,"kg");
        masa.put(2,"hg");
        masa.put(3,"dag");
        masa.put(4,"g");
        masa.put(5,"dg");
        masa.put(6,"cg");
        masa.put(7,"mg");
        masa.put(8,"µg");
        masa.put(9,"ng");
    }
    private static void llenarArea(){
        area.put(1,"km^2");
        area.put(2,"hm^2");
        area.put(3,"dam^2");
        area.put(4,"m^2");
        area.put(5,"dm^2");
        area.put(6,"cm^2");
        area.put(7,"mm^2");
        area.put(8,"µm^2");
        area.put(9,"nm^2");
    }
    private static void llenarVolumen(){
        volumen.put(1,"km^3");
        volumen.put(2,"hm^3");
        volumen.put(3,"dam^3");
        volumen.put(4,"m^3");
        volumen.put(5,"dm^3");
        volumen.put(6,"cm^3");
        volumen.put(7,"mm^3");
        volumen.put(8,"µm^3");
        volumen.put(9,"nm^3");
    }
    public static void llenarUnidades(){
        unidades.put("k",1000.00);
        unidades.put("h",100.00);
        unidades.put("da",10.00);
        unidades.put("u",1.00);
        unidades.put("d",0.10);
        unidades.put("c",0.01);
        unidades.put("m",0.001);
        unidades.put("µ",0.0001);
        unidades.put("n",0.00001);
    }
    public static double hacerConversion(double valor,String u1, String u2){
        if(u1.isEmpty()){
            u1 = "u";
        }
        if (u2.isEmpty()){
            u2 = "u";
        }
        Double valor1 = unidades.get(u1);
        Double valor2 = unidades.get(u2);
        Double sol = valor*valor1/valor2;
        return sol;

    }
    public static double hacerConversion2(double valor,String u1, String u2,double pot){
        if(u1.isEmpty()){
            u1 = "u";
        }
        if (u2.isEmpty()){
            u2 = "u";
        }
        Double valor1 = unidades.get(u1);
        Double valor2 = unidades.get(u2);
        Double sol = valor*Math.pow(valor1,pot)/Math.pow(valor2,pot);
        return sol;

    }
    public static double cambiarMonedas(double valor, String coin1, String coin2 ){
        double ratio = cambioMoneda(coin1,coin2);
        return valor*ratio;
    }
    public static double cambioMoneda(String fromCurrency, String toCurrency) {
        try {
            URL url = new URL(String.format(API_URL, fromCurrency, toCurrency));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println(response);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

            JsonObject ratesObject = jsonObject.getAsJsonObject("rates");
            double conversion = ratesObject.get(toCurrency).getAsDouble();
            return conversion;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
