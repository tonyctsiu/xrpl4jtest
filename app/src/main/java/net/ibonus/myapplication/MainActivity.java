package net.ibonus.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xrpl.xrpl4j.client.XrplClient;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoRequestParams;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoResult;
import org.xrpl.xrpl4j.model.client.common.LedgerSpecifier;
import org.xrpl.xrpl4j.model.transactions.Address;

import okhttp3.HttpUrl;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "XrpTestActivity";
    Button btnGetTicket;
    TextView txtDebug;
    XrplClient xrplClient;
    public static final String familySeed = "sEdTymjSL7sGoU9Kc4tZg74M7ch1bCS";
    public static final String testNetUrl = "https://s.altnet.rippletest.net:51234/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetTicket = findViewById(R.id.btnGetTicket);
        txtDebug = findViewById(R.id.txtDebug);

        HttpUrl httpUrl = HttpUrl.parse(testNetUrl);
        xrplClient = new XrplClient(httpUrl);

        btnGetTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String rAddress = XrpUtil.familySeedToRAddress(familySeed);

                AccountInfoResult accountInfoResult = getValidatedAccountInfo(Address.of(rAddress));

            }
        });
    }


    public AccountInfoResult getValidatedAccountInfo(Address classicAddress) {
        try {
            AccountInfoRequestParams params = AccountInfoRequestParams.builder()
                    .account(classicAddress)
                    .ledgerSpecifier(LedgerSpecifier.VALIDATED)
                    .build();
            return xrplClient.accountInfo(params);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}