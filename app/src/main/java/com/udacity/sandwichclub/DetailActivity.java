package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich mSandwich;

    //Initial views
    @BindView(R.id.origin_tv) TextView tv_origin;
    @BindView(R.id.also_known_tv) TextView tv_otherNames;
    @BindView(R.id.ingredients_tv) TextView tv_ingredents;
    @BindView(R.id.description_tv) TextView tv_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        mSandwich = JsonUtils.parseSandwichJson(json);
        if (mSandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(mSandwich.getImage())
                .placeholder(R.mipmap.ic_default_launcher)
                .error(R.mipmap.ic_default_launcher)
                .into(ingredientsIv);

        setTitle(mSandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        //Build string from array
        String otherNames = TextUtils.join(", ", mSandwich.getAlsoKnownAs());
        String ingredents = TextUtils.join(", ", mSandwich.getIngredients());

        //Populate Views
        tv_origin.setText(mSandwich.getPlaceOfOrigin());
        tv_otherNames.setText(otherNames);
        tv_ingredents.setText(ingredents);
        tv_description.setText(mSandwich.getDescription());
    }
}
