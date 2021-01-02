package com.example.anticovid.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anticovid.Activity.NewsActivity;
import com.example.anticovid.Adapter.NewsAdapter;
import com.example.anticovid.Data.XMLDOMParser;
import com.example.anticovid.Model.News;
import com.example.anticovid.R;
import com.example.anticovid.util.AsynctaskCallBack;
import com.example.anticovid.Data.MyAsynctask;
import com.example.anticovid.util.OnItemClickListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentNews extends Fragment  {
    private static final String LINK_RSS = "https://vnexpress.net/rss/the-gioi.rss";
    View view;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private Boolean isExpanded=true;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private ArrayList<News> arrTitle = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initToolbar();
        initRecyclerview();
        initToolbarAnimation();
        readRss();
        newsAdapter = new NewsAdapter(arrTitle,onItemClickListener);
        recyclerView.setAdapter(newsAdapter);
    }

    private void init() {
        recyclerView = view.findViewById(R.id.recyclerview_news);
        collapsingToolbarLayout= view.findViewById(R.id.collapsingToolbarLayout);
        toolbar=view.findViewById(R.id.toolbar);
        appBarLayout = view.findViewById(R.id.appBarLayoutNew);
    }
    private void initToolbar(){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }
    private void initRecyclerview(){
        recyclerView.setHasFixedSize(true);
    }
    private void initToolbarAnimation(){
        collapsingToolbarLayout.setTitle(getString(R.string.app_name));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img_anticovid);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
              int myColor=palette.getVibrantColor(getResources().getColor(R.color.colorToolbar));
              collapsingToolbarLayout.setContentScrimColor(myColor);
              collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.black_trans));
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if( Math.abs(verticalOffset)> 200){
                    isExpanded=false;
                }
                else {
                    isExpanded=true;
                }
                getActivity().invalidateOptionsMenu();
            }
        });
    }

    private void readRss() {
        new MyAsynctask(new AsynctaskCallBack() {
            @Override
            public void onSuccess(String data) {
                getRssContent(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        }).execute(LINK_RSS);
    }

    private void getRssContent(String data) {
        XMLDOMParser xmldomParser = new XMLDOMParser();
        Document document = xmldomParser.getDocument(data);
        NodeList nodeList = document.getElementsByTagName("item");
        NodeList nodeListDescription = document.getElementsByTagName("description");
        Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher matcher;
        String title = "", timenew = "", cdata="", imgUrl = "",link="";

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            cdata = nodeListDescription.item(i + 1).getTextContent();
            matcher = pattern.matcher(cdata);
            if (matcher.find()) imgUrl = matcher.group(1);
            title = xmldomParser.getValue(element, "title");
            timenew = xmldomParser.getValue(element, "pubDate");
            link = xmldomParser.getValue(element,"link");
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy"); //"dd-MM-yyyy"
            Date date = null;
            try {
                date = inputFormat.parse(timenew);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String formattedDate = outputFormat.format(date);
            if(title.contains("Covid-19")) {
                arrTitle.add(new News(imgUrl, title, formattedDate, link));
            }

        }
        newsAdapter.updateData(arrTitle);
    }
   OnItemClickListener onItemClickListener = new OnItemClickListener() {
       @Override
       public void OnitemClick(News news) {
           Intent intent = new Intent(getActivity(), NewsActivity.class);
           intent.putExtra("link",news.getLink());
           startActivity(intent);
             }
   };



}
