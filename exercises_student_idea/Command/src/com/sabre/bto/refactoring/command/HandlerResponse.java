package com.sabre.bto.refactoring.command;

public class HandlerResponse {

   private final String content;
   private final ResponseType type;
   
   public HandlerResponse(String content, ResponseType type) {
      this.content = content;
      this.type = type;
   }
   
   String getContent()
   {
      return content;
   }
   
   ResponseType getType()
   {
      return type;
   }

}
