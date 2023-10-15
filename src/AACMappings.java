
import structures.AssociativeArray;
import structures.KeyNotFoundException;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.PrintWriter;
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * AACMappings
 *
 * @author Sam Bigham
 * 
 *         this AACMappings class is where the bulk of the backend is done
 *         maps images into categories so that when clicked it goes into more
 *         images
 *         saves and can add more categories and images
 */
public class AACMappings<K, V> extends AssociativeArray<K, V> {

    public AssociativeArray<String, AACCategory> aamap = new AssociativeArray<>();

    String filena;

    AACCategory currentCategory;
    AACCategory homCategory;

    public AACMappings(String filename) {
        this.filena = filename;

        java.io.PrintWriter pen = new java.io.PrintWriter(System.out, true);

        int incr = 0; // increments when reading a file

        this.homCategory = new AACCategory(""); // default home category
        this.currentCategory = homCategory; // category that can change

        try { // all to read a file and load images accordingly
            File input = new File(filename);
            Scanner filRead = new Scanner(input);
            String substr1;
            String substr2;
            String lastCat = ""; // default val

            while (filRead.hasNextLine()) { // loop to read through all of file
                String data = filRead.nextLine();
                int index = data.indexOf(" ");
                if (!data.contains(">")) { // if it is a category holding images

                    substr1 = data.substring(0, index); // takes everything before space
                    substr2 = data.substring(index, data.length()); // takes everything after space

                    lastCat = substr1; // last known category

                    homCategory.addItem(substr1, substr2); // adds items to the category
                    aamap.set(substr1, new AACCategory(substr2));
                } else if (data.contains(">")) { // if subcategory and therefore an image and text pair

                    substr1 = data.substring(1, index); // ignores the >
                    substr2 = data.substring(index, data.length());

                    try {
                        currentCategory = aamap.get(lastCat); // changes to new category and sets values
                        currentCategory.addItem(substr1, substr2); // adds image and text
                        // currentCategory.CatName = "food";
                    } catch (KeyNotFoundException e) {
                        System.err.println("ERROR");
                        e.printStackTrace();
                    }
                }
                incr++;
                currentCategory = homCategory; // goes back to home
            }
        } catch (FileNotFoundException e) {
            pen.println("ERROR IN READING FILE");
            e.printStackTrace();
        }
    }

    /*
     * returns a String[] of all the images in the current category
     */
    public String[] getImageLocs() {
        return currentCategory.getImages();
    } // getImageLocs()

    /*
     * getText
     * changes the category to the action command if the image is a category
     * otherwise just returns actionCommand
     */
    public String getText(String actionCommand) {
        if (currentCategory.CatName.equals("")) {
            try {
                currentCategory = aamap.get(actionCommand);
                return currentCategory.CatName;
            } catch (Exception e) {
                new KeyNotFoundException("error");
                return "error";
            }
        } else {
            return currentCategory.getText​(actionCommand);
        }
    }

    /*
     * gets the current category
     */
    public String getCurrentCategory() {
        return currentCategory.getCategory();
    }

    /*
     * checks whether the image is a category or not
     */
    public boolean isCategory​(String imageLoc) {
        return aamap.hasKey(imageLoc);
    }

    /*
     * resets back to home screen
     */
    public void reset() {
        currentCategory = homCategory;
    }

    /*
     * writes all changes made in the array to the given filename
     */
    public void writeToFile(String filename) {
        AACCategory curCat = currentCategory;
        String[] imagearr = new String[homCategory.aacat.size]; // sets up String[] to be used for writing onto a file
        String[] catarr = new String[homCategory.aacat.size];
        try {
            PrintWriter pen = new PrintWriter(filename); //pen will print to a file
            currentCategory = homCategory; //goes to home category before starting
            imagearr = homCategory.aacat.getImageLocs(); //will become all image loc
            catarr = homCategory.aacat.getCategories(); //will become all categories

            for (int i = 0; i < aamap.size; i++) { //for how many categories are in aamap
                pen.println(imagearr[i] + " " + catarr[i]);//prints all image categories pairs

                if (homCategory.aacat.size != 0) { //if categories have other images
                    try {
                        currentCategory = aamap.get(imagearr[i]);
                        String[] innerImages = currentCategory.aacat.getImageLocs(); //becomes all inner images
                        String[] innerWords = currentCategory.aacat.getCategories(); //becomes all inner text

                        for (int j = 0; j < currentCategory.aacat.size; j++) {
                            System.out.println("innerImages:" +innerImages[j]);
                            System.out.println("innerWords:" + innerWords[j]);
                            pen.println(">" + innerImages[j] + " " + innerWords[j]);// prints everything out onto file
                        }
                    } catch (KeyNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } // for

            pen.flush(); // prints items to file
            pen.close();

        } catch (FileNotFoundException e) {
            System.err.println("CAN'T FIND FILE");
            e.printStackTrace();
        }

        currentCategory = curCat; //returns back to starting category
    }

    /*
     * adds new imageLoc and text onto either categories or subcategories
     */
    public void add(String imageLoc, String text) {
        if (currentCategory.getCategory().equals("")) {
            homCategory.addItem(imageLoc, text);
            this.aamap.set(imageLoc, new AACCategory(text));
        } else {

            this.currentCategory.addItem(imageLoc, text);
        }
    }

}
