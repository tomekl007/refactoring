package com.sabre.bto.refactoring.permissions;

import com.sabre.bto.refactoring.permissions.SystemAdmin;
import com.sabre.bto.refactoring.permissions.SystemPermission;
import com.sabre.bto.refactoring.permissions.SystemProfile;
import com.sabre.bto.refactoring.permissions.SystemUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class StatesTest {
   
   private SystemPermission permission;

   private static final SystemAdmin ADMIN = new SystemAdmin();
   private static final SystemAdmin DIFFERENT_ADMIN = new SystemAdmin();
   
   private static final SystemProfile PROFILE = new SystemProfile();
   private static final SystemUser USER = new SystemUser();
   
   @Before
   public void setUp() {
     permission = new SystemPermission(USER, PROFILE);
   }

   @After
   public void tearDown() {
      permission = null;
   }
   
   @Test
   public void test_WhenCreated()
   {
      Assert.assertEquals("requested", SystemPermission.REQUESTED, permission.getState());
      Assert.assertFalse("not granted", permission.isGranted());
   }

   @Test
   public void test_WhenCreated_And_Claimed()
   {
      permission.claimedBy(ADMIN);
      Assert.assertEquals("claimed", SystemPermission.CLAIMED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }

   @Test
   public void test_WhenCreated_And_Granted()
   {
      permission.grantedBy(ADMIN);
      Assert.assertEquals("requested", SystemPermission.REQUESTED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }
   
   @Test
   public void test_WhenCreated_And_Denied()
   {
      permission.deniedBy(ADMIN);
      Assert.assertEquals("requested", SystemPermission.REQUESTED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }
   
   @Test
   public void test_WhenCreated_And_Claimed_And_Granted()
   {
      permission.claimedBy(ADMIN);
      permission.grantedBy(ADMIN);
      Assert.assertEquals("granted", SystemPermission.GRANTED, permission.getState());
      Assert.assertEquals("granted", true, permission.isGranted());
   }

   @Test
   public void test_When_Created_And_Claimed_And_Denied()
   {
      permission.claimedBy(ADMIN);
      permission.deniedBy(ADMIN);
      Assert.assertEquals("denied", SystemPermission.DENIED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
   }
   
   
   @Test
   public void test_WhenCreated_And_Claimed_And_Granted_By_Different_Admins()
   {
      permission.claimedBy(ADMIN);
      permission.grantedBy(DIFFERENT_ADMIN);
      Assert.assertEquals("claimed", SystemPermission.CLAIMED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
   }

   @Test
   public void test_When_Created_And_Claimed_And_Denied_By_Different_Admins()
   {
      permission.claimedBy(ADMIN);
      permission.deniedBy(DIFFERENT_ADMIN);
      Assert.assertEquals("claimed", SystemPermission.CLAIMED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
   }
}
