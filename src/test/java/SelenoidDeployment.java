import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.*;

public class SelenoidDeployment {
    private static final Logger logger = LogManager.getLogger(SelenoidDeployment.class);


    public void deploySelenoid(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","./RunSelenoid.sh");

        try {
            File deploySelenoidLog = new File("deploySelenoidLog.txt");
            processBuilder.redirectErrorStream(true);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.to(deploySelenoidLog));

            logger.info("Starting deploy Selenoid");
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Selenoid deployed successfully");
            }
            else if(exitCode ==127){
                logger.info("Selenoid is already running. Skip deployment");
            }
            else {
                throw new SelenoidDeploymentException(String.format("Selenoid Deployment process exit code is %s", exitCode));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SelenoidDeploymentException(String.format("Not possible to deploy selenoid because of error %s", e.getMessage()));
        }
    }



    public String getSelenoidListedPort(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","docker ps --filter \"name=^selenoid$\" --format '{{.Ports}}'");
        try {
            Process process = processBuilder.start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int exitCode = process.waitFor();
            if (exitCode==0){
                line = reader.readLine();
                String port = line.substring(line.indexOf(":")+1,line.indexOf("-"));
                logger.info("Selenoid listed port is {}", port);
                return port;
            }
            else {
                throw new SelenoidPortNotDefinedException(String.format("Port is not defined. Process exit code is %s",exitCode));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new SelenoidPortNotDefinedException(String.format("Not possible to define port because of error %s", e.getMessage()));
        }
    }
}
