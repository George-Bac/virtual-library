package com.endava.library;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WishListActivity extends AppCompatActivity {

   private RecyclerView wishListRecyclerView;
   private BookRecyclerViewAdapter bookRecyclerViewAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_wish_list);

      wishListRecyclerView = findViewById(R.id.wishListRecyclerView);
      bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this);

      bookRecyclerViewAdapter.setBooks(BookUtils.getWishListBooks());
      wishListRecyclerView.setAdapter(bookRecyclerViewAdapter);
      wishListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   }

   @Override
   public void onBackPressed() {
      Intent intent = new Intent(this, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
   }
}
