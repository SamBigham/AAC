import structures.AssociativeArray;
import structures.KeyNotFoundException;

/**
 * AACategory
 *
 * @author Sam Bigham
 * 
 *         This class contains an associative holding two strings.
 *         This can be used to hold an imageLoc and the text of the image
 *         this will then be read
 */
public class AACCategory {

    public AssociativeArray<String, String> aacat;
    String CatName;

    /*
     * Constructer
     */
    public AACCategory(String name) {

        aacat = new AssociativeArray<String, String>(); //sets of AA to hold strings

        this.CatName = name; //name of category
    }

    /*
     * Returns the name of the category
     */
    public String getCategory() {
        return this.CatName;
    }

    /*
     * returns an array of all images in the category
     */
    public String[] getImages() {
        return aacat.getKeys();
    }

    /*
     *  gets the text associated with an image location
     */
    public String getText​(java.lang.String imageLoc) {

        try {
            String str;
            str = aacat.get(imageLoc);
            return str;
        } catch (KeyNotFoundException e) {
            e.printStackTrace();
            return "error";
        }

    }

    /*
     * checks wether the array has an image
     */
    public boolean hasImage​(String imageLoc) {
        return this.aacat.hasKey(imageLoc);
    }

    /*
     * adds an image location and text pair to an associative array
     */
    public void addItem(String imageLoc, String text) {
        this.aacat.set(imageLoc, text);
    }

}
