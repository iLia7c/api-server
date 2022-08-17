package olx.remote;

import olx.CarModel;
import olx.Defect;

import java.util.List;

public interface DefectService {
    List<Defect> getDefects(CarModel carModel);
}
