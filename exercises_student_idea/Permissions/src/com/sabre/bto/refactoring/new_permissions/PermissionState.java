package com.sabre.bto.refactoring.new_permissions;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 6:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class PermissionState {

    final String permission;

    public PermissionState(String name) {
        this.permission = name;
    }

    public String getPermission() {
        return permission.toString();
    }

    @Override
    public String toString() {
        System.out.println("return toString:" + permission);
        return permission;
    }

    @Override
    public boolean equals(Object obj) {
        PermissionState other = (PermissionState) obj;
        return this.permission.equals(other.permission);
    }

    public void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
       if (!systemPermission.getState().equals(new PermissionState("REQUESTED")) && !systemPermission.getState().equals(new PermissionState("UNIX_REQUESTED")))
          return;
        systemPermission.willBeHandledBy(admin);
        if (systemPermission.getState().equals(new PermissionState("REQUESTED")))     {
          systemPermission.setState(new PermissionState("CLAIMED"));
            System.out.println("------> in iF");
        }
        else if (systemPermission.getState().equals(new PermissionState("UNIX_REQUESTED")))
          systemPermission.setState(new PermissionState("UNIX_CLAIMED"));
    }

    public void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {
       if (!systemPermission.getState().equals(new PermissionState("CLAIMED")) && !systemPermission.getState().equals(new PermissionState("UNIX_CLAIMED")))
          return;
        if (!systemPermission.admin.equals(admin))
          return;
        systemPermission.granted = false;
        systemPermission.unixPermissionGranted = false;
        systemPermission.setState(new PermissionState("DENIED"));
        systemPermission.notifyUserOfPermissionRequestResult();
    }

    public void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {
       if (!systemPermission.getState().equals(new PermissionState("CLAIMED")) && !systemPermission.getState().equals(new PermissionState("UNIX_CLAIMED")))
          return;
        if (!systemPermission.admin.equals(admin))
          return;

        if (systemPermission.profile.isUnixPermissionRequired() && systemPermission.getState().equals(new PermissionState("UNIX_CLAIMED")))
          systemPermission.unixPermissionGranted = true;
        else if (systemPermission.profile.isUnixPermissionRequired() &&
          !systemPermission.isUnixPermissionGranted()) {
          systemPermission.setState(new PermissionState("UNIX_REQUESTED"));
          systemPermission.notifyAdminOfPermissionRequest();
          return;
        }
        systemPermission.setState(new PermissionState("GRANTED"));
        systemPermission.granted = true;
        systemPermission.notifyUserOfPermissionRequestResult();
    }
}
