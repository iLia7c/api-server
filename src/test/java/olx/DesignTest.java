package olx;

import olx.remote.DefectService;
import olx.remote.DefectServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DesignTest {

    @Test
    public void createDesignTest() {
        Car bmwCar = Car.getInstance(Brand.BMW, ModelName.GOLF);
        CarModel bmwModel = CarModel.getInstance(Brand.BMW, ModelName.GOLF);

        Year year2010 = Year.getYear(2010);
        Year year2012 = Year.getYear(2012);

        Defect bwmModelDefect = Defect.getDefaultInstance(bmwModel, Arrays.asList(year2010, year2012));
    }

    @Test
    public void testYearNegative() {
        Exception ex = assertThrows(RuntimeException.class,
                () -> Year.getYear(0),
                "Incorrect year");
    }

    @Test
    public void testUserFriendlyTextRepresentationForCarModelA() {
        CarModel bmwModel = CarModel.getInstance(Brand.BMW, ModelName.GOLF);
        CarModel audiModel = CarModel.getInstance(Brand.AUDI, ModelName.GOLF);

        assertTrue(bmwModel.toString().contains("{Brand: BMW, modelName: GOLF, version: 1}"));
        assertTrue(audiModel.toString().contains("{Brand: AUDI, modelName: GOLF, version: 2}"));
    }

    @Test
    public void testDeveloperFriendlyTextRepresentationA() throws IOException {
        CarModel bmwModel = CarModel.getInstance(Brand.BMW, ModelName.GOLF);

        List<String> lines = Files.readAllLines(Paths.get("carModel.log"));
        assertTrue(lines.get(0).contains("{Brand: BMW, modelName: GOLF, version: 1}"));
    }

    @Test
    public void testDefectServiceA() {
        DefectService service = DefectServiceImpl.getDefaultDefectService();
        CarModel bmwModel = CarModel.getInstance(Brand.BMW, ModelName.GOLF);

        List<Defect> defectList = service.getDefects(bmwModel);
    }

    @Test
    public void testAllDefects() {
        DefectService defectService = DefectServiceImpl.getDefaultDefectService();
        CarService carService = CarService.getInstance(defectService);

        Car bmw = Car.getInstance(Brand.BMW, ModelName.GOLF);
        Car audio = Car.getInstance(Brand.AUDI, ModelName.GOLF);
        List<Car> cars = Arrays.asList(bmw, audio);

        List<Defect> defects = carService.getDefects(cars);

        assertEquals(2, defects.size());
        // more asserts to check each defect
    }

}
