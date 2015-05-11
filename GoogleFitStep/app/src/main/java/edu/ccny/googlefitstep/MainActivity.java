package edu.ccny.googlefitstep;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;

import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity {




    private static String TAG = MainActivity.class.getName().toUpperCase();

    private GoogleApiClient googleApiClient = null;
    private static final int OAUTH_REQUET = 0;
    private OnDataPointListener onDataPointListener;
    private static final String AUTH_PENDING = "auth_state_pending";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){

        }

        buildFitnessCLient();
    }

    private void buildFitnessCLient() {
       googleApiClient = new GoogleApiClient.Builder(this)
               .addApi(Fitness.API)
               .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
               .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                   @Override
                   public void onConnected(Bundle bundle) {

                       findFitnessSource();

                   }

                   @Override
                   public void onConnectionSuspended(int i) {

                   }
               })
               .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                   @Override
                   public void onConnectionFailed(ConnectionResult connectionResult) {

                   }
               })
               .build();
    }

    private void findFitnessSource() {
        Fitness.SensorsApi.findDataSources(googleApiClient, new DataSourcesRequest.Builder()
                .setDataTypes(DataType.TYPE_LOCATION_SAMPLE)
                .setDataSourceTypes(DataSource.TYPE_RAW)
                .build())
                .setResultCallback(new ResultCallback<DataSourcesResult>() {
                    @Override
                    public void onResult(DataSourcesResult dataSourcesResult) {
                        for (DataSource dataSource : dataSourcesResult.getDataSources()){
                            if (dataSource.getDataType().equals(DataType.TYPE_STEP_COUNT_DELTA))){
                                registerFitnessDataListener(dataSource, DataType.TYPE_STEP_COUNT_DELTA);
                            }
                        }
                    }
                });

    }

    private void registerFitnessDataListener(DataSource dataSource, DataType typeStepCountDelta) {
        onDataPointListener = new OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                for (Field field : dataPoint.getDataType().getFields()) {
                    Value val = dataPoint.getValue(field);
                    Log.i(TAG, "Detected DataPoint field: " + field.getName());
                    Log.i(TAG, "Detected DataPoint value: " + val);
                }
            }
        };

        Fitness.SensorsApi.add(
                googleApiClient,
                new SensorRequest.Builder()
                        .setDataSource(dataSource) // Optional but recommended for custom data sets.
                        .setDataType(typeStepCountDelta) // Can't be omitted.
                        .setSamplingRate(10, TimeUnit.SECONDS)
                        .build(),
                googleApiClient)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            Log.i(TAG, "Listener registered!");
                        } else {
                            Log.i(TAG, "Listener not registered.");
                        }
                    }
                });


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
