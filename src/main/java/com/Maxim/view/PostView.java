package com.Maxim.view;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.repository.jdbc.JDBCPostLabelRepository;

import java.util.*;

public class PostView extends BaseView{

    private HashMap<String,String > userDataFromConsole = new HashMap();
    private Scanner scanner = new Scanner(System.in);

    public HashMap<String, String> create() {

        System.out.println("Введите тег поста :");
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
        JDBCPostLabelRepository jdbcPostLabelRepository = new JDBCPostLabelRepository();

        List<Label> labels = jdbcPostLabelRepository.getPostsById(post.getId());
        post.setLabels(labels);

        System.out.print(String.format("id = %s, created = %s, updated = %s, content = %s\n",
                post.getId(), post.getCreated(), post.getUpdated(),post.getContent()));
        System.out.print("Теги поста: \n");
        for (Label labenName: post.getLabels()) {
            System.out.print(labenName.getName()+"\n");
        }

    }

    public void getAllPosts(List<Post> posts){
        for (Post post:posts) {
            post.getLabels().stream().forEach(label -> System.out.print(label.getName()+"\n"));

            System.out.print(String.format("id: %s,  content: %s, created: %s, updated: %s, status: %s \n",
                    post.getId(),post.getContent(),post.getCreated(), post.getUpdated(),post.getPostStatus()));
            System.out.print("Теги поста: \n");
            for (Label labenName: post.getLabels()) {
                System.out.print(labenName.getName()+"\n");
            }
        }
    }


}
