package com.endava.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavoriteBooksActivity extends AppCompatActivity {

   private RecyclerView favoriteBooksRecyclerView;
   private BookRecyclerViewAdapter bookRecyclerViewAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_favorite_books);

      favoriteBooksRecyclerView = findViewById(R.id.favoriteBooksRecyclerView);
      bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this);

      bookRecyclerViewAdapter.setBooks(BookUtils.getFavoriteBooks());
      favoriteBooksRecyclerView.setAdapter(bookRecyclerViewAdapter);
      favoriteBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   }

   @Override
   public void onBackPressed() {
      Intent intent = new Intent(this, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
   }
}