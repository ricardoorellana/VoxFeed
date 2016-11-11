package com.ricardoorellana.welcometovoxfeed.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricardoorellana.welcometovoxfeed.R;
import com.ricardoorellana.welcometovoxfeed.beans.Publication;
import com.ricardoorellana.welcometovoxfeed.utils.CircleTransform;
import com.ricardoorellana.welcometovoxfeed.utils.StringFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * PublicationListAdapter
 *
 * Extends from ArrayAdapter to create custom listview items
 *
 * @author rorellanam
 * @version 1.0
 * @since 1.0
 */
public class PublicationsListAdapter extends ArrayAdapter <Publication> {

    /**
     * References of text view to set text content in list item
     */
    private TextView mTextView;

    /**
     * References of image view to set images in list items
     */
    private ImageView mImageView;

    /**
     *
     * @param context
     * @param publications
     */
    public PublicationsListAdapter(Context context, List<Publication> publications) {
        super(context, 0, publications);
    }

    /**
     * Sets data for each list item
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the  object from the ArrayAdapter at the appropriate position

        Publication publication = getItem(position);

        View listItem = convertView;

        // Adapters recycle views to list views.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.adapter_list_item_publications, parent, false);
        }

        mImageView = (ImageView) listItem.findViewById(R.id.profile_image);
        Picasso.with(getContext()).load(publication.getUser().getUserProfileImage()).transform(new CircleTransform()).into(mImageView);

        mTextView = (TextView) listItem.findViewById(R.id.username);
        mTextView.setText(publication.getUser().getUserName());

        mTextView = (TextView) listItem.findViewById(R.id.socialNetwork);

        switch (publication.getSocialNetwork()) {
            case "facebook":
                mTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.mariner));
                break;
            case "twitter":
                mTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.deep_sky_blue));
                break;
            case "instagram":
                mTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.radical_red));
                break;
        }

        String socialNetwork = publication.getSocialNetwork();
        socialNetwork = socialNetwork.substring(0,1).toUpperCase() + socialNetwork.substring(1);
        mTextView.setText(socialNetwork);

        mTextView = (TextView) listItem.findViewById(R.id.date);
        mTextView.setText(StringFormatter.formatDate(publication.getDate()));

        mImageView = (ImageView) listItem.findViewById(R.id.post_image);
        Picasso.with(getContext()).load(publication.getPost().getImage()).resize(700, mImageView.getHeight()).into(mImageView);

        mTextView = (TextView) listItem.findViewById(R.id.post_text);
        mTextView.setText(publication.getPost().getText());


        return listItem;
    }

}
