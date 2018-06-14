package cl.ubiobio.ignacio.sensoresubb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Temperatura> TemperaturaDeSensor;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TemperaturaDeSensor =new ArrayList<>();
        serviceTemperaturaUbb();

        listView =(ListView) findViewById(R.id.listView);

        ArrayAdapter<Temperatura> adapter =new ArrayAdapter<Temperatura>(this,android.R.layout.simple_list_item_1,TemperaturaDeSensor );

        listView.setAdapter(adapter);
    }

    private void generateToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }



    private void serviceTemperaturaUbb(){
        Log.d("LOG WS", "entre");
        String WS_URL = "http://arrau.chillan.ubiobio.cl:8075/ubbiot/web/mediciones/medicionespordia/4OdmJnRUuH/E1yGxKAcrg/07042018";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(
                Request.Method.GET,
                WS_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJs = new JSONObject(response);
                            JSONArray responseJson = responseJs.getJSONArray("data");

                            for(int i = 0; i < responseJson.length(); i++){
                                JSONObject o = responseJson.getJSONObject(i);
                                Temperatura temp = new Temperatura();

                                temp.setFecha(o.getString("fecha"));
                                temp.setHora(o.getString("hora"));
                                temp.setValor(o.getString("valor"));

                                TemperaturaDeSensor.add(temp);
                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG WS", error.toString());
                generateToast("Error en el WEB Service jooorgeee");
            }
        }
        );
        requestQueue.add(request);
    }


}

