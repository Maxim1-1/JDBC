package com.Maxim.command_user_handler;

import com.Maxim.controller.LabelController;
import com.Maxim.controller.PostController;
import com.Maxim.controller.WriterController;
import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.Writer;
import com.Maxim.view.WriterView;
import com.Maxim.view.LabelView;
import com.Maxim.view.PostView;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UserHandler {

    private static WriterView writerView = new WriterView();
    private static LabelView labelView = new LabelView();
    private static PostView postView = new PostView();

    public static void getCommandConsole() {
        WriterController writerController = new WriterController();
        LabelController labelController = new LabelController();
        PostController postController = new PostController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nВведите команду из списка или воспользуйтесь файлом README.md. " +
                "\nСписок команд: \ncreate writer, get all writers,get writer by id, update writer by id,delete writer by id" +
                "\nget all labels, get label by id, create label, update label by id, delete label by id " +
                "\nget all posts, get post by id, create post, update post by id, delete post by id");

        String command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "create writer":
                HashMap<String, String> dataFromConsole = writerView.create();
                writerController.save(dataFromConsole);
                break;
            case "get all writers":
                writerView.getAllWriters(writerController.getAllWriter());
                break;
            case "get writer by id":
                Integer writerId = writerView.getIdFromConsole("Введите id писателя");
                Writer writer = writerController.getWriterById(writerId);
                writerView.getWriterById(writer);
                break;
            case "update writer by id":
                Integer updateWriterId = writerView.getIdFromConsole("Введите поста id для обновления");
                HashMap<String, String> updatedData = writerView.updateWriterById();
                writerController.updateWriterById(updateWriterId, updatedData);
                break;
            case "delete writer by id":
                Integer deleteWriterId = writerView.getIdFromConsole("Введите writer id для удаления");
                writerController.deleteWriterById(deleteWriterId);
                break;
            case "get all labels":
                List<Label> labels = labelController.getAllLabels();
                labelView.getAllLabels(labels);
                break;
            case "get label by id":
                Integer labelId = labelView.getIdFromConsole("Введите label id");
                Label label = labelController.getLabelById(labelId);
                labelView.getLabelById(label);
                break;
            case "create label":
                HashMap<String, String> labelDataFromConsole = labelView.create();
                labelController.save(labelDataFromConsole);
                break;
            case "update label by id":
                Integer updateLabelId = labelView.getIdFromConsole("Введите label id для обновления");
                String newNameLabel = labelView.updateLabelById().get("labelName");
                labelController.updateLabelById(updateLabelId,newNameLabel);
                break;
            case "delete label by id":
                Integer deleteLabelId = labelView.getIdFromConsole("Введите label id");
                labelController.deleteLabelById(deleteLabelId);
                break;
            case "get all posts":
                List<Post> posts = postController.getAllPosts();
                postView.getAllPosts(posts);

                break;
            case "get post by id":
                Integer postId = postView.getIdFromConsole("Введите id поста");
                Post post = postController.getPostById(postId);
                postView.getPostById(post);
                break;
            case "create post":
                HashMap<String, String> postDataFromConsole = postView.create();
                postController.savePost(postDataFromConsole);
                break;
            case "update post by id":
                Integer updatePostId = postView.getIdFromConsole("Введите поста id для обновления");
                HashMap<String, String> updatePostdData = postView.updatePostById();
                postController.updatePostById(updatePostId,updatePostdData);
                break;
            case "delete post by id":
                Integer deletePostId = postView.getIdFromConsole("Введите post id для удаления");
                postController.deletePostById(deletePostId);
                break;
            case "exit":
                break;
            default:
                System.out.println("Проверьте правильность введенной команды");
        }

    }

}
