package com.endava.library.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Book {

   private Long id;
   private String title;
   private String author;
   private Integer numberOfPages;
   private String imageUrl;
   private String shortDescription;
   private String longDescription;
   private Boolean isExpanded;

   public Book(Long id, String title, String author, Integer numberOfPages, String imageUrl, String shortDescription, String longDescription) {
      this.id = id;
      this.title = title;
      this.author = author;
      this.numberOfPages = numberOfPages;
      this.imageUrl = imageUrl;
      this.shortDescription = shortDescription;
      this.longDescription = longDescription;
      isExpanded = false;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public Integer getNumberOfPages() {
      return numberOfPages;
   }

   public void setNumberOfPages(Integer numberOfPages) {
      this.numberOfPages = numberOfPages;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public String getShortDescription() {
      return shortDescription;
   }

   public void setShortDescription(String shortDescription) {
      this.shortDescription = shortDescription;
   }

   public String getLongDescription() {
      return longDescription;
   }

   public void setLongDescription(String longDescription) {
      this.longDescription = longDescription;
   }

   public Boolean getExpanded() {
      return isExpanded;
   }

   public void setExpanded(Boolean expanded) {
      isExpanded = expanded;
   }

   @NonNull
   @Override
   public String toString() {
      return "Book{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", numberOfPages=" + numberOfPages +
            ", imageUrl='" + imageUrl + '\'' +
            ", shortDescription='" + shortDescription + '\'' +
            ", longDescription='" + longDescription + '\'' +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Book book = (Book) o;
      return id.equals(book.id) &&
            title.equals(book.title) &&
            author.equals(book.author) &&
            numberOfPages.equals(book.numberOfPages) &&
            imageUrl.equals(book.imageUrl) &&
            shortDescription.equals(book.shortDescription) &&
            longDescription.equals(book.longDescription);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, title, author, numberOfPages, imageUrl, shortDescription, longDescription);
   }
}