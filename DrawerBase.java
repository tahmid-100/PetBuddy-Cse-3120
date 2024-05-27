/**
 * DrawerBase is a base activity class that provides navigation drawer functionality.
 * It handles the setup of the navigation drawer and its interaction with the toolbar.
 * Subclasses can extend this class to implement specific functionality for each menu item.
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /** Tag for logging purposes. */
    private static final String TAG = "DrawerBase";

    /** Layout for the navigation drawer. */
    private DrawerLayout drawerLayout;

    /** Toolbar for the activity. */
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_base);

        // Set up the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find the navigation drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);

        // Set up the navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set up the action bar drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Called when a navigation item in the drawer is selected.
     *
     * @param item The selected menu item.
     * @return true if the event was handled, false otherwise.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Close the drawer
        drawerLayout.closeDrawer(GravityCompat.START);

        // Get the ID of the selected item
        int itemId = item.getItemId();

        // Handle navigation item selection
        if (itemId == R.id.home) {
            startActivity(new Intent(this, HomeActivity.class));
        } else if (itemId == R.id.profile) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (itemId == R.id.fav) {
            startActivity(new Intent(this, JsonParse.class));
        }

        return false;
    }

    /**
     * Subclasses can override this method to allocate a custom title to the activity.
     *
     * @param titleString The title string to be set.
     */
    protected void allocateActivityTitle(String titleString) {
        // Implementation left to subclasses
    }
}
