package iwiw;


import iwiw.model.Note;
import iwiw.model.User;
import iwiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IwiwApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(IwiwApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder().name("teszt1").userName("teszt1").password("teszt1").build();
        User user2 = User.builder().name("teszt2").userName("teszt2").password("teszt2").build();

        user2.addNote(new Note());

        userRepository.save(user2);
        user1.addFriend(user2);
        userRepository.save(user1);
    }
}