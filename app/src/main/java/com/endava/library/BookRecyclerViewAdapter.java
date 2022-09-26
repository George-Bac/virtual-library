package com.endava.library;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

   public BookRecyclerViewAdapter(Context context) {
      this.context = context;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.book, parent, false));
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
      Log.d(TAG, "onBindViewHolder: called");
      viewHolder.bookTitleTextView.setText(books.get(position).getTitle());
      viewHolder.bookAuthorTextView.setText(books.get(position).getAuthor());
      viewHolder.bookShortDescriptionTextView.setText(books.get(position).getShortDescription());
      Glide.with(context).asBitmap().load(books.get(position).getImageUrl()).into(viewHolder.bookImageView);

      viewHolder.parentCardView.setOnClickListener(view -> Toast.makeText(context, books.get(position).getTitle() + " selected", Toast.LENGTH_SHORT).show());

      TransitionManager.beginDelayedTransition(viewHolder.parentCardView);
      viewHolder.expandedRelativeLayout.setVisibility(books.get(position).getExpanded() ? View.VISIBLE : View.GONE);
      viewHolder.arrowDownButton.setVisibility(books.get(position).getExpanded() ? View.GONE : View.VISIBLE);
   }

   @Override
   public int getItemCount() {
      return books.size();
   }

   public void setBooks(List<Book> books) {
      this.books = books;
      notifyDataSetChanged();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private CardView parentCardView;
      private RelativeLayout expandedRelativeLayout;
      private ImageView bookImageView, arrowDownButton, arrowUpButton;
      private TextView bookTitleTextView, bookAuthorTextView, bookShortDescriptionTextView;

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
      }

      private void invertExpandedValue() {
         Book book = books.get(getAdapterPosition());
         book.setExpanded(!book.getExpanded());
         notifyItemChanged(getAdapterPosition());
      }
   }
}