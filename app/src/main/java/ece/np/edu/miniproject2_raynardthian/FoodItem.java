package ece.np.edu.miniproject2_raynardthian;

public class FoodItem {
    private int id;
    private String FoodName;
    private String FoodDescription;
    private Float FoodCost;
    private String FoodPicture;

    public FoodItem(int id, String foodName, String foodDescription, Float foodCost, String foodPicture) {
        this.id = id;
        FoodName = foodName;
        FoodDescription = foodDescription;
        FoodCost = foodCost;
        FoodPicture = foodPicture;
    }

    public FoodItem( ) {
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodDescription() {
        return FoodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        FoodDescription = foodDescription;
    }

    public Float getFoodCost() {
        return FoodCost;
    }

    public void setFoodCost(Float foodCost) {
        FoodCost = foodCost;
    }

    public String getFoodPicture() {
        return FoodPicture;
    }

    public void setFoodPicture(String foodPicture) {
        FoodPicture = foodPicture;
    }

    // To string


    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", FoodName='" + FoodName + '\'' +
                ", FoodDescription='" + FoodDescription + '\'' +
                ", FoodCost=" + FoodCost +
                ", FoodPicture=" + FoodPicture +
                '}';
    }

    public String FoodNameToString(){
        return FoodName;
    }

    public String FoodDescriptionToString(){
        return FoodDescription;
    }

    public Float FoodCostToString(){
        return FoodCost;
    }

    public String FoodPictureToString(){
        return FoodPicture;
    }
}
