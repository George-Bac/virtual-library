package com.endava.library;

import static com.endava.library.Constants.ALL_BOOKS_KEY;
import static com.endava.library.Constants.ALREADY_READ_BOOKS_KEY;
import static com.endava.library.Constants.CURRENTLY_READING_BOOKS_KEY;
import static com.endava.library.Constants.FAVORITE_BOOKS_KEY;
import static com.endava.library.Constants.SHARED_PREFERENCES_VERSION;
import static com.endava.library.Constants.WISH_LIST_BOOKS_KEY;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookUtils {

   private static BookUtils instance;
   private final SharedPreferences sharedPreferences;

   private BookUtils(Context context) {
      sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_VERSION, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      if (getAllBooks() == null) {
         editor.putString(ALL_BOOKS_KEY, new Gson().toJson(List.of(
               new Book(1L, "1Q84", "Haruki Murakami", 1350, "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/1Q84_(United_States_edition).jpg/220px-1Q84_(United_States_edition).jpg", "A work of maddening brilliance", "The book opens with a woman named Aomame (青豆) as she rides a taxi to a work assignment. She hears the Sinfonietta by Leoš Janáček playing on the radio and immediately recognizes it, somehow having detailed knowledge of its history and context. When the taxi gets stuck in a traffic jam on the Shibuya Route of the Shuto Expressway, the driver suggests she climb down an emergency escape to reach her meeting, warning her that it might change the very nature of reality. Aomame follows the driver's advice. Eventually, Aomame makes her way to a hotel in Shibuya and poses as an attendant in order to kill a guest. She performs the murder with an ice pick that leaves no trace on its victim. It is revealed that Aomame's job is to kill men who have committed domestic violence."),
               new Book(2L, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg", "One of the most influential works of this century, this is a crucial exposition of existentialist", "He begins by describing the following absurd condition: we build our life on the hope for tomorrow, yet tomorrow brings us closer to death and is the ultimate enemy; people live their lives as if they were not aware of the certainty of death. Once stripped of its common romanticism, the world is a foreign, strange and inhuman place; true knowledge is impossible and rationality and science cannot explain the world: their stories ultimately end in meaningless abstractions, in metaphors. This is the absurd condition and \"from the moment absurdity is recognized, it becomes a passion, the most harrowing of all.\"")
         )));
      }
      if (getCurrentlyReadingBooks() == null) convertFromListToJsonAndCommit(editor, CURRENTLY_READING_BOOKS_KEY);
      if (getAlreadyReadBooks() == null) convertFromListToJsonAndCommit(editor, ALREADY_READ_BOOKS_KEY);
      if (getWishListBooks() == null) convertFromListToJsonAndCommit(editor, WISH_LIST_BOOKS_KEY);
      if (getFavoriteBooks() == null) convertFromListToJsonAndCommit(editor, FAVORITE_BOOKS_KEY);
   }

   public static BookUtils getInstance(Context context) {
      if (instance == null) instance = new BookUtils(context);
      return instance;
   }

   public List<Book> getAllBooks() {
      return convertFromJsonToList(ALL_BOOKS_KEY);
   }

   public List<Book> getCurrentlyReadingBooks() {
      return convertFromJsonToList(CURRENTLY_READING_BOOKS_KEY);
   }

   public List<Book> getAlreadyReadBooks() {
      return convertFromJsonToList(ALREADY_READ_BOOKS_KEY);
   }

   public List<Book> getWishListBooks() {
      return convertFromJsonToList(WISH_LIST_BOOKS_KEY);
   }

   public List<Book> getFavoriteBooks() {
      return convertFromJsonToList(FAVORITE_BOOKS_KEY);
   }

   private void convertFromListToJsonAndCommit(SharedPreferences.Editor editor, String key) {
      editor.putString(key, new Gson().toJson(new ArrayList<>()));
      editor.commit();
   }

   @Nullable
   private List<Book> convertFromJsonToList(String key) {
      return new Gson().fromJson(sharedPreferences.getString(key, null), new TypeToken<List<Book>>() {}.getType());
   }

   public Book getBookById(Long bookId) {
      for (Book book : getAllBooks()) {
         if (Objects.equals(book.getId(), bookId)) return book;
      }
      return null;
   }

   public boolean addToCategory(Book book, List<Book> books, String key) {
      if (books != null) {
         if (books.add(book)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.putString(key, new Gson().toJson(books));
            editor.commit();
            return true;
         }
      }
      return false;
   }

   public boolean removeFromCategory(Book incomingBook, List<Book> books, String key) {
      if (books != null) {
         for (Book book : books) {
            if (Objects.equals(book.getId(), incomingBook.getId())) {
               if (books.remove(book)) {
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putString(key, new Gson().toJson(books));
                  editor.commit();
                  return true;
               }
            }
         }
      }
      return false;
   }
}