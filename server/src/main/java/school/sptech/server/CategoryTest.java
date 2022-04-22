package school.sptech.server;

import school.sptech.server.model.Category;

public class CategoryTest {

    public static void main(String[] args) {

        ListObj<Category> categoryList = new ListObj(5);
        Category category1 = new Category(1, "Encanamento");
        Category category2 = new Category(2, "Elétrica");
        Category category3 = new Category(3, "Montagem de móveis");
        Category category4 = new Category(4, "Pintura");
        Category category5 = new Category(5, "Limpeza");

        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        categoryList.add(category5);

        System.out.printf("%3s %-14s\n", "ID", "CATEGORIA");
        categoryList.show();

        categoryList.show();
        CsvFile.recordCsvFile(categoryList, "category");
        CsvFile.readCsvFile("category");
    }

}
