package com.Maxim.view;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.Writer;
import com.Maxim.service.PostService;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WriterView extends BaseView {

    private HashMap<String, String> userDataFromConsole = new HashMap();
    private Scanner scanner = new Scanner(System.in);

    public HashMap<String, String> create() {
        String firstName;
        String lastName;


        System.out.println("Введите имя:");
        firstName = scanner.nextLine();
        userDataFromConsole.put("firstName", firstName);


        System.out.println("Введите фамилию:");
        lastName = scanner.nextLine();
        userDataFromConsole.put("lastName", lastName);

        return userDataFromConsole;

    }

    public void getWriterById(Writer writer) {
        PostService postService = new PostService();

        List<Post> posts = postService.getAllPosts();

        System.out.print(String.format("id = %s, first name = %s, last name = %s", writer.getId(), writer.getFirstName(), writer.getLastName()));
        System.out.print("\nposts: \n");
        for (Post post: posts) {
            if (post.getWriterId() == writer.getId()) {
                System.out.print(String.format("id: %s, content: %s, created: %s\n",
                        post.getId(), post.getContent(), post.getCreated()));
            }
        }
    }

    public void getAllWriters(List<Writer> writers) {
        PostService postService = new PostService();
        List<Post> posts = postService.getAllPosts();

        for (Writer writer : writers) {
            System.out.print(String.format("id: %s, firstName: %s, lastName : %s, \n",
                    writer.getId(), writer.getFirstName(), writer.getLastName()));

            for (Post post: posts) {
                if (post.getWriterId() == writer.getId()) {
                    System.out.print("posts: \n");
                    System.out.print(String.format("id: %s, content: %s, created: %s\n",
                            post.getId(), post.getContent(), post.getCreated()));
                }

            }

            System.out.print("\n");

        }
    }

    public HashMap<String,String> updateWriterById(){

        System.out.println("Выберите из списка один или несколько столбцов(через запятую) для изменения: firstName, lastName");
        String[] params = scanner.nextLine().split(",");
        for (String param : params) {
            System.out.printf("Новое значение для %s%n", param);
            userDataFromConsole.put(param, scanner.nextLine());
        }
        return userDataFromConsole;

    }

}
