package com.endava.library.activity;

import static com.endava.library.Constants.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.endava.library.model.Book;
import com.endava.library.model.BookAddition;
import com.endava.library.utils.BookUtils;
import com.endava.library.R;

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
      Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
      initViews();

      Intent intent = getIntent();
      if (intent != null) {
         long bookId = intent.getLongExtra(BOOK_ID, -1);
         if (bookId != -1) {
            BookUtils instance = BookUtils.getInstance(this);
            Book incomingBook = instance.getBookById(bookId);
            if (incomingBook != null) {
               setBookData(incomingBook);
               List<BookAddition> bookAdditions = List.of(
                     new BookAddition(instance.getCurrentlyReadingBooks(), addToCurrentlyReadingButton, CURRENTLY_READING_BOOKS_CATEGORY, CURRENTLY_READING_BOOKS_KEY, CurrentlyReadingActivity.class),
                     new BookAddition(instance.getAlreadyReadBooks(), addToAlreadyReadButton, ALREADY_READ_BOOKS_CATEGORY, ALREADY_READ_BOOKS_KEY, AlreadyReadActivity.class),
                     new BookAddition(instance.getWishListBooks(), addToWishListButton, WISH_LIST_BOOKS_CATEGORY, WISH_LIST_BOOKS_KEY, WishListActivity.class),
                     new BookAddition(instance.getFavoriteBooks(), addToFavoritesButton, FAVORITE_BOOKS_CATEGORY, FAVORITE_BOOKS_KEY, FavoriteBooksActivity.class)
               );
               for (int i = 0; i < bookAdditions.size(); i++) handleAddBookToCategory(incomingBook, bookAdditions.get(i));
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

   private void handleAddBookToCategory(Book incomingBook, BookAddition bookAddition) {
      if (bookExistsInCategory(incomingBook, bookAddition.getBooksCategory())) {
         bookAddition.getAddToCategoryButton().setEnabled(false);
      } else {
         bookAddition.getAddToCategoryButton().setOnClickListener(view -> {
            if (BookUtils.getInstance(BookActivity.this).addToCategory(incomingBook, bookAddition.getBooksCategory(), bookAddition.getCategoryKey())) {
               Toast.makeText(BookActivity.this, "Book " + incomingBook.getTitle() + " added to " + bookAddition.getCategoryName(), Toast.LENGTH_SHORT).show();
               startActivity(new Intent(BookActivity.this, bookAddition.getCategoryActivity()));
            } else {
               Toast.makeText(BookActivity.this, ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
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

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if (item.getItemId() == android.R.id.home) onBackPressed();
      return super.onOptionsItemSelected(item);
   }
}