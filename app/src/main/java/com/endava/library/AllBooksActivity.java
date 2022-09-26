package com.endava.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class AllBooksActivity extends AppCompatActivity {

   private RecyclerView allBooksRecyclerView;
   private BookRecyclerViewAdapter bookRecyclerViewAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_all_books);

      allBooksRecyclerView = findViewById(R.id.allBooksRecyclerView);
      bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this);

      bookRecyclerViewAdapter.setBooks(List.of(
            new Book(1L, "1Q84", "Haruki Murakami", 1350, "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/1Q84_(United_States_edition).jpg/220px-1Q84_(United_States_edition).jpg", "A work of maddening brilliance", "The book opens with a woman named Aomame (青豆) as she rides a taxi to a work assignment. She hears the Sinfonietta by Leoš Janáček playing on the radio and immediately recognizes it, somehow having detailed knowledge of its history and context. When the taxi gets stuck in a traffic jam on the Shibuya Route of the Shuto Expressway, the driver suggests she climb down an emergency escape to reach her meeting, warning her that it might change the very nature of reality. Aomame follows the driver's advice. Eventually, Aomame makes her way to a hotel in Shibuya and poses as an attendant in order to kill a guest. She performs the murder with an ice pick that leaves no trace on its victim. It is revealed that Aomame's job is to kill men who have committed domestic violence."),
            new Book(2L, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg", "One of the most influential works of this century, this is a crucial exposition of existentialist", "He begins by describing the following absurd condition: we build our life on the hope for tomorrow, yet tomorrow brings us closer to death and is the ultimate enemy; people live their lives as if they were not aware of the certainty of death. Once stripped of its common romanticism, the world is a foreign, strange and inhuman place; true knowledge is impossible and rationality and science cannot explain the world: their stories ultimately end in meaningless abstractions, in metaphors. This is the absurd condition and \"from the moment absurdity is recognized, it becomes a passion, the most harrowing of all.\"")
      ));
      allBooksRecyclerView.setAdapter(bookRecyclerViewAdapter);
      allBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   }
}