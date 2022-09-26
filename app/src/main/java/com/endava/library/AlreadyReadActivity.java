package com.endava.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadActivity extends AppCompatActivity {

   private RecyclerView alreadyReadRecyclerView;
   private BookRecyclerViewAdapter bookRecyclerViewAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_already_read);

      alreadyReadRecyclerView = findViewById(R.id.alreadyReadRecyclerView);
      bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this, "alreadyRead");

      bookRecyclerViewAdapter.setBooks(BookUtils.getAlreadyReadBooks());
      alreadyReadRecyclerView.setAdapter(bookRecyclerViewAdapter);
      alreadyReadRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   }

   @Override
   public void onBackPressed() {
      Intent intent = new Intent(this, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
   }
}