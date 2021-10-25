package com.example.notassqlite.db;

public class Datos {

   private  int id;
    private String title;
    private String description;
     private int deleted;


     public Datos(int id, String title, String description, int deleted) {
          this.id = id;
          this.title = title;
          this.description = description;
          this.deleted = deleted;
     }

     public int getId() {
          return id;
     }

     public String getTitle() {
          return title;
     }

     public String getDescription() {
          return description;
     }

     public int getDeleted() {
          return deleted;
     }
}
