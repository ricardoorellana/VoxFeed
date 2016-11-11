package com.ricardoorellana.welcometovoxfeed.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricardoorellana.welcometovoxfeed.R;
import com.ricardoorellana.welcometovoxfeed.beans.Publication;
import com.ricardoorellana.welcometovoxfeed.utils.CircleTransform;
import com.ricardoorellana.welcometovoxfeed.utils.StringFormatter;
import com.squareup.picasso.Picasso;

/**
 * Activity for display publication details
 *
 * This activity is used to display publications details.
 *
 * @author rorellanam
 * @version 1.0
 * @since 1.0
 */
public class PublicationDetail extends AppCompatActivity {

    /**
     * Debug Tag for use logging debug output to LogCat
     */
    private static final String LOG_TAG = PublicationDetail.class.getName();

    /**
     * Publication object to get publication data
     */
    private Publication mPublication;

    /**
     * Reference to text view object to display publication information
     */
    private TextView mTextView;


    /**
     * Initializes activity
     * @param savedInstanceState Contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_detail);

        Intent intent = this.getIntent();

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            mPublication = intent.getParcelableExtra(Intent.EXTRA_TEXT);
        }

        loadToolbar();
        refreshUI();

        mTextView = (TextView) findViewById(R.id.see_publications);

        String seePublication = getResources().getString(R.string.see_publications_in).toUpperCase();
        String social = mPublication.getSocialNetwork().toUpperCase();
        mTextView.setText(seePublication + " " + social);

        switch (mPublication.getSocialNetwork()) {
            case "facebook":
                mTextView.setTextColor(ContextCompat.getColor(this, R.color.mariner));
                break;
            case "twitter" :
                mTextView.setTextColor(ContextCompat.getColor(this, R.color.deep_sky_blue));
                break;
            case "instagram":
                mTextView.setTextColor(ContextCompat.getColor(this, R.color.radical_red));
        }


        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mPublication.getPost().getLink();
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(url)));
                }
            }
        });
    }

    /**
     * Callback executed when user clicks the menu buttons
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Initializes UI
     */
    public void refreshUI() {
        ImageView coverImageView = (ImageView) findViewById(R.id.cover_imageView);
        Picasso.with(this)
                .load(mPublication.getCampaign().getCoverImage())
                .into(coverImageView);

        ImageView logoImageView = (ImageView) findViewById(R.id.logo_imageView);
        Picasso.with(this)
                .load(mPublication.getBrand().getLogo())
                .transform(new CircleTransform())
                .into(logoImageView);

        mTextView = (TextView) findViewById(R.id.campaign_name);
        mTextView.setText(mPublication.getCampaign().getName());

        mTextView = (TextView) findViewById(R.id.earnings);
        String earnings = StringFormatter.currencyFormatter(mPublication.getEarning());
        mTextView.setText(earnings + " " + getResources().getString(R.string.usd).toUpperCase());

        mTextView = (TextView) findViewById(R.id.like);
        mTextView.setText(StringFormatter.audienceFormat(mPublication.getStat().getLikes()));

        mTextView = (TextView) findViewById(R.id.click);
        mTextView.setText(StringFormatter.audienceFormat(mPublication.getStat().getClicks()));

        mTextView = (TextView) findViewById(R.id.comments);
        mTextView.setText(StringFormatter.audienceFormat(mPublication.getStat().getComments()));

        mTextView = (TextView) findViewById(R.id.shared);
        mTextView.setText(StringFormatter.audienceFormat(mPublication.getStat().getShares()));

        mTextView = (TextView) findViewById(R.id.scope);
        mTextView.setText(StringFormatter.audienceFormat(mPublication.getStat().getAudience()));

    }

    /**
     * Loads toolbar
     */
    public void loadToolbar () {
        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mPublication.getBrand().getName());
    }

    /**
     * Defines options to loaded in menu
     * @param menu Reference of menu to be inflated
     * @return Boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
