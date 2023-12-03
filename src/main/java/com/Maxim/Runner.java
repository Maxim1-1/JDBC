package com.Maxim;

public class Runner {
    public static void main(String[] args) {
//        ResultSet resultSet = stmt.executeQuery(s);

        LiquibaseRunner liquibase = new LiquibaseRunner();
        liquibase.run();

//        UserHandler.getCommandConsole();

//        Post post = new Post();
//
//        HashMap<String, String> updatedData = new HashMap();
//        updatedData.put("content", "dffssfdsf");
//        updatedData.put("created", "dffssfdsf");
//
//        updatedData.forEach((key, value) -> {
//            switch (key) {
//                case "content":
//                    post.setContent(updatedData.get("content"));
//                    break;
//                case "created":
//                    post.setCreated(updatedData.get("content"));
//                    break;
//                case "updated":
//                    post.setUpdated(updatedData.get("content"));
//                    break;
//            }
//        });

//        System.out.print(post.toString());

//        CrudOperation crudOperation = new CrudOperation();
//        crudOperation.insert("task2_2.label", new String[]{"id","name","postId"}, new String[]{"45666","еуыеfdgf","5"});
//        crudOperation.update("task2_2.label", "set name='max'","where id=3");
//        crudOperation.delete("task2_2.label","where id=3");


//        ResultSet set = null;
//        try {
//            set = crudOperation.read("*", "task2_2.label", "");
//            while (set.next()) {
//                int id = set.getInt("id");
//                String str = set.getString("name");
//                int id2 = set.getInt("postId");
//
//                System.out.print(id + " " + str + " " + id2 + "\n");
//            }
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//


    }
}