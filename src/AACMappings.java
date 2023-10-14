

import structures.AssociativeArray;
import structures.KeyNotFoundException;

public class AACMappings<K,V> extends AssociativeArray<K,V> {

    public AssociativeArray<String, AACCategory> aamap = new AssociativeArray<>();

    String filena;

    AACCategory currentCategory;
    AACCategory homCategory;


    public AACMappings(String filename) {
        this.filena = filename;
    
        this.homCategory = new AACCategory("");
        this.currentCategory = homCategory;


        // homCategory.aacat.set("img/food/plate.png", "food");
        // homCategory.aacat.set("img/clothing/hanger.png", "clothing");
        homCategory.addItem("img/food/plate.png", "food");
        aamap.set("img/food/plate.png", new AACCategory("food"));

        try {
            currentCategory = aamap.get("img/food/plate.png");
            System.err.println("current cat thing: " + currentCategory.getCategory());
           // currentCategory.CatName = "food";
        } catch (KeyNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        currentCategory.addItem("img/food/icons8-apple-96.png", "apple");
        currentCategory.addItem("img/food/icons8-cookies-96.png", "cookies");

        currentCategory = homCategory;
        System.err.println("current category: " + currentCategory.getCategory());

        //>img/food/icons8-apple-96.png apple
        //>img/food/icons8-cookies-96.png cookies

        
        // aamap.set("watermelon", );
        // aamap.set("apple", );

        //homCategory.aacat.set("img/food/plate.png", currentCategory);
    }

    public String[] getImageLocs() {

        //return currentCategory.getImages(); // STUB

        //return this.currentCategory.getImages();
        System.out.println("-------------------====----"+currentCategory.getCategory());

        
        return currentCategory.getImages();
    //     if (currentCategory.getCategory() == ""){   
            
            

    //         System.out.println("cdcxzbcvxznbm,cvxzbmncvbmncxvzbmncvxzmbn,zcx");
      
    //    return this.aamap.getKeys();
    //     }else{
    //         System.out.println("ELSE CALL ACTIVATIED");
    //         //return currentCategory.aacat.getKeys();
    //         return currentCategory.getImages();
    //     }


      } // getImageLocs()

    public String getText(String actionCommand) {

        // System.out.println(actionCommand);
         System.out.println(currentCategory.CatName);
        // System.out.println(homCategory.CatName);
        if (currentCategory.CatName.equals("")){
            try {
    
            System.out.println("he;lpppp"+currentCategory.CatName);

             //currentCategory = new AACCategory("currentCat");
             currentCategory = aamap.get(actionCommand);
             System.err.println("category: " +currentCategory.CatName);
              


             
            return "food";
            } catch(Exception e){
               new KeyNotFoundException("error");
               return "error";
                
            } 
        } else {
            System.out.println("Else in getText called");
            // System.out.println(actionCommand);
            // System.out.println(currentCategory);
         
            return "foo";
            //return currentCategory.getText​(actionCommand);
        }
        
       // return "sdfklsaf";
    }

    public String getCurrentCategory() {
        
        return currentCategory.toString(); // STUB
    }


    public boolean isCategory​(String imageLoc) {
    
        //eturn true;
        System.out.println(aamap);
        System.out.println(aamap.hasKey(imageLoc));
        return aamap.hasKey(imageLoc);
    }

    public void reset() {
        this.aamap = new AssociativeArray<String, AACCategory>();
        
    }

    public void writeToFile(String filename) {
        
    }

    public void add(String imageLoc, String text) {
       // this..addItem​(imageLoc, text);

    if(currentCategory.getCategory().equals("")){
        homCategory.addItem(imageLoc, text);
        this.aamap.set(imageLoc, new AACCategory(text));
    }else{

       this.currentCategory.addItem(imageLoc, text);
    }
    }

}
