package com.mahmoudsalah.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {
ListView listText;
ArrayList<String> news = new ArrayList<>();
    ArrayList<String> head = new ArrayList<>();
    ArrayList<String> des = new ArrayList<>();
    ArrayList<String> link = new ArrayList<>();
    List<Element> elements,elements1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listText = findViewById(R.id.listText);
        listText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,details.class);
//                if (position ==0){
                    intent.putExtra("title",elements.get(position).getChildText("title"));
                    intent.putExtra("des",elements.get(position).getChildText("description"));
                    intent.putExtra("url",elements.get(position).getChildText("link"));
//                    intent.putExtra("afturl",elements.get(position+1).getChildText("title"));
//                    intent.putExtra("aftdes",elements.get(position+1).getChildText("description"));
//                    intent.putExtra("aftlink",elements.get(position+1).getChildText("link"));
                    startActivity(intent);
//                }
//                else {
//                    intent.putExtra("title", elements.get(position).getChildText("title"));
//                    intent.putExtra("des", elements.get(position).getChildText("description"));
//                    intent.putExtra("url", elements.get(position).getChildText("link"));
//                    intent.putExtra("befurl", elements.get(position - 1).getChildText("title"));
//                    intent.putExtra("befdes",elements.get(position-1).getChildText("description"));
//                    intent.putExtra("beflink",elements.get(position-1).getChildText("link"));
//                    intent.putExtra("afturl", elements.get(position + 1).getChildText("title"));
//                    intent.putExtra("aftdes",elements.get(position+1).getChildText("description"));
//                    intent.putExtra("aftlink",elements.get(position+1).getChildText("link"));
//                    startActivity(intent);
//                }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        RequestQueue queue1 = Volley.newRequestQueue(this);
        String url = "https://arabic.cnn.com/api/v1/rss/rss.xml";
//        String url2="https://www.espn.com/espn/rss/news";
        StringRequest request = new StringRequest(url,this,this);
//        StringRequest request1 = new StringRequest(url2,this,this);
        queue.add(request);
//        queue1.add(request1);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        SAXBuilder builder = new SAXBuilder();
        StringReader reader = new StringReader(response);
        try {
            Document document = builder.build(reader);
            elements = document.getRootElement().getChild("channel").getChildren("item");
            for (Element element : elements) {
                news.add(element.getChildText("title"));
//                news.add(element.getChildText("description"));
//                news.add(element.getChildText("link"));
//                Toast.makeText(this, ""+news, Toast.LENGTH_SHORT).show();
            }

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, news);
            listText.setAdapter(adapter);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void refresh(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://arabic.cnn.com/api/v1/rss/rss.xml";
//        String url2="https://www.aljazeera.net/aljazeerarss/a7c186be-1baa-4bd4-9d80-a84db769f779/73d0e1b4-532f-45ef-b135-bfdff8b8cab9";
        StringRequest request = new StringRequest(url,this,this);
        queue.add(request);
        news.clear();

    }
}
