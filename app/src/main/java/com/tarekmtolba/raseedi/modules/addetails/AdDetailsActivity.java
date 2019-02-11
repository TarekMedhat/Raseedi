package com.tarekmtolba.raseedi.modules.addetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tarekmtolba.raseedi.R;
import com.tarekmtolba.raseedi.data.model.Ad;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tarekmtolba.raseedi.modules.ad.AdsActivity.CLICKED_AD_KEY;

public class AdDetailsActivity extends AppCompatActivity {

    @BindView(R.id.ad_details_imageview)
    ImageView adDetailsImageview;
    @BindView(R.id.ad_details_textview)
    TextView adDetailsTextview;
    private Ad mAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_details);
        ButterKnife.bind(this);
        mAd = getIntent().getParcelableExtra(CLICKED_AD_KEY);
        Picasso.get().load(mAd.getPicture()).placeholder(R.drawable.raseedi).into(adDetailsImageview);
        adDetailsTextview.setText(mAd.getTitle());
    }

    @OnClick(R.id.details_button)
    public void onButtonClicked() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mAd.getUrl()));
        startActivity(intent);
    }
}
