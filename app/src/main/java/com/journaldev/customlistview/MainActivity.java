package com.journaldev.customlistview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<DataModel> dataModels = new ArrayList<>();
    ListView listView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MyTask().execute();
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... strings) {

            listView = (ListView) findViewById(R.id.list);

            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.muslimpro.com/ar/find?country_code=MA&country_name=Morocco&city_name=Casablanca&coordinates=33.5731104,-7.589843399999999").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements links = null;
            if (doc != null) {
                links = doc.select(".prayer-timetable");
            }
            dataModels.add(new DataModel("اليوم", "الفجر", "الشروق", "الظهر", "العصر", "المغرب", "العشاء"));

            if (links != null) {
                for (Element link : links) {
                    Elements rows = link.select("tr");
                    for (Element row : rows) {
                        Elements columns = row.select(".prayertime");
                        Elements columns_data = row.select(".prayertime-1");
                        int i = 0;
                        String date = columns_data.text();
                        String fajr = null;
                        String sunrise = null;
                        String dhuhr = null;
                        String asr = null;
                        String maghrib = null;
                        String isha = null;

                        for (Element column : columns) {
                            if (i == 0) {
                                fajr = column.text();
                            }
                            if (i == 1) {
                                sunrise = column.text();
                            }
                            if (i == 2) {
                                dhuhr = column.text();
                            }
                            if (i == 3) {
                                asr = column.text();
                            }
                            if (i == 4) {
                                maghrib = column.text();
                            }
                            if (i == 5) {
                                isha = column.text();
                            }
                            i++;
                        }
                        if (date != null && !date.trim().isEmpty()) {
                            dataModels.add(new DataModel(date, fajr, sunrise, dhuhr, asr, maghrib, isha));
                        }

                    }
                }
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            adapter = new CustomAdapter(dataModels, getApplicationContext());
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
