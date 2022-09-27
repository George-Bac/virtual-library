package com.endava.library;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

   private static final String TAG = "BookRecyclerViewAdapter";

   private List<Book> books = new ArrayList<>();
   private final Context context;
   private final String parentActivity;

   public BookRecyclerViewAdapter(Context context, String parentActivity) {
      this.context = context;
      this.parentActivity = parentActivity;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false));
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
      Log.d(TAG, "onBindViewHolder: called");
      viewHolder.bookTitleTextView.setText(books.get(position).getTitle());
      viewHolder.bookAuthorTextView.setText(books.get(position).getAuthor());
      viewHolder.bookShortDescriptionTextView.setText(books.get(position).getShortDescription());
      Glide.with(context).asBitmap().load(books.get(position).getImageUrl()).into(viewHolder.bookImageView);

      viewHolder.parentCardView.setOnClickListener(view -> {
         Intent intent = new Intent(context, BookActivity.class);
         intent.putExtra("bookId", books.get(position).getId());
         context.startActivity(intent);
      });

      TransitionManager.beginDelayedTransition(viewHolder.parentCardView);
      viewHolder.expandedRelativeLayout.setVisibility(books.get(position).getExpanded() ? View.VISIBLE : View.GONE);
      viewHolder.arrowDownButton.setVisibility(books.get(position).getExpanded() ? View.GONE : View.VISIBLE);

      if (books.get(position).getExpanded()) {
         switch (parentActivity) {
            case "allBooks": viewHolder.deleteBookButton.setVisibility(View.GONE); break;
            case "currently_reading_books": handleRemoveBookFromCategory(viewHolder, books.get(position), BookUtils.getInstance(context).getCurrentlyReadingBooks(), "currently reading", "currently_reading_books"); break;
            case "already_read_books": handleRemoveBookFromCategory(viewHolder, books.get(position), BookUtils.getInstance(context).getAlreadyReadBooks(), "already read", "already_read_books"); break;
            case "wish_list_books": handleRemoveBookFromCategory(viewHolder, books.get(position), BookUtils.getInstance(context).getWishListBooks(), "wish list", "wish_list_books"); break;
            case "favorite_books": handleRemoveBookFromCategory(viewHolder, books.get(position), BookUtils.getInstance(context).getFavoriteBooks(), "favorites", "favorite_books"); break;
            default: Toast.makeText(context, "Invalid category", Toast.LENGTH_SHORT).show();
         }
      }
   }

   @Override
   public int getItemCount() {
      return books.size();
   }

   private void handleRemoveBookFromCategory(@NonNull ViewHolder viewHolder, Book book, List<Book> booksCategory, String categoryName, String categoryKey) {
      viewHolder.deleteBookButton.setVisibility(View.VISIBLE);
      viewHolder.deleteBookButton.setOnClickListener(view -> {
         AlertDialog.Builder builder = new AlertDialog.Builder(context);
         builder.setMessage("Are you sure you want to delete " + book.getTitle() + " from " + categoryName + "?");
         builder.setPositiveButton("Yes", (dialogInterface, which) -> {
            if (BookUtils.getInstance(context).removeFromCategory(book, booksCategory, categoryKey)) {
               Toast.makeText(context, book.getTitle() + " removed from " + categoryName, Toast.LENGTH_SHORT).show();
               notifyDataSetChanged();
            }
         });
         builder.setNegativeButton("No", (dialogInterface, which) -> Toast.makeText(context, "Dialog dismissed", Toast.LENGTH_SHORT).show());
         builder.create().show();
      });
   }

   public void setBooks(List<Book> books) {
      this.books = books;
      notifyDataSetChanged();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private CardView parentCardView;
      private RelativeLayout expandedRelativeLayout;
      private ImageView bookImageView, arrowDownButton, arrowUpButton;
      private TextView bookTitleTextView, bookAuthorTextView, bookShortDescriptionTextView, deleteBookButton;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         initViews(itemView);

         arrowDownButton.setOnClickListener(view -> invertExpandedValue());
         arrowUpButton.setOnClickListener(view -> invertExpandedValue());
      }

      private void initViews(@NonNull View itemView) {
         parentCardView = itemView.findViewById(R.id.parentCardView);
         bookImageView = itemView.findViewById(R.id.bookImageView);
         bookTitleTextView = itemView.findViewById(R.id.bookTitleTextView);

         expandedRelativeLayout = itemView.findViewById(R.id.expandedRelativeLayout);
         arrowDownButton = itemView.findViewById(R.id.arrowDownButton);
         arrowUpButton = itemView.findViewById(R.id.arrowUpButton);
         bookAuthorTextView = itemView.findViewById(R.id.bookAuthorTextView);
         bookShortDescriptionTextView = itemView.findViewById(R.id.bookShortDescriptionTextView);
         deleteBookButton = itemView.findViewById(R.id.deleteBookButton);
      }

      private void invertExpandedValue() {
         Book book = books.get(getAdapterPosition());
         book.setExpanded(!book.getExpanded());
         notifyItemChanged(getAdapterPosition());
      }
   }
}