package com.endava.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

public class AlreadyReadActivity extends AppCompatActivity {

   private RecyclerView alreadyReadRecyclerView;
   private BookRecyclerViewAdapter bookRecyclerViewAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_already_read);
      Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

      alreadyReadRecyclerView = findViewById(R.id.alreadyReadRecyclerView);
      bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this, "already_read_books");

      bookRecyclerViewAdapter.setBooks(BookUtils.getInstance(this).getAlreadyReadBooks());
      alreadyReadRecyclerView.setAdapter(bookRecyclerViewAdapter);
      alreadyReadRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   }

   @Override
   public void onBackPressed() {
      Intent intent = new Intent(this, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if (item.getItemId() == android.R.id.home) onBackPressed();
      return super.onOptionsItemSelected(item);
   }
}