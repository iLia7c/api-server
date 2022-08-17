package olx;

import java.util.List;

public class Defect {
    // can't be empty as well
    private CarModel model;

    // can't be empty
    private List<Year> yearsOfIssue;

    // must be unique per Defect
    private String defectCode;

    private Defect(CarModel carModel, List<Year> yearsOfIssue, String defectCode) {
        model = carModel;
        this.yearsOfIssue = yearsOfIssue;
        this.defectCode = defectCode;
    }


    public static Defect getDefaultInstance(CarModel model, List<Year> years) {
        String defaultDetectCode = "345F";
        return new Defect(model, years, defaultDetectCode);
    }


}
