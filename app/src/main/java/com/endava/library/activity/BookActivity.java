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
            Book incomingBook = BookUtils.getInstance(this).getBookById(bookId);
            if (incomingBook != null) {
               setBookData(incomingBook);
               List<List<Book>> booksCategories = List.of(BookUtils.getInstance(this).getCurrentlyReadingBooks(), BookUtils.getInstance(this).getAlreadyReadBooks(),
                     BookUtils.getInstance(this).getWishListBooks(), BookUtils.getInstance(this).getFavoriteBooks());
               List<Button> addToCategoryButtons = List.of(addToCurrentlyReadingButton, addToAlreadyReadButton, addToWishListButton, addToFavoritesButton);
               List<Class<? extends AppCompatActivity>> categoriesActivities = List.of(CurrentlyReadingActivity.class, AlreadyReadActivity.class, WishListActivity.class, FavoriteBooksActivity.class);
               List<String> categoriesNames = List.of(CURRENTLY_READING_BOOKS_CATEGORY, ALREADY_READ_BOOKS_CATEGORY, WISH_LIST_BOOKS_CATEGORY, FAVORITE_BOOKS_CATEGORY);
               List<String> categoriesKeys = List.of(CURRENTLY_READING_BOOKS_KEY, ALREADY_READ_BOOKS_KEY, WISH_LIST_BOOKS_KEY, FAVORITE_BOOKS_KEY);
               for (int i = 0; i < booksCategories.size(); i++)
                  handleAddBookToCategory(incomingBook, booksCategories.get(i), addToCategoryButtons.get(i), categoriesActivities.get(i), categoriesNames.get(i), categoriesKeys.get(i));
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

   private void handleAddBookToCategory(Book incomingBook, List<Book> booksCategory, Button addToCategoryButton, Class<? extends AppCompatActivity> categoryActivity, String categoryName, String categoryKey) {
      if (bookExistsInCategory(incomingBook, booksCategory)) {
         addToCategoryButton.setEnabled(false);
      } else {
         addToCategoryButton.setOnClickListener(view -> {
            if (BookUtils.getInstance(BookActivity.this).addToCategory(incomingBook, booksCategory, categoryKey)) {
               Toast.makeText(BookActivity.this, "Book " + incomingBook.getTitle() + " added to " + categoryName, Toast.LENGTH_SHORT).show();
               startActivity(new Intent(BookActivity.this, categoryActivity));
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