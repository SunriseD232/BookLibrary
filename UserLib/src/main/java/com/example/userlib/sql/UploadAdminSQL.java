package com.example.userlib.sql;

public class UploadAdminSQL {
  public static String UploadAdminScript(){
    String script = "INSERT INTO user_impl (is_blocked, password, role, strike, username)\n"
        + "VALUES\n"
        + "    (false,'$2a$10$4PoFaCggbsfCTPa8SUAdb.lLa4olkQRIrddxP80g0SDMSJgV.Uy52','ADMIN',0,'admin')";
  return script;
  }
}
