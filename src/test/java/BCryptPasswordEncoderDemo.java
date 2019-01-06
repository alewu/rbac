import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author alewu
 * @date 2018/6/5 20:46
 */
public class BCryptPasswordEncoderDemo {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("admin"));
    }
}
