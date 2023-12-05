package com.Maxim.command_user_handler;

import com.Maxim.controller.LabelController;
import com.Maxim.controller.PostController;
import com.Maxim.controller.WriterController;

import java.util.Scanner;

public class UserHandler {

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
                writerController.save();
                break;
            case "get all writers":
                writerController.getAllWriter();
                break;
            case "get writer by id":
                writerController.getWriterById();
                break;
            case "update writer by id":
                writerController.updateWriterById();
                break;
            case "delete writer by id":
                writerController.deleteWriterById();
                break;
            case "get all labels":
                labelController.getAllLabels();
                break;
            case "get label by id":
                labelController.getLabelById();
                break;
            case "create label":
                labelController.save();
                break;
            case "update label by id":
                labelController.updateLabelById();
                break;
            case "delete label by id":
                labelController.deleteLabelById();
                break;
            case "get all posts":
                postController.getAllPosts();
                break;
            case "get post by id":
                postController.getPostById();
                break;
            case "create post":
                postController.save();
                break;
            case "update post by id":
                postController.updatePostById();
                break;
            case "delete post by id":
                postController.deleteWriterById();
                break;

            case "exit":
                break;
            default:
                System.out.println("Проверьте правильность введенной команды");
        }

    }

}
