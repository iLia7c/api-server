package olx;

import olx.remote.DefectService;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private DefectService defectService;

    private CarService(DefectService defectService) {
        this.defectService = defectService;
    }

    // TODO implement narrowing rom cars to carModels
    public List<Defect> getDefects(List<Car> cars) {
        if (cars == null) {
            return new ArrayList<>();
        }

        List<Defect> defectList = new ArrayList<>();
        //List<CarModel> carModels = getModels(cars);
        for (Car car : cars) {
            defectList.addAll(defectService.getDefects(car));
        }

        return defectList;
    }

    public static CarService getInstance(DefectService service) {
        return new CarService(service);
    }
}

