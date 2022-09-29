package com.endava.library.model;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

public class BookAddition {

   List<Book> booksCategory;
   Button addToCategoryButton;
   String categoryName;
   String categoryKey;
   Class<? extends AppCompatActivity> categoryActivity;

   public BookAddition(List<Book> booksCategory, Button addToCategoryButton, String categoryName, String categoryKey, Class<? extends AppCompatActivity> categoryActivity) {
      this.booksCategory = booksCategory;
      this.addToCategoryButton = addToCategoryButton;
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

   public Button getAddToCategoryButton() {
      return addToCategoryButton;
   }

   public void setAddToCategoryButton(Button addToCategoryButton) {
      this.addToCategoryButton = addToCategoryButton;
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
      return "AddBook{" +
            "booksCategory=" + booksCategory +
            ", addToCategoryButton=" + addToCategoryButton +
            ", categoryName='" + categoryName + '\'' +
            ", categoryKey='" + categoryKey + '\'' +
            ", categoryActivity=" + categoryActivity +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      BookAddition bookAddition = (BookAddition) o;
      return booksCategory.equals(bookAddition.booksCategory) &&
            addToCategoryButton.equals(bookAddition.addToCategoryButton) &&
            categoryName.equals(bookAddition.categoryName) &&
            categoryKey.equals(bookAddition.categoryKey) &&
            categoryActivity.equals(bookAddition.categoryActivity);
   }

   @Override
   public int hashCode() {
      return Objects.hash(booksCategory, addToCategoryButton, categoryName, categoryKey, categoryActivity);
   }
}
