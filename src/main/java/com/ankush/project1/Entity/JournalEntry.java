package com.ankush.project1.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Document(collection = "journal")
@Data
@NoArgsConstructor
public class JournalEntry {
        @Id
        private ObjectId id;
        @NonNull
        private  String title;
        private  String content;
        private LocalDate date;
    }

