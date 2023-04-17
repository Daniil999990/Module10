import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

public class Main {
    public static List<User> readUsersFromFile(String filePath) throws IOException {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // пропускаємо перший рядок
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                User user = new User(name, age);
                users.add(user);
            }
        }

        return users;
    }

    public static void writeUsersToJsonFile(List<User> users, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, writer);
        }
    }

    public static void main(String[] args) {
        String inputFile = "file.txt";
        String outputFile = "user.json";

        try {
            List<User> users = readUsersFromFile(inputFile);
            writeUsersToJsonFile(users, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
