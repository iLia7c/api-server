package olx.remote;

import olx.CarModel;
import olx.Defect;
import olx.Year;

import java.util.Arrays;
import java.util.List;

public class DefectServiceImpl implements DefectService {


    private DefectServiceImpl() {

    }

    @Override
    public List<Defect> getDefects(CarModel carModel) {
        Year year2010 = Year.getYear(2010);
        Year year2012 = Year.getYear(2012);

        Defect defaultDefect = Defect.getDefaultInstance(carModel, Arrays.asList(year2010, year2012));

        return Arrays.asList(defaultDefect);
    }

    public static DefectService getDefaultDefectService() {
        return new DefectServiceImpl();
    }
}
