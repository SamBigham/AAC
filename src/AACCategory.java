import structures.AssociativeArray;
import structures.KeyNotFoundException;


public class AACCategory{

    public AssociativeArray<String, String> aacat;


   // AACMappings<String, AACCategory> mapref;

    //AACCategory homcat = AACMappings.homCategory;

    String CatName;
    public AACCategory(String name) {

        aacat = new AssociativeArray<String, String>();

    //   aacat.set("img/food/icons8-french-fries-96.png", "french fries");
    //   aacat.set("img/food/icons8-watermelon-96.png", "watermelon");
      this.CatName = name;
       // aacat.set("img/clothing/cap.png", "ballcap");
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
        System.out.println("GETTING IMAGES>>>>");
         String[] strarr = new String[20];
         strarr = aacat.getKeys();

        // System.out.println(homcat.aacat);
           // System.out.println(aacat.get("img/food/plate.png"));
        //  System.out.println(aacat.size);

        return strarr;
    }

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
     *  checks wether the array has an image
     */
    public boolean hasImage​(String imageLoc){
        return this.aacat.hasKey(imageLoc);
    }

    /*
     * adds an image location and text pair to an associative array
     */
    public void addItem(String imageLoc, String text) {
        this.aacat.set(imageLoc, text);
    }

    
}
