package jose.campuzano.chacacterservicio.ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {
    //singleton
    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }
    //schedulerExecutorService
    private final ScheduledExecutorService schedulerExecutorService = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService schedulerExecutorService(){
        return schedulerExecutorService;
    }
}
