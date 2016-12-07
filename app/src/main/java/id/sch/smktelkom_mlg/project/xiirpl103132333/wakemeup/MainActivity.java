package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.adapter.AlarmAdapter;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common.AlarmService;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.Alarm;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.dbAlarm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AlarmAdapter.IalarmAdapter {


    public final static int EDIT = 213;
    public static final String LEVEL = "level";
    static Context ctx;
    Intent mServiceIntent;
    ArrayList<Alarm> mAlarmList = new ArrayList<>();
    AlarmAdapter mAdapter;
    dbAlarm db;
    private AlarmService mAlarmService;

    public static Context getCtx() {
        return ctx;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new dbAlarm();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AlarmAdapter(this, mAlarmList);
        recyclerView.setAdapter(mAdapter);
        refresh();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<dbAlarm> dt = dbAlarm.getAll();
                Intent intent = new Intent(MainActivity.this, tambahActivity.class);
                if (dt.size() > 0) {
                    intent.putExtra("id", mAlarmList.get(mAlarmList.size() - 1).id);
                    Toast.makeText(MainActivity.getCtx(), "" + String.valueOf(dbAlarm.getMaxID()), Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("id", 0);
                    Toast.makeText(MainActivity.getCtx(), "0", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ctx = this;
        mAlarmService = new AlarmService(getCtx());
        mServiceIntent = new Intent(getCtx(), mAlarmService.getClass());
        if (!isMyServiceRunning(mAlarmService.getClass())) {
            startService(mServiceIntent);
        }

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void refresh() {
        Log.i("start", "refresh");
        List<dbAlarm> dbData = dbAlarm.getAll();

        mAlarmList.clear();

        for (int i = 0; i < dbData.size(); i++) {
            mAlarmList.add(new Alarm(dbData.get(i).getId(), dbData.get(i).hours, dbData.get(i).days,
                    dbData.get(i).ringtone, dbData.get(i).method, dbData.get(i).level, dbData.get(i).memo, dbData.get(i).isEnabled));
            Log.i("a", "refresh: " + i);
        }

        mAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(mServiceIntent);
        Log.i("MAINACT", "onDestroy!");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            refresh();
        }
    }


    @Override
    public void doClick(int pos) {
        //Toast.makeText(this, "" + mAlarmList.get(pos).id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, tambahActivity.class);
        intent.putExtra("code", EDIT);
        intent.putExtra("id", mAlarmList.get(pos).id);
        startActivity(intent);
    }

    @Override
    public void changeSwitch(int pos, boolean state) {
        db.enDisAlarm(mAlarmList.get(pos).id, state);
        Log.i("info", "onCheckedChanged: " + state);
    }
}
