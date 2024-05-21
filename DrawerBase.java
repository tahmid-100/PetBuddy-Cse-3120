public class DrawerBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
   public  void setContentView(View view){
        drawerLayout=(DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container=drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);


        NavigationView navigationView=drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            startActivity(new Intent(this, HomeActivity.class));
        } else if (itemId == R.id.profile) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (itemId == R.id.fav) {
            startActivity(new Intent(this, JsonParse.class));
        }



        return false;
    }

    protected  void allocteActivityTitle(String titleString){




    }


}