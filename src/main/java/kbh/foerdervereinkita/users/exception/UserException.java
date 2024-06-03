package kbh.foerdervereinkita.users.exception;

public class UserException extends Exception {

  private UserException(String msg) {
    super(msg);
  }

  public static UserException alreadyExists(String username) {
    return new UserException(String.format("User '%s' already exists.", username));
  }
}
