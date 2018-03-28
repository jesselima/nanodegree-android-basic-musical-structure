package com.udacity.nanodegree.musicalstructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

/**
 * The code in this activity was provided by Simplify documentation.
 * The API key below is public and it's points to a Sandbox for tests purposes.
 *
 * For more information about the Simplify documentation follow the link below
 * https://www.simplify.com/commerce/docs/sdk/android
 */

public class PaymentActivity extends AppCompatActivity {

    Simplify simplify;

    // Public API Key for use in Sandbox
    final String API_KEY = "sbpb_NGUyMGViOWItZDNmMi00ODdlLTk1ZTMtZTIzYWRmZDE4OTE0";
    CardView cardViewDescriptionLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardViewDescriptionLibrary = findViewById(R.id.card_description_payment);
        cardViewDescriptionLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewDescriptionLibrary.setVisibility(View.GONE);
            }
        });

        simplify = new Simplify();
        simplify.setApiKey(API_KEY);

        /* SAMPLE CODE - START
        * Provided by https://www.simplify.com/commerce/docs/sdk/android
        */

        // init card editor
        final CardEditor cardEditor = findViewById(R.id.card_editor);
        final Button checkoutButton = findViewById(R.id.checkout_button);
        // add state change listener
        cardEditor.addOnStateChangedListener(new CardEditor.OnStateChangedListener() {
            @Override
            public void onStateChange(CardEditor cardEditor) {
                // isValid() == true : card editor contains valid and complete card information
                checkoutButton.setEnabled(cardEditor.isValid());
            }
        });
        // add checkout button click listener
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a card token
                simplify.createCardToken(cardEditor.getCard(), new CardToken.Callback() {
                    @Override
                    public void onSuccess(CardToken cardToken) {
                        Toast.makeText(PaymentActivity.this, R.string.card_ok, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        Toast.makeText(PaymentActivity.this, R.string.card_not_ok, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        // SAMPLE CODE - END

    }
}
