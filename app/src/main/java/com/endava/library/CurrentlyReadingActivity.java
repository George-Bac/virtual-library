package com.endava.library;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CurrentlyReadingActivity extends AppCompatActivity {

   private RecyclerView currentlyReadingRecyclerView;
   private BookRecyclerViewAdapter bookRecyclerViewAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_currently_reading);

      currentlyReadingRecyclerView = findViewById(R.id.currentlyReadingRecyclerView);
      bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this, "currentlyReading");

      bookRecyclerViewAdapter.setBooks(BookUtils.getCurrentlyReadingBooks());
      currentlyReadingRecyclerView.setAdapter(bookRecyclerViewAdapter);
      currentlyReadingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   }

   @Override
   public void onBackPressed() {
      Intent intent = new Intent(this, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
   }
}