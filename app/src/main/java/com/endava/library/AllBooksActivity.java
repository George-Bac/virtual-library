package com.endava.library;

import static com.endava.library.Constants.ALL_BOOKS_KEY;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class AllBooksActivity extends AppCompatActivity {

   private RecyclerView allBooksRecyclerView;
   private BookRecyclerViewAdapter bookRecyclerViewAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_all_books);
      Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

      allBooksRecyclerView = findViewById(R.id.allBooksRecyclerView);
      bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this, ALL_BOOKS_KEY);

      bookRecyclerViewAdapter.setBooks(BookUtils.getInstance(this).getAllBooks());
      allBooksRecyclerView.setAdapter(bookRecyclerViewAdapter);
      allBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if (item.getItemId() == android.R.id.home) onBackPressed();
      return super.onOptionsItemSelected(item);
   }
}