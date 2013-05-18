package com.sabre.bto.refactoring.new_permissions;

public class SystemProfile {

   private final boolean unixPermissionRequired;
   
   SystemProfile(boolean unixPermissionRequired)
   {
      this.unixPermissionRequired = unixPermissionRequired;
   }
   
   public boolean isUnixPermissionRequired() {
      return unixPermissionRequired;
   }

}
