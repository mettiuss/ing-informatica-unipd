public class Padlock {
    private boolean open;
    private String password;

    public Padlock() {
        open = true;
        password = "0000";
    }

    public Padlock(String pw) {
        open = true;
        password = pw;
    }

    public void close() {
        open = false;
    }

    public void open(String pw) {
        if (pw.equals(password))
            open = true;
    }

    public void setPasswd(String oldPw, String newPw) {
        if (oldPw.equals(password))
            password = newPw;
    }

    public String toString() {
        return "Lock " + (open ? "aperto" : "chiuso");
    }
}