package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("Model 1", 1);
      Car car2 = new Car("Model 2", 2);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }
      User carOwner1 = userService.getCarOwner("Model 1", 1);
      User carOwner2 = userService.getCarOwner("Model 2", 2);
      System.out.println("Owner of first car is " + carOwner1.getLastName() + ", " + carOwner1.getFirstName() + ", email: " + carOwner1.getEmail());
      System.out.println("Owner of second car is " + carOwner2.getLastName() + ", " + carOwner2.getFirstName() + ", email: " + carOwner2.getEmail());
      context.close();
   }
}
