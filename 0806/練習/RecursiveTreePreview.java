import java.util.*;

public class RecursiveTreePreview {

    static class Folder {
        String name;
        List<Folder> subfolders = new ArrayList<>();
        int files;

        Folder(String name, int files) {
            this.name = name;
            this.files = files;
        }
    }

    public static int countFiles(Folder folder) {
        int total = folder.files;
        for (Folder sub : folder.subfolders)
            total += countFiles(sub);
        return total;
    }

    public static void printMenu(String[] menu, int level) {
        for (String item : menu) {
            System.out.println("  ".repeat(level) + "- " + item);

        }
    }

    public static List<Integer> flatten(List<Object> nested) {
        List<Integer> flat = new ArrayList<>();
        for (Object item : nested) {
            if (item instanceof List) flat.addAll(flatten((List<Object>) item));
            else flat.add((Integer) item);
        }
        return flat;
    }

    public static int maxDepth(Object nested) {
        if (!(nested instanceof List)) return 0;
        int depth = 0;
        for (Object obj : (List<?>) nested)
            depth = Math.max(depth, maxDepth(obj));
        return depth + 1;
    }
}
