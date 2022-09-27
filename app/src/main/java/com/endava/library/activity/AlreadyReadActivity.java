package com.endava.library.activity;

import static com.endava.library.Constants.ALREADY_READ_BOOKS_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.library.adapter.BookRecyclerViewAdapter;
import com.endava.library.utils.BookUtils;
import com.endava.library.R;

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
      bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this, ALREADY_READ_BOOKS_KEY);

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