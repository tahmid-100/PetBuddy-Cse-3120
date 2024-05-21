public class HomeActivity extends DrawerBase {


    ImageButton image_button_1,image_button_2,image_button_3;
  ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_home);




        ImageSlider imageSlider=findViewById(R.id.imageSlider);
        ArrayList<SlideModel>slideModels=new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.image_home, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_home_cat, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_home_dog, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);


        image_button_1=findViewById(R.id.image_btn1);
        image_button_2=findViewById(R.id.image_btn2);
        image_button_3=findViewById(R.id.image_btn3);

    }
     image_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, Bird.class);
                startActivity(intent);

            }
        });

}