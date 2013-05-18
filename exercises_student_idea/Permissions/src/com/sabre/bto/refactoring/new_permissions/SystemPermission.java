package com.sabre.bto.refactoring.new_permissions;

public class SystemPermission {


    protected SystemProfile profile;
   protected SystemUser requestor;
    protected SystemAdmin admin;
    protected boolean granted;
    protected boolean unixPermissionGranted;
   
   PermissionState state;

    public void setState(PermissionState permissionState) {
        System.out.println("setState to  :" + permissionState);
        this.state = permissionState;
    }

   // private PermissionState permissionState;
   
   public boolean isUnixPermissionGranted() {
      return unixPermissionGranted;
   }

   void setUnixPermissionGranted(boolean unixPermissionGranted) {
      this.unixPermissionGranted = unixPermissionGranted;
   }

   public boolean isGranted() {
      return granted;
   }



   public PermissionState getState() {
      return state;
   }

//   public void setState(String state) {
//      this.state = state;
//   }

   public SystemPermission(SystemUser requestor, SystemProfile profile) {
      this.requestor = requestor;
      this.profile = profile;
      setState(new PermissionState("REQUESTED"));
      granted = false;
      unixPermissionGranted = false;
      notifyAdminOfPermissionRequest();
   }

   protected void notifyAdminOfPermissionRequest() {
      // TODO Auto-generated method stub
   }

    protected void willBeHandledBy(SystemAdmin admin) {
      this.admin = admin;
      
   }

    protected void notifyUserOfPermissionRequestResult() {
      // TODO Auto-generated method stub

   }

}
