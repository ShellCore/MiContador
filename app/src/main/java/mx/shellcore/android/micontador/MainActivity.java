package mx.shellcore.android.micontador;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import mx.shellcore.android.micontador.fragments.CategoriesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;

    // Components
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        frame = (FrameLayout) findViewById(R.id.frame);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_categories:
                toolbar.setTitle(getString(R.string.menu_categories));
                CategoriesFragment fragment = new CategoriesFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
