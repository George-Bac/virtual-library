package com.endava.library;

import java.util.ArrayList;
import java.util.List;

public class BookUtils {

   private static BookUtils instance;
   private static List<Book> allBooks;
   private static List<Book> currentlyReadingBooks;
   private static List<Book> alreadyReadBooks;
   private static List<Book> wishListBooks;
   private static List<Book> favoriteBooks;

   private BookUtils() {
      if (allBooks == null) {
         allBooks = List.of(
               new Book(1L, "1Q84", "Haruki Murakami", 1350, "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/1Q84_(United_States_edition).jpg/220px-1Q84_(United_States_edition).jpg", "A work of maddening brilliance", "The book opens with a woman named Aomame (青豆) as she rides a taxi to a work assignment. She hears the Sinfonietta by Leoš Janáček playing on the radio and immediately recognizes it, somehow having detailed knowledge of its history and context. When the taxi gets stuck in a traffic jam on the Shibuya Route of the Shuto Expressway, the driver suggests she climb down an emergency escape to reach her meeting, warning her that it might change the very nature of reality. Aomame follows the driver's advice. Eventually, Aomame makes her way to a hotel in Shibuya and poses as an attendant in order to kill a guest. She performs the murder with an ice pick that leaves no trace on its victim. It is revealed that Aomame's job is to kill men who have committed domestic violence."),
               new Book(2L, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg", "One of the most influential works of this century, this is a crucial exposition of existentialist", "He begins by describing the following absurd condition: we build our life on the hope for tomorrow, yet tomorrow brings us closer to death and is the ultimate enemy; people live their lives as if they were not aware of the certainty of death. Once stripped of its common romanticism, the world is a foreign, strange and inhuman place; true knowledge is impossible and rationality and science cannot explain the world: their stories ultimately end in meaningless abstractions, in metaphors. This is the absurd condition and \"from the moment absurdity is recognized, it becomes a passion, the most harrowing of all.\"")
         );
      }
      if (currentlyReadingBooks == null) {
         currentlyReadingBooks = new ArrayList<>();
      }
      if (alreadyReadBooks == null) {
         alreadyReadBooks = new ArrayList<>();
      }
      if (wishListBooks == null) {
         wishListBooks = new ArrayList<>();
      }
      if (favoriteBooks == null) {
         favoriteBooks = new ArrayList<>();
      }
   }

   public static BookUtils getInstance() {
      if (instance == null) instance = new BookUtils();
      return instance;
   }

   public static List<Book> getAllBooks() {
      return allBooks;
   }

   public static List<Book> getCurrentlyReadingBooks() {
      return currentlyReadingBooks;
   }

   public static List<Book> getAlreadyReadBooks() {
      return alreadyReadBooks;
   }

   public static List<Book> getWishListBooks() {
      return wishListBooks;
   }

   public static List<Book> getFavoriteBooks() {
      return favoriteBooks;
   }
}