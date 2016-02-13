package com.sqldexter.organization;

/**
 * Created by HOME on 12-02-2016.
 */
public class InitializeServer {
   public InitializeServer(){

   }

    public static void main(String[] args) {
        new Controller(new DataAccess());
    }
}
