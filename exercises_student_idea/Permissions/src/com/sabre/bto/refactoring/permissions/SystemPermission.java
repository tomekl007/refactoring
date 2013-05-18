package com.sabre.bto.refactoring.permissions;

public class SystemPermission {

   public final static String REQUESTED = "REQUESTED";
   public final static String CLAIMED = "CLAIMED";
   public final static String GRANTED = "GRANTED";
   public final static String DENIED = "DENIED";

   private String state;
   
   private SystemProfile profile;
   private SystemUser requestor;
   private SystemAdmin admin;
   private boolean granted;

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
      notifyAdminOfPermissionRequest();
   }

   private void notifyAdminOfPermissionRequest() {
      // TODO Auto-generated method stub

   }

   public void claimedBy(SystemAdmin admin) {
      if (!state.equals(REQUESTED))
         return;
      willBeHandledBy(admin);
      state = CLAIMED;
   }

   private void willBeHandledBy(SystemAdmin admin) {
      this.admin = admin;

   }

   public void deniedBy(SystemAdmin admin) {
      if (!state.equals(CLAIMED))
         return;
      if (!this.admin.equals(admin))
         return;
      granted = false;
      state = DENIED;
      notifyUserOfPermissionRequestResult();
   }

   private void notifyUserOfPermissionRequestResult() {
      // TODO Auto-generated method stub

   }

   public void grantedBy(SystemAdmin admin) {
      if (!state.equals(CLAIMED))
         return;
      if (!this.admin.equals(admin))
         return;
      state = GRANTED;
      granted = true;
      notifyUserOfPermissionRequestResult();
   }
}
