package avalanche.sasquatch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.UUID;

import avalanche.analytics.Analytics;
import avalanche.base.Avalanche;
import avalanche.base.utils.AvalancheLog;
import avalanche.crash.Crashes;
import avalanche.sasquatch.R;
import avalanche.sasquatch.features.TestFeatures;
import avalanche.sasquatch.features.TestFeaturesListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Avalanche.useFeatures(getApplication(), UUID.randomUUID().toString(), Analytics.class, Crashes.class);

        AvalancheLog.setLogLevel(Log.VERBOSE);

        boolean crashManagerAvailable = Avalanche.isFeatureAvailable(Crashes.class.getName());

        AvalancheLog.info("crash available: " + crashManagerAvailable);

        boolean crashManagerEnabled = Avalanche.isFeatureEnabled(Crashes.class.getName());

        AvalancheLog.info("crash enabled: " + crashManagerEnabled);

        TestFeatures.initialize(this);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new TestFeaturesListAdapter(TestFeatures.getAvailableControls()));
        listView.setOnItemClickListener(TestFeatures.getOnItemClickListener());
    }
}
