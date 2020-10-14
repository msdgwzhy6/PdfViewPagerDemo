package com.zhongjh.pdfviewpagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DownloadFile.Listener {

    PDFPagerAdapter adapter;
    RemotePDFViewPager remotePDFViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://www.chinalife.com.hk/sites/default/files/2020-06/C360_201912_Ultimate-Fortune-Wealth-Planner-Supreme_EN.pdf";
        remotePDFViewPager = new RemotePDFViewPager(MainActivity.this, url, this);
        remotePDFViewPager.setId(R.id.pdfViewPager);
    }


    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);
        setContentView(remotePDFViewPager);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null)
            adapter.close();
    }

}