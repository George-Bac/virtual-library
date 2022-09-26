package com.endava.library;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

   private Button allBooksButton, currentlyReadingButton, alreadyReadButton, wishListButton, favoritesButton, aboutButton;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      initViews();

      BookUtils.getInstance();
      allBooksButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AllBooksActivity.class)));
      currentlyReadingButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CurrentlyReadingActivity.class)));
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