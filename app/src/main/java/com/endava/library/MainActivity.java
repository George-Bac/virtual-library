package com.endava.library;

import static com.endava.library.Constants.ABOUT_PAGE_URL;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

   private Button allBooksButton, currentlyReadingButton, alreadyReadButton, wishListButton, favoritesButton, aboutButton;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      initViews();

      BookUtils.getInstance(this);
      allBooksButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AllBooksActivity.class)));
      currentlyReadingButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CurrentlyReadingActivity.class)));
      alreadyReadButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AlreadyReadActivity.class)));
      wishListButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, WishListActivity.class)));
      favoritesButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, FavoriteBooksActivity.class)));
      aboutButton.setOnClickListener(view -> {
         AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
         builder.setTitle(getString(R.string.app_name));
         builder.setMessage("Developed by George Bacalu");
         builder.setPositiveButton("Visit", (dialogInterface, which) -> {
            Intent intent = new Intent(MainActivity.this, WebActivity.class);
            intent.putExtra(ABOUT_PAGE_URL, "https://www.google.com/");
            startActivity(intent);
         });
         builder.setNegativeButton("Dismiss", (dialogInterface, which) -> Toast.makeText(MainActivity.this, "Dialog dismissed", Toast.LENGTH_SHORT).show());
         builder.create().show();
      });
   }

   private void initViews() {
      allBooksButton = findViewById(R.id.allBooksButton);
      currentlyReadingButton = findViewById(R.id.currentlyReadingButton);
      alreadyReadButton = findViewById(R.id.alreadyReadButton);
      wishListButton = findViewById(R.id.wishListButton);
      favoritesButton = findViewById(R.id.favoritesButton);
      aboutButton = findViewById(R.id.aboutButton);
   }
}