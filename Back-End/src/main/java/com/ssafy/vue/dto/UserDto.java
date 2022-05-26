package com.ssafy.vue.dto;

public class UserDto {
   private String id;
   private String pw;
   private String name;
   private String email;
   private int campus;
   
   public UserDto() {
      super();
   }
   
   public UserDto(String id, String name, String email) {
      super();
      this.id = id;
      this.name = name;
      this.email = email;
   }

   public UserDto(String id, String pw) {
      super();
      this.id = id;
      this.pw = pw;
   }
   
   public UserDto(String id, String pw, String name, String email, int campus) {
      super();
      this.id = id;
      this.pw = pw;
      this.name = name;
      this.email = email;
      this.campus = campus;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPw() {
      return pw;
   }

   public void setPw(String pw) {
      this.pw = pw;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
   
   public int getCampus() {
      return campus;
   }

   public void setCampus(int campus) {
      this.campus = campus;
   }

   @Override
   public String toString() {
      return "UserDto [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", campus=" + campus + "]";
   }
}