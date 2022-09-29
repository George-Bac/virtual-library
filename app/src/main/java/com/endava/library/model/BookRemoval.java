package com.endava.library.model;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

public class BookRemoval {

   List<Book> booksCategory;
   String categoryName;
   String categoryKey;
   Class<? extends AppCompatActivity> categoryActivity;

   public BookRemoval(List<Book> booksCategory, String categoryName, String categoryKey, Class<? extends AppCompatActivity> categoryActivity) {
      this.booksCategory = booksCategory;
      this.categoryName = categoryName;
      this.categoryKey = categoryKey;
      this.categoryActivity = categoryActivity;
   }

   public List<Book> getBooksCategory() {
      return booksCategory;
   }

   public void setBooksCategory(List<Book> booksCategory) {
      this.booksCategory = booksCategory;
   }

   public String getCategoryName() {
      return categoryName;
   }

   public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
   }

   public String getCategoryKey() {
      return categoryKey;
   }

   public void setCategoryKey(String categoryKey) {
      this.categoryKey = categoryKey;
   }

   public Class<? extends AppCompatActivity> getCategoryActivity() {
      return categoryActivity;
   }

   public void setCategoryActivity(Class<? extends AppCompatActivity> categoryActivity) {
      this.categoryActivity = categoryActivity;
   }

   @Override
   public String toString() {
      return "BookRemoval{" +
            "booksCategory=" + booksCategory +
            ", categoryName='" + categoryName + '\'' +
            ", categoryKey='" + categoryKey + '\'' +
            ", categoryActivity=" + categoryActivity +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      BookRemoval that = (BookRemoval) o;
      return booksCategory.equals(that.booksCategory) &&
            categoryName.equals(that.categoryName) &&
            categoryKey.equals(that.categoryKey) &&
            categoryActivity.equals(that.categoryActivity);
   }

   @Override
   public int hashCode() {
      return Objects.hash(booksCategory, categoryName, categoryKey, categoryActivity);
   }
}
