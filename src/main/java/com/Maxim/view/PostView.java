package com.Maxim.view;

import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;

import java.util.*;

public class PostView extends BaseView{

    private HashMap<String,String > userDataFromConsole = new HashMap();
    private Scanner scanner = new Scanner(System.in);

    public HashMap<String, String> create() {

        System.out.println("Введите заголовок поста :");
        String labelName = scanner.nextLine();
        userDataFromConsole.put("labelPost",labelName);

        System.out.println("Введите текст поста:");
        String postText = scanner.nextLine();
        userDataFromConsole.put("postText",postText);

        System.out.println("Введите id автора поста или введите команду create, для создания нового автора:");

        if (scanner.hasNextInt()) {
            int writerId = scanner.nextInt();
            userDataFromConsole.put("writerId", String.valueOf(writerId));
        } else {
            WriterView writerView = new WriterView();

            HashMap<String,String> newWriter= writerView.create();
            userDataFromConsole.put("firstName",newWriter.get("firstName"));
            userDataFromConsole.put("lastName",newWriter.get("lastName"));
        }

        return userDataFromConsole;

    }

    public HashMap<String,String> updatePostById(){

        System.out.println("Выберите из списка один или несколько столбцов(через запятую) для изменения: content, created, updated, writerId, status");
        String[] params = scanner.nextLine().split(",");
        for (String param : params) {
            System.out.printf("Новое значение для %s%n", param);
            userDataFromConsole.put(param, scanner.nextLine());
        }
        return userDataFromConsole;

    }

    public void getPostById(Post post){
//        TODO доделать выподет всех заголовком
        System.out.print(String.format("id = %s, created = %s, updated = %s, label = %s, content = %s",
                post.getId(), post.getCreated(), post.getUpdated(), post.getLabels(),post.getContent()));

    }

    public void getAllPosts(List<Post> posts){
        for (Post post:posts) {
            post.getLabels().stream().forEach(label -> System.out.print(label.getName()+"\n"));

            System.out.print(String.format("id: %s, label: %s, content: %s, created: %s, updated: %s, status: %s \n",
                    post.getId(),post.getLabels(),post.getContent(),post.getCreated(), post.getUpdated(),post.getPostStatus()));

        }
    }


}
