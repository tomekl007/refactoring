package com.sabre.bto.refactoring.new_permissions;

public class SystemPermission {

   public final static String REQUESTED = "REQUESTED";
   public final static String CLAIMED = "CLAIMED";
   public final static String GRANTED = "GRANTED";
   public final static String DENIED = "DENIED";
   public final static String UNIX_REQUESTED = "UNIX_REQUESTED";
   public final static String UNIX_CLAIMED = "UNIX_CLAIMED";
   
   private SystemProfile profile;
   private SystemUser requestor;
   private SystemAdmin admin;
   private boolean granted;
   private boolean unixPermissionGranted;
   
   private String state;
   
   public boolean isUnixPermissionGranted() {
      return unixPermissionGranted;
   }

   void setUnixPermissionGranted(boolean unixPermissionGranted) {
      this.unixPermissionGranted = unixPermissionGranted;
   }

   public boolean isGranted() {
      return granted;
   }



   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public SystemPermission(SystemUser requestor, SystemProfile profile) {
      this.requestor = requestor;
      this.profile = profile;
      state = REQUESTED;
      granted = false;
      unixPermissionGranted = false;
      notifyAdminOfPermissionRequest();
   }

   private void notifyAdminOfPermissionRequest() {
      // TODO Auto-generated method stub
   }

   public void claimedBy(SystemAdmin admin) {
      if (!state.equals(REQUESTED) && !state.equals(UNIX_REQUESTED))
         return;
       willBeHandledBy(admin);
       if (state.equals(REQUESTED))
         state = CLAIMED;
       else if (state.equals(UNIX_REQUESTED))
         state = UNIX_CLAIMED;
   }

   private void willBeHandledBy(SystemAdmin admin) {
      this.admin = admin;
      
   }

   public void deniedBy(SystemAdmin admin) {
      if (!state.equals(CLAIMED) && !state.equals(UNIX_CLAIMED))
         return;
       if (!this.admin.equals(admin))
         return;
       granted = false;
       unixPermissionGranted = false;
       state = DENIED;
       notifyUserOfPermissionRequestResult();
   }

   private void notifyUserOfPermissionRequestResult() {
      // TODO Auto-generated method stub

   }

   public void grantedBy(SystemAdmin admin) {
      if (!state.equals(CLAIMED) && !state.equals(UNIX_CLAIMED))
         return;
       if (!this.admin.equals(admin))
         return;

       if (profile.isUnixPermissionRequired() && state.equals(UNIX_CLAIMED))
         unixPermissionGranted = true;
       else if (profile.isUnixPermissionRequired() &&
         !isUnixPermissionGranted()) {
         state = UNIX_REQUESTED;
         notifyAdminOfPermissionRequest();
         return;
       }
       state = GRANTED;
       granted = true;
       notifyUserOfPermissionRequestResult();
   }

}
