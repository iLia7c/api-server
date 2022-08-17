package olx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CarModel {
    protected Brand brand;
    protected ModelName modelName;
    private long version;

    private static final String logName = "carModel.log";

    private static int versionCounter = 1;

    // TODO replace RuntimeException
    // TODO files write rewrites logs
    protected CarModel(Brand b, ModelName modelName) {
        brand = b;
        this.modelName = modelName;

        version = versionCounter++;

        try {
            Files.write(Paths.get(logName), Arrays.asList(this.toString()));
        } catch (IOException writeEx) {
            throw new RuntimeException(writeEx);
        }
    }

    public long getVersion() {
        return version;
    }

    public static CarModel getInstance(Brand b, ModelName model) {
        return new CarModel(b, model);
    }

    @Override
    public String toString() {
        return "{Brand: " + brand.name() + ", modelName: "
                + modelName.name() + ", version: " + version + "}";
    }

}
