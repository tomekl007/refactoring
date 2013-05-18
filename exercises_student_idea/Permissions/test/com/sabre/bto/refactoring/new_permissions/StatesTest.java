package com.sabre.bto.refactoring.new_permissions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class StatesTest {
   
   private SystemPermission permission;

   private static final SystemAdmin ADMIN = new SystemAdmin();
   private static final SystemAdmin DIFFERENT_ADMIN = new SystemAdmin();
   
   private static final SystemProfile NON_UNIX_PROFILE = new SystemProfile(false);
   private static final SystemProfile UNIX_PROFILE = new SystemProfile(true);
   
   private static final SystemUser USER = new SystemUser();
   
   @Before
   public void setUp() {

   }

   @After
   public void tearDown() {
      permission = null;
   }
   
   @Test
   public void test_NonUnix_WhenCreated()
   {
      permission = new SystemPermission(USER, NON_UNIX_PROFILE);
      
      Assert.assertEquals("requested", SystemPermission.REQUESTED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }

   @Test
   public void test_Unix_WhenCreated()
   {
      permission = new SystemPermission(USER, UNIX_PROFILE);
      
      Assert.assertEquals("requested", SystemPermission.REQUESTED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }
   
   @Test
   public void test_NonUnix_WhenCreated_And_Claimed()
   {
      permission = new SystemPermission(USER, NON_UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      Assert.assertEquals("claimed", SystemPermission.CLAIMED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }

   @Test
   public void test_Unix_WhenCreated_And_Claimed()
   {
      permission = new SystemPermission(USER, UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      Assert.assertEquals("claimed", SystemPermission.CLAIMED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }
   
   @Test
   public void test_NonUnix_WhenCreated_And_Granted()
   {
      permission = new SystemPermission(USER, NON_UNIX_PROFILE);
      
      permission.grantedBy(ADMIN);
      Assert.assertEquals("requested", SystemPermission.REQUESTED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }
   
   @Test
   public void test_Unix_WhenCreated_And_Granted()
   {
      permission = new SystemPermission(USER, UNIX_PROFILE);
      
      permission.grantedBy(ADMIN);
      Assert.assertEquals("requested", SystemPermission.REQUESTED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }
   
   @Test
   public void test_NonUnix_WhenCreated_And_Denied()
   {
      permission = new SystemPermission(USER, NON_UNIX_PROFILE);
      
      permission.deniedBy(ADMIN);
      Assert.assertEquals("requested", SystemPermission.REQUESTED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }
   
   @Test
   public void test_Unix_WhenCreated_And_Denied()
   {
      permission = new SystemPermission(USER, UNIX_PROFILE);
      
      permission.deniedBy(ADMIN);
      Assert.assertEquals("requested", SystemPermission.REQUESTED, permission.getState());
      Assert.assertEquals("not granted", false, permission.isGranted());
   }
   
   @Test
   public void test_NonUnix_WhenCreated_And_Claimed_And_Granted()
   {
      permission = new SystemPermission(USER, NON_UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      permission.grantedBy(ADMIN);
      Assert.assertEquals("granted", SystemPermission.GRANTED, permission.getState());
      Assert.assertEquals("granted", true, permission.isGranted());
   }

   @Test
   public void test_Unix_WhenCreated_And_Claimed_And_Granted_And_Claimed_And_Granted()
   {
      permission = new SystemPermission(USER, UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      permission.grantedBy(ADMIN);
      Assert.assertEquals("unix requested", SystemPermission.UNIX_REQUESTED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
      permission.claimedBy(ADMIN);
      Assert.assertEquals("unix claimed", SystemPermission.UNIX_CLAIMED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
      permission.grantedBy(ADMIN);
      Assert.assertEquals("unix requested", SystemPermission.GRANTED, permission.getState());
      Assert.assertEquals("granted", true, permission.isGranted());
   }
   
   @Test
   public void test_NonUnix_When_Created_And_Claimed_And_Denied()
   {
      permission = new SystemPermission(USER, NON_UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      permission.deniedBy(ADMIN);
      Assert.assertEquals("granted", SystemPermission.DENIED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
   }
   
   @Test
   public void test_Unix_When_Created_And_Claimed_And_Denied()
   {
      permission = new SystemPermission(USER, UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      permission.deniedBy(ADMIN);
      Assert.assertEquals("granted", SystemPermission.DENIED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
   }
   
   @Test
   public void test_NonUnix_WhenCreated_And_Claimed_And_Granted_By_Different_Admins()
   {
      permission = new SystemPermission(USER, NON_UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      permission.grantedBy(DIFFERENT_ADMIN);
      Assert.assertEquals("claimed", SystemPermission.CLAIMED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
   }

   @Test
   public void test_Unix_WhenCreated_And_Claimed_And_Granted_By_Different_Admins()
   {
      permission = new SystemPermission(USER, UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      permission.grantedBy(DIFFERENT_ADMIN);
      Assert.assertEquals("claimed", SystemPermission.CLAIMED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
   }

   
   @Test
   public void test_NonUnix_When_Created_And_Claimed_And_Denied_By_Different_Admins()
   {
      permission = new SystemPermission(USER, NON_UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      permission.deniedBy(DIFFERENT_ADMIN);
      Assert.assertEquals("claimed", SystemPermission.CLAIMED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
   }
   
   @Test
   public void test_Unix_When_Created_And_Claimed_And_Denied_By_Different_Admins()
   {
      permission = new SystemPermission(USER, UNIX_PROFILE);
      
      permission.claimedBy(ADMIN);
      permission.deniedBy(DIFFERENT_ADMIN);
      Assert.assertEquals("claimed", SystemPermission.CLAIMED, permission.getState());
      Assert.assertEquals("granted", false, permission.isGranted());
   }
}
