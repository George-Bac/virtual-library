package com.endava.library;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllBooksActivity extends AppCompatActivity {

   private RecyclerView allBooksRecyclerView;
   private BookRecyclerViewAdapter bookRecyclerViewAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_all_books);

      overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

      allBooksRecyclerView = findViewById(R.id.allBooksRecyclerView);
      bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this, "allBooks");

      bookRecyclerViewAdapter.setBooks(BookUtils.getAllBooks());
      allBooksRecyclerView.setAdapter(bookRecyclerViewAdapter);
      allBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   }

   @Override
   public void finish() {
      super.finish();
      overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
   }
}