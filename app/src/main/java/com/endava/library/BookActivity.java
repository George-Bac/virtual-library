package com.endava.library;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

public class BookActivity extends AppCompatActivity {

   private TextView itemBookTitleTextView, itemBookAuthorTextView, itemBookPagesTextView, itemBookLongDescriptionTextView;
   private Button addToCurrentlyReadingButton, addToAlreadyReadButton, addToWishListButton, addToFavoritesButton;
   private ImageView itemBookImageView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_book);
      initViews();

      Intent intent = getIntent();
      if (intent != null) {
         long bookId = intent.getLongExtra("bookId", -1);
         if (bookId != -1) {
            Book incomingBook = BookUtils.getInstance().getBookById(bookId);
            if (incomingBook != null) {
               setBookData(incomingBook);
               handleAddToCurrentlyReading(incomingBook);
               handleAddToAlreadyRead(incomingBook);
               handleAddToWishList(incomingBook);
               handleAddToFavorites(incomingBook);
            }
         }
      }
   }

   private void initViews() {
      itemBookImageView = findViewById(R.id.itemBookImageView);
      itemBookTitleTextView = findViewById(R.id.itemBookTitleTextView);
      itemBookAuthorTextView = findViewById(R.id.itemBookAuthorTextView);
      itemBookPagesTextView = findViewById(R.id.itemBookPagesTextView);
      itemBookLongDescriptionTextView = findViewById(R.id.itemBookLongDescriptionTextView);

      addToCurrentlyReadingButton = findViewById(R.id.addToCurrentlyReadingButton);
      addToAlreadyReadButton = findViewById(R.id.addToAlreadyReadButton);
      addToWishListButton = findViewById(R.id.addToWishListButton);
      addToFavoritesButton = findViewById(R.id.addToFavoritesButton);
   }

   private void setBookData(Book book) {
      Glide.with(this).asBitmap().load(book.getImageUrl()).into(itemBookImageView);
      itemBookTitleTextView.setText(book.getTitle());
      itemBookAuthorTextView.setText(book.getAuthor());
      itemBookPagesTextView.setText(String.valueOf(book.getNumberOfPages()));
      itemBookLongDescriptionTextView.setText(book.getLongDescription());
   }

   private void handleAddToCurrentlyReading(Book incomingBook) {
      if (bookExistsInCategory(incomingBook, BookUtils.getCurrentlyReadingBooks())) {
         addToCurrentlyReadingButton.setEnabled(false);
      } else {
         addToCurrentlyReadingButton.setOnClickListener(view -> {
            if (BookUtils.getInstance().addToCurrentlyReading(incomingBook)) {
               Toast.makeText(BookActivity.this, "Book " + incomingBook.getTitle() + " added to currently reading", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(BookActivity.this, CurrentlyReadingActivity.class));
            } else {
               Toast.makeText(BookActivity.this, "Something went wrong! Try again!", Toast.LENGTH_SHORT).show();
            }
         });
      }
   }

   private void handleAddToAlreadyRead(Book incomingBook) {
      if (bookExistsInCategory(incomingBook, BookUtils.getAlreadyReadBooks())) {
         addToAlreadyReadButton.setEnabled(false);
      } else {
         addToAlreadyReadButton.setOnClickListener(view -> {
            if (BookUtils.getInstance().addToAlreadyRead(incomingBook)) {
               Toast.makeText(BookActivity.this, "Book " + incomingBook.getTitle() + " added to already read", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(BookActivity.this, AlreadyReadActivity.class));
            } else {
               Toast.makeText(BookActivity.this, "Something went wrong! Try again!", Toast.LENGTH_SHORT).show();
            }
         });
      }
   }

   private void handleAddToWishList(Book incomingBook) {
      if (bookExistsInCategory(incomingBook, BookUtils.getWishListBooks())) {
         addToWishListButton.setEnabled(false);
      } else {
         addToWishListButton.setOnClickListener(view -> {
            if (BookUtils.getInstance().addToWishList(incomingBook)) {
               Toast.makeText(BookActivity.this, "Book " + incomingBook.getTitle() + " added to wish list", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(BookActivity.this, WishListActivity.class));
            } else {
               Toast.makeText(BookActivity.this, "Something went wrong! Try again!", Toast.LENGTH_SHORT).show();
            }
         });
      }
   }

   private void handleAddToFavorites(Book incomingBook) {
      if (bookExistsInCategory(incomingBook, BookUtils.getFavoriteBooks())) {
         addToFavoritesButton.setEnabled(false);
      } else {
         addToFavoritesButton.setOnClickListener(view -> {
            if (BookUtils.getInstance().addToFavorites(incomingBook)) {
               Toast.makeText(BookActivity.this, "Book " + incomingBook.getTitle() + " added to favorites", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(BookActivity.this, FavoriteBooksActivity.class));
            } else {
               Toast.makeText(BookActivity.this, "Something went wrong! Try again!", Toast.LENGTH_SHORT).show();
            }
         });
      }
   }

   private boolean bookExistsInCategory(Book incomingBook, List<Book> books) {
      for (Book book : books) {
         if (Objects.equals(book.getId(), incomingBook.getId())) return true;
      }
      return false;
   }
}