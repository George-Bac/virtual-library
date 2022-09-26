package com.endava.library;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

   private TextView itemBookTitleTextView, itemBookAuthorTextView, itemBookPagesTextView, itemBookLongDescriptionTextView;
   private Button addToCurrentlyReadingButton, addToAlreadyReadButton, addToWishListButton, addToFavoritesButton;
   private ImageView itemBookImageView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_book);
      initViews();

      //TODO: Get the data from recycler view in here
      Book book = new Book(1L, "1Q84", "Haruki Murakami", 1350, "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/1Q84_(United_States_edition).jpg/220px-1Q84_(United_States_edition).jpg", "A work of maddening brilliance", "The book opens with a woman named Aomame (青豆) as she rides a taxi to a work assignment. She hears the Sinfonietta by Leoš Janáček playing on the radio and immediately recognizes it, somehow having detailed knowledge of its history and context. When the taxi gets stuck in a traffic jam on the Shibuya Route of the Shuto Expressway, the driver suggests she climb down an emergency escape to reach her meeting, warning her that it might change the very nature of reality. Aomame follows the driver's advice. Eventually, Aomame makes her way to a hotel in Shibuya and poses as an attendant in order to kill a guest. She performs the murder with an ice pick that leaves no trace on its victim. It is revealed that Aomame's job is to kill men who have committed domestic violence.");
      setData(book);
   }

   private void setData(Book book) {
      Glide.with(this).asBitmap().load(book.getImageUrl()).into(itemBookImageView);
      itemBookTitleTextView.setText(book.getTitle());
      itemBookAuthorTextView.setText(book.getAuthor());
      itemBookPagesTextView.setText(String.valueOf(book.getNumberOfPages()));
      itemBookLongDescriptionTextView.setText(book.getLongDescription());
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
}